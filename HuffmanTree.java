
public class HuffmanTree extends BinaryTree<HuffmanData> implements Comparable<HuffmanTree> {
	public HuffmanTree(HuffmanData newData) {
		super();
		super.root.data = newData;
	}
	public HuffmanTree(String symbol, int occurrences) {
		super();
		super.root.data = new HuffmanData(symbol, occurrences);
	}
	public HuffmanTree(HuffmanData rootData, HuffmanTree leftTree, HuffmanTree rightTree) {
		super(rootData, leftTree, rightTree);
	}
	public HuffmanTree merge(HuffmanTree otherTree) {
		HuffmanData newRoot = new HuffmanData(this.getData().getSymbol() + otherTree.getData().getSymbol(), this.getData().getOccurrences() + otherTree.getData().getOccurrences());
		return new HuffmanTree(newRoot, this, otherTree);
	}
	
	public int compareTo(HuffmanTree otherTree) {
		//this puts symbols with GREATER occurrences at the front of the PriorityQueue.
	//	return otherTree.getData().getOccurrences() - this.getData().getOccurrences();
		
		//this puts symbols with FEWER occurrences at the front of the PriorityQueue.
		return this.getData().getOccurrences() - otherTree.getData().getOccurrences();
	}
}
