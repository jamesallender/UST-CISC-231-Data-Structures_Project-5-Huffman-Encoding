/**
 * This is an implementation of a binary tree
 *
 * @version 8/9/17
 */
public class BinaryTree<E> {
    protected Node<E> root;   /** this is the root node */

    /**
     * Creates an empty binary tree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Creates a binary tree with the given root
     * @param newRoot  reference to the root node
     */
    public BinaryTree(Node<E> new_root) {
        root = new_root;
    }

    /**
     * Creates a binary tree with the given data at the root and subtrees
     * @param new_data  reference to the data for the root node
     * @param leftTree  reference to the left subtree
     * @param rightTree reference to the right subtree
     */
    public BinaryTree(E new_data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        //create new root node
        root = new Node<>(new_data);

        // set left child
        if (leftTree != null) {
            root.left = leftTree.root;
        }
        else {
            root.left = null;
        }

        //set right child
        if (rightTree != null) {
            root.right = rightTree.root;
        }
        else {
            root.right = null;
        }
    }

    /**
     * @return the data item stored in the root node
     */
    public E getData() {
        return root.data;
    }

    /**
     * @return the left subtree, or null if either the root 
     * or the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            // need to "wrap" left node in a BinaryTree object before returning
            BinaryTree<E> subtree = new BinaryTree<>(root.left);
            return subtree;
        }
        return null;
    }

    /**
     * @return the right subtree, or null if either the root 
     * or the right subtree is null
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            // need to "wrap" right node in a BinaryTree object before returning
            BinaryTree<E> subtree = new BinaryTree<>(root.right);
            return subtree;
        }
        return null;
    }

    /**
     * @return true if root node is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * @return string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    /** Converts a sub-tree to a string. 
     * Performs a preorder traversal. 
     * @param node The local root 
     * @param depth The depth 
     * @param sb The StringBuilder to save the output 
     */ 
    private void toString(Node<E> node, int depth, StringBuilder sb) { 
        for (int i = 1; i < depth; i++) { 
            sb.append("  "); 
        } 
        if (node == null) { 
            sb.append("null\n"); 
        }
        else { 
            sb.append(node.toString()); 
            sb.append("\n"); 
            toString(node.left, depth + 1, sb); 
            toString(node.right, depth + 1, sb); 
        } 
    } 

    /**
     * Performs inorder traversal; prints data when node is "visited."
     */
    public void inOrder() {
        inOrder(this);
    }
    private void inOrder(BinaryTree<E> T) {
        if (T != null) {
            inOrder(T.getLeftSubtree());
            System.out.print(T.getData());
            inOrder(T.getRightSubtree());
        }
    }

    /**
     * Performs preorder traversal; prints data when node is "visited."
     */
    public void preOrder() {
        preOrder(this);
    }
    private void preOrder(BinaryTree<E> T) {
        if (T != null) {
            System.out.print(T.getData());
            preOrder(T.getLeftSubtree());
            preOrder(T.getRightSubtree());
        }
    }

    /**
     * Performs postorder traversal; prints data when node is "visited."
     */
    public void postOrder() {
        postOrder(this);
    }
    private void postOrder(BinaryTree<E> T) {
        if (T != null) {
            postOrder(T.getLeftSubtree());
            postOrder(T.getRightSubtree());
            System.out.print(T.getData());
        }
    }

    /**
     * A tree node can store a data element and a reference to left and right children.
     *
     * @version 8/9/17
     */
    protected static class Node<E> {
        protected E data;           /**  data stored in this node */
        protected Node<E> left;   /**  reference to the left child node */
        protected Node<E> right;  /**  reference to the right child node */

        /**
         * Creates a new node with a null next field
         * 
         * @param new_data a value for the data element
         */
        public Node(E new_data) {
            data = new_data;
            left = null;
            right = null;
        }

        /**
         * @return the string representation of this node
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
}