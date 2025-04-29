public class CompleteBinaryTree {

    protected TreeNode root;

    /**
     * A static nested class representing a node in the binary tree.
     * Contains an integer value and references to left and right children.
     */
    public static class TreeNode {
        protected Integer value;
        protected TreeNode left;
        protected TreeNode right;

        /**
         * Constructs a TreeNode with a given integer value.
         * 
         * @param value the value to store in the node
         */
        public TreeNode(Integer value) {
            this.value = value;
        }
    }

    /**
     * Constructs a complete binary tree from an array of integers.
     * Nodes are filled in level-order. For a node at index i:
     * left child is at 2*i + 1, right child is at 2*i + 2.
     *
     * @param array array of Integer values to build the tree
     * @throws InvalidTreeException if any array element is null
     */
    public CompleteBinaryTree(Integer[] array) throws InvalidTreeException {
        if (array == null || array.length == 0) {
            root = null;
        } else {
            root = makeNode(array, 0);
        }
    }

    /**
     * Recursively constructs a complete binary tree from an array.
     * The array is assumed to represent a complete binary tree in level-order
     * traversal.
     * 
     * For each index `i` in the array:
     * - The element at index `i` represents the node.
     * - The left child of the node is at index `2*i + 1`.
     * - The right child of the node is at index `2*i + 2`.
     * 
     * This method constructs the tree in a level-by-level manner.
     * 
     * @param array array of integer values representing the tree in level-order
     * @param index current index in the array that corresponds to the current node
     * @return TreeNode at the current index, with left and right children
     *         recursively constructed
     * @throws InvalidTreeException if a node value is null or invalid
     */
    protected TreeNode makeNode(Integer[] array, int index) throws InvalidTreeException {
        if (index >= array.length) {
            return null;
        }
        if (array[index] == null) {
            throw new InvalidTreeException("Node element must not be null");
        }

        TreeNode node = new TreeNode(array[index]);
        node.left = makeNode(array, 2 * index + 1);
        node.right = makeNode(array, 2 * index + 2);

        return node;
    }

    /**
     * Performs a preorder traversal of the tree, printing nodes by depth.
     */
    public void preorder() {
        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();
    }

    /**
     * Recursive helper method for preorder traversal.
     *
     * @param root the current subtree root
     */
    private void preorder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.value + " ");
        preorder(root.left);
        preorder(root.right);
    }

}
