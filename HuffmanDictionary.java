import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class HuffmanDictionary implements CodeDictionary{
	private HuffmanTree codeTable;
	private PriorityQueue<HuffmanTree> allTrees = new PriorityQueue<HuffmanTree>();
	private ArrayList<HuffmanTree> treeList = new ArrayList<HuffmanTree>(); //consider binarySearchTree 
	
	public HuffmanDictionary(String inFile) {
		this.codeTable = this.buildTable(inFile);
	}
	private HuffmanTree buildTable(String inFile) {
		try {
			FileInputStream inStream = new FileInputStream(inFile);
			Iterator<HuffmanTree> it;
			
			while (inStream.available() > 0) {
				String curLetter = Character.toString((char) inStream.read());
				HuffmanTree curTree = new HuffmanTree(new HuffmanData(curLetter, 1));
				int treeLocation = treeList.indexOf(curTree);
				
				if (treeLocation != -1) {
					treeList.get(treeLocation).getData().incOccurrences();
				} else {
					treeList.add(curTree);
				}
			}
			it = treeList.iterator();
			while (it.hasNext()) {
				allTrees.offer(it.next());
			}
			while (allTrees.size() > 1) {
				allTrees.add(allTrees.poll().merge(allTrees.poll()));
			}
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTrees.poll();
	}
	public String lookup(String key) {	//Huffman sends to this when encoding
		//we're supposed to return the proper binary code
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
			if (value.substring(0,1).equals("0")) {
				curTable = (HuffmanTree) curTable.getLeftSubtree();
			} else if (value.substring(0,1).equals("1")) {
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
