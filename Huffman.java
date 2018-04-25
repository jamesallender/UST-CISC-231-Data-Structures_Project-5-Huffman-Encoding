import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Objects of this class are able to compress and decompress a given text file
 * using Huffman's encoding scheme.
 * 
 * @version 01/01/17
 */
public class Huffman {
    private long originalBitLength;
    private long compressedBitLength;
    private CodeDictionary dictionary;
    
    /**
     * Creates and initializes a new Huffman (de)compressor.
     */
    public Huffman() {
        originalBitLength = 0;
        compressedBitLength = 0;
        dictionary = null;
    }

    /**
     * Builds the Huffman-code dictionary using the given file for sampling.
     * @param inFile
     */
    public void buildDictionary(String inFile) {
        dictionary = new HuffmanDictionary(inFile);//This is the class you have to create
    }

    /**
     * Takes the uncompressed input file, and writes the compressed version of this file
     * into the specified output file. Uses the given Huffman dictionary for compression.
     * @author David
     * @param inFile
     * @param outFile
     */
    public void encode(String inFile, String outFile) {
        if (dictionary == null) {
            throw new RuntimeException("Error: No dictionary! "
                    + "First run buildDictionary.");
        }
                
        int bitpos = 0;         //counts the current bit position in the compressed string
        BitSet bitset = new BitSet();

        //prepare the compressed bitset 
        //run through contents of given file, and replace each term with a Huffman code
        FileInputStream file = null;
        try {
            file = new FileInputStream(inFile);
            char c;
            String code;
            while (file.available() > 0) {
                c = (char) file.read();     //examine each character from the file stream
                                            //remember that newline and tab are handled specially.
                switch(c) {
                    case '\n':
                        code = dictionary.lookup("\\n");
                        break;
                    case '\t':
                        code = dictionary.lookup("\\t");
                        break;
                    default:
                        code =  dictionary.lookup(Character.toString(c));
                }
                for (int i = 0; i < code.length(); i++) {
                    //sets a bit to 1 at the specified position
                    if (code.charAt(i) == '1') {
                        bitset.set(bitpos);
                    }
                    bitpos++;
                }
                originalBitLength += 8; //8 bits per character that is read
            }
            file.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        //write binary to file
        long[] compressedContent = bitset.toLongArray();    //converts the compressed BitSet into an array of longs
                                                            //BitSet uses little-endian (remember for debugging)
        DataOutputStream fileOut = null;
        try {
            fileOut = new DataOutputStream(new FileOutputStream(outFile));
            //write each word (long) to file sequentially
            for (int i = 0; i < compressedContent.length; i++) {
                fileOut.writeLong(compressedContent[i]);
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //bookkeeping: update the field
        compressedBitLength = bitpos;
    }
    
    /**
     * Decodes a compressed file
     * @param inFile name of the compressed file
     * @param outFile name of file to output the decompressed original data to
     * @param reference to a Huffman dictionary 
     */
    public void decode(String inFile, String outFile) {
        if (dictionary == null) {
            throw new RuntimeException("Error: No dictionary! First run buildDictionary.");
        }
        
        //read each word (longs) from filestream into an ArrayList
        ArrayList<Long> incoming = new ArrayList<Long>();
        DataInputStream fileIn = null;
        try {
            fileIn = new DataInputStream(new FileInputStream(inFile));
            while (fileIn.available() > 0) {
                incoming.add(fileIn.readLong());
            }
            fileIn.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        //convert ArrayList of Longs into long[] for BitSet
        long[] longArr = new long[incoming.size()];
        for (int i = 0; i < longArr.length; i++) {
            longArr[i] = incoming.get(i).longValue();
        }
        BitSet bs = BitSet.valueOf(longArr);

        //write out to file. Decode the codes from the bitset through reverse 
        //lookups (code -> original letter) in the dictionary.
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(outFile);

            //let's decompress
            String bitString = "";  //builds a single huffman code

            //size of the "leftover" pad bits that were packed into a long
            long pad_size = 64 - compressedBitLength % 64;
            for (int i = 0; i < bs.size() - pad_size; i++) {
                //build up a huffman code
                bitString += (bs.get(i)) ? "1" : "0";

                try {
                    //perform a reverse lookup to get the character from the huffman code
                    String token = dictionary.reverseLookup(bitString.toString());

                    //handle newline and tab specially
                    if (token.equals("\\n")) {
                        token = "\n";
                    }
                    else if (token.equals("\\t")) {
                        token = "\t";
                    }
                    fileOut.write(token.getBytes());
                    bitString = "";     //clear the bitString; build a new code
                } catch(IllegalArgumentException e) {
                    //code couldn't be found in the map, so do nothing and keep appending
                }
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Prints contents of the dictionary
     */
    public void printDictionary() {
        //dictionary hadn't been built
        if (dictionary == null) {
            throw new RuntimeException("Error: No dictionary! "
                    + "First run buildDictionary.");
        }
        System.out.println(dictionary.toString());
    }
    
    /**
     * @return the ratio between the size of the compressed file and the 
     * size of the original file
     */
    public double getCompressionRatio() {
        //dictionary hadn't been built
        if (dictionary == null) {
            throw new RuntimeException("Error: No dictionary! "
                    + "First run buildDictionary.");
        }
        return (double) compressedBitLength / originalBitLength;
    }
}
