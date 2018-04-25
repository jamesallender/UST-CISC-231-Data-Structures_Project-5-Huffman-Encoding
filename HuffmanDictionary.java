import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 
 * @author James Allender & Adam Abrams, CISC 231 Data Structures, Assignment 5,
 *         Professor Jason Sawin, Class HuffmanDictionary
 * 
 * 
 * 
 */
public class HuffmanDictionary implements CodeDictionary {
	// Instance Variables
	private HuffmanTree codeTable; // This is the HuffmanTree that will be used to generate the codes for encoding
									// characters
	private PriorityQueue<HuffmanTree> allTrees; // Will hold
	private ArrayList<HuffmanTree> treeList; // consider binarySearchTree

	/**
	 * Constructor creates a new HuffmanDictionary instance with a string for the
	 * file name that is to be
	 * 
	 * @param inFile
	 */
	public HuffmanDictionary(String inFile) {
		this.allTrees = new PriorityQueue<HuffmanTree>();
		this.treeList = new ArrayList<HuffmanTree>();
		this.codeTable = this.buildTable(inFile);
	}

	/**
	 * 
	 * @param inFile
	 * @return
	 */
	private HuffmanTree buildTable(String inFile) {
		Iterator<HuffmanTree> it;
		FileInputStream inStream;
		int treeIndex;
		boolean treeFound;

		try {
			inStream = new FileInputStream(inFile);
			while (inStream.available() > 0) {
				// Get the next character in the stream
				String curLetter = Character.toString((char) inStream.read());

				// Removed because this will create an object for every character all potently
				// thousands, not just 30 ish
				// HuffmanTree curTree = new HuffmanTree(new HuffmanData(curLetter));
				// int treeLocation = treeList.indexOf(curTree);

				// Set while loop conditions before start of loop
				treeIndex = 0;
				treeFound = false;
				// loops until the index is the size of the ArrayList or a match has been found
				while (treeIndex < treeList.size() && !treeFound) {
					// Determine if a match has been found
					treeFound = treeList.get(treeIndex).getData().getSymbol().equals(curLetter);
					// Increment search index
					treeIndex++;
				}
				// If the index was not the size of the tree
				if (treeIndex != treeList.size()) {
					// Go to that index and increment the occurrences
					treeList.get(treeIndex).getData().incrementOccurrences();
				}
				// Otherwise if the index was the size of the treeList
				else {
					// Add a new HuffmanTree to the ArrayList
					treeList.add(new HuffmanTree(new HuffmanData(curLetter)));
				}
			}
			// Close stream
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Iterate over the treeList and add the trees to the PriorityQueue
		it = treeList.iterator();
		while (it.hasNext()) {
			allTrees.offer(it.next());
		}
		// While the PriorityQueue has more than 1 tree in it merge the top two trees
		// together
		while (allTrees.size() > 1) {
			allTrees.add(allTrees.poll().merge(allTrees.poll()));
		}
		// Return the last tree in the PriorityQueue
		return allTrees.poll();
	}

	public String lookup(String key) { // Huffman sends to this when encoding
		// we're supposed to return the proper binary code
		HuffmanTree curTable = codeTable;
		String result = "";
		boolean throwEx = true;

		while (curTable.getData().getSymbol().contains(key)) {
			throwEx = false;
			if (curTable.getLeftSubtree().getData().getSymbol().contains(key)) {
				result += "0";
				curTable = (HuffmanTree) curTable.getLeftSubtree();
			} else if (curTable.getRightSubtree().getData().getSymbol().contains(key)) {
				result += "1";
				curTable = (HuffmanTree) curTable.getRightSubtree();
			} else {
				return result;
			}
		}
		if (throwEx) {
			throw new IllegalArgumentException();
		}
		return null;
	}

	public String reverseLookup(String value) {
		HuffmanTree curTable = codeTable;
		boolean throwEx = true;

		while (value.length() > 0) {
			throwEx = false;
			if (value.substring(0, 1).equals("0")) {
				curTable = (HuffmanTree) curTable.getLeftSubtree();
			} else if (value.substring(0, 1).equals("1")) {
				curTable = (HuffmanTree) curTable.getRightSubtree();
			} else {
				throw new IllegalArgumentException();
			}
		}
		if (throwEx) {
			throw new IllegalArgumentException();
		}
		return curTable.getData().getSymbol();
	}

	public String toString() {
		return "";
	}
}
