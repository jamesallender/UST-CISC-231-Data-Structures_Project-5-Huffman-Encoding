
public class HuffmanDictionary implements CodeDictionary{
	private HuffmanTree codeTable;
	//private String inFile;
	
	public HuffmanDictionary(String inFile) {
		//this.inFile = inFile;
		this.codeTable = this.buildTable(inFile);
	}
	private HuffmanTree buildTable(String inFile) {
		
		return null;
	}
	public String lookup(String key) {	//Huffman sends to this when encoding
		//we're supposed to return the proper binary code
		return null;
	}
	public String reverseLookup(String value) {
		return null;
	}
	public String toString() {
		return "";
	}
}
