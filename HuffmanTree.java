
/**
 * 
 * @author James Allender & Adam Abrams, CISC 231 Data Structures, Assignment 5,
 *         Professor Jason Sawin, Class HuffmanTree
 * 
 *         HuffmanTree class models a binary tree where the data of the nodes is
 *         of type HuffmanData. Class extends BinaryTree and implements the
 *         Comparable interface for comparing HuffmanTrees against each other
 * 
 */
public class HuffmanTree extends BinaryTree<HuffmanData> implements Comparable<HuffmanTree> {

	/**
	 * Calls Parent Class constructor to create a new HuffmanTree
	 * 
	 * @param rootData
	 *            the data for the root of the new tree
	 * @param leftTree
	 *            the left sub tree of the new tree
	 * @param rightTree
	 *            the right sub tree of the new tree
	 */
	public HuffmanTree(HuffmanData rootData, HuffmanTree leftTree, HuffmanTree rightTree) {
		super(rootData, leftTree, rightTree);
	}

	/**
	 * constructor explicitly calls above constructor with the given data and null
	 * for the subtrees
	 * 
	 * @param newData
	 *            the data for the root of the new tree
	 */
	public HuffmanTree(HuffmanData newData) {
		this(newData, null, null);
	}

	/**
	 * constructor explicitly calls above constructor with new HuffmanData created
	 * from the given symbol and occurrences and null for the subtrees
	 * 
	 * @param symbol
	 *            the symbol for the data value for the root of the tree
	 * @param occurrences
	 *            the number of occurrences for the data for the root of the tree
	 */
	public HuffmanTree(String symbol, int occurrences) {
		this(new HuffmanData(symbol, occurrences));
	}

	/**
	 * merge method takes two trees and merges them together into a new tree whoes
	 * root node contains the symbols from both of the trees and the sum of the
	 * occurrences of both of the trees
	 * 
	 * @param otherTree
	 *            the other HuffmanTree to be merged with
	 * @return a HuffmanTree of the two trees
	 */
	public HuffmanTree merge(HuffmanTree otherTree) {
		// Create a new HuffmanData instance with the combined data of the the
		// HuffmanTree instance
		HuffmanData newRootData = new HuffmanData(this.getData().getSymbol() + otherTree.getData().getSymbol(),
				this.getData().getOccurrences() + otherTree.getData().getOccurrences());
		return new HuffmanTree(newRootData, this, otherTree);
	}

	// Removed because this method says that two trees are the same tree if they
	// have the same symbol in their root. Moved overridden equals to HuffmanData
	// @Override
	// /**
	// * Overridden equal method compares two HuffmanTrees based on the
	// */
	// public boolean equals(Object other) { // we messed up the hashcode hahahaha,
	// also this says two trees are equal if
	// HuffmanTree otherTree = (HuffmanTree) other;
	// return this.getData().getSymbol().equals(otherTree.getData().getSymbol());
	// }
	//

	public int compareTo(HuffmanTree otherTree) {
		// this puts symbols with GREATER occurrences at the front of the PriorityQueue.
		// return otherTree.getData().getOccurrences() -
		// this.getData().getOccurrences();

		// this puts symbols with FEWER occurrences at the front of the PriorityQueue.
		return this.getData().getOccurrences() - otherTree.getData().getOccurrences();
	}
}
