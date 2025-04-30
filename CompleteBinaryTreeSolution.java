import java.util.ArrayList;

/**
 * A class representing a complete binary tree of integers.
 * Supports construction from arrays or strings, traversal, and property checks.
 */
public class CompleteBinaryTreeSolution {

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
     * @param values array of Integer values to build the tree
     * @throws InvalidTreeException if any array element is null
     */
    public CompleteBinaryTreeSolution(Integer[] values) throws InvalidTreeException {
        if (values == null || values.length == 0) {
            root = null;
        } else {
            root = makeNode(values, 0);
        }
    }

    /**
     * Constructs a complete binary tree from a space-separated string of integers
     * given in level-order (i.e., top to bottom, left to right).
     *
     * The string must contain valid integer values only, separated by whitespace.
     * These values are parsed and used to build the tree in level-order.
     *
     * @param levelOrderValues a whitespace-delimited string of integer values
     *                         representing nodes in level-order
     * @throws InvalidTreeException if any token in the input is not a valid integer
     */
    public CompleteBinaryTreeSolution(String levelOrderValues) throws InvalidTreeException {
        // Remove leading/trailing whitespace and split the string into tokens,
        // using any sequence of whitespace (spaces, tabs, etc.) as the delimiter
        String[] tokens = levelOrderValues.trim().split("\\s+");

        // Convert tokens to an array of Integer objects
        Integer[] values = new Integer[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            try {
                // Parse each token as an integer
                values[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                // If a token is not a valid integer, throw a custom exception
                throw new InvalidTreeException("Node value must be an integer.");
            }
        }

        // Use the parsed values to construct the complete binary tree
        // by delegating to another constructor that builds from Integer[]
        CompleteBinaryTreeSolution t = new CompleteBinaryTreeSolution(values);

        // Set the root of this tree to the root of the newly created tree
        this.root = t.root;
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
     * @param values array of integer values representing the tree in
     *               level-order
     * @param index  current index in the array that corresponds to the
     *               current node
     * @return TreeNode at the current index, with left and right children
     *         recursively constructed
     * @throws InvalidTreeException if a node value is null or invalid
     */
    protected TreeNode makeNode(Integer[] values, int index) throws InvalidTreeException {
        if (index >= values.length) {
            return null;
        }
        if (values[index] == null) {
            throw new InvalidTreeException("Node element must not be null");
        }

        TreeNode node = new TreeNode(values[index]);
        node.left = makeNode(values, 2 * index + 1);
        node.right = makeNode(values, 2 * index + 2);

        return node;
    }

    /**
     * Performs a preorder traversal of the tree, printing nodes by depth.
     */
    public void preorder() {
        System.out.println("Preorder: ");
        preorder(root, 0);
    }

    /**
     * Recursive helper method for preorder traversal.
     * Prints each node's value with indentation based on its depth level
     * to visually represent the tree structure.
     *
     * @param root  the current subtree root
     * @param level the indentation level, increases with depth in the tree
     */
    private void preorder(TreeNode root, int level) {
        if (root == null)
            return;
        System.out.println("    ".repeat(level) + root.value);
        preorder(root.left, level + 1);
        preorder(root.right, level + 1);
    }

    /**
     * Checks whether the binary tree satisfies the max-heap property.
     *
     * @return true if the tree is a max-heap, false otherwise
     */
    public boolean isMaxHeap() {
        return isMaxHeap(root);
    }

    /**
     * Recursive helper method to check the max-heap property.
     *
     * @param node current node
     * @return true if subtree rooted at node is a max-heap
     */
    private boolean isMaxHeap(TreeNode node) {
        if (node == null)
            return true;

        if (node.left != null && node.value < node.left.value) {
            return false;
        }

        if (node.right != null && node.value < node.right.value) {
            return false;
        }

        return isMaxHeap(node.left) && isMaxHeap(node.right);
    }

    /**
     * Checks whether the tree is a valid binary search tree (BST).
     * A BST is valid if, for every node:
     * - All nodes in the left subtree are strictly less than the node's value.
     * - All nodes in the right subtree are strictly greater than the node's value.
     *
     * @return true if the tree satisfies BST properties, false otherwise
     */
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, null, null);
    }

    /**
     * Recursive helper method to check BST property using range limits.
     * At each node, ensures:
     * - Node's value is strictly greater than the min bound.
     * - Node's value is strictly less than the max bound.
     * Recursively checks left and right subtrees with updated bounds.
     *
     * @param node current node in the tree
     * @param min  lower bound (exclusive) for the node's value
     * @param max  upper bound (exclusive) for the node's value
     * @return true if the subtree rooted at the current node is a valid BST, false
     *         otherwise
     */
    private boolean isBinarySearchTree(TreeNode node, Integer min, Integer max) {
        if (node == null)
            return true;

        // Check if the node's value is within the valid range
        if ((min != null && node.value <= min) || (max != null && node.value >= max))
            return false;

        // Recursively check the left subtree with updated max bound
        // Recursively check the right subtree with updated min bound
        return isBinarySearchTree(node.left, min, node.value) &&
                isBinarySearchTree(node.right, node.value, max);
    }

    /**
     * Returns an ArrayList containing the values of the tree nodes in in-order
     * traversal, visiting nodes in the following order:
     * - Left subtree
     * - Current node
     * - Right subtree
     *
     * @return an ArrayList containing the values of the nodes in in-order traversal
     */
    public ArrayList<Integer> inorderList() {
        return inorderList(root);
    }

    /**
     * Helper method to recursively collect the node values in in-order traversal.
     * The traversal order is left subtree, then current node, then right subtree.
     *
     * @param current the current node in the subtree
     * @return an ArrayList containing the values of the nodes in in-order traversal
     *         for the current subtree
     */
    private ArrayList<Integer> inorderList(TreeNode current) {
        if (current == null)
            return new ArrayList<Integer>();
        ArrayList<Integer> list = inorderList(current.left);
        list.add(current.value);
        list.addAll(inorderList(current.right));
        return list;
    }

}
