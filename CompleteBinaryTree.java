/* A complete binary tree is always balanced in the height-difference 
sense — that is, for every node, the difference in height between the left and right subtree is at most 1. */
public class CompleteBinaryTree {

    protected TreeNode root;

    /* Static because TreeNode does not access members of BinaryTree */
    public static class TreeNode {
        protected Integer element;
        protected TreeNode left;
        protected TreeNode right;

        public TreeNode(Integer element) {
            this.element = element;
        }
    }

    /**
     * Create a complete binary tree from an array.
     * Build the tree level by level, starting at the root.
     * For each array element at index i:
     * Node element is at i
     * Left child is at 2*i + 1
     * Right child is at 2*i + 2
     */
    public CompleteBinaryTree(Integer[] array) throws InvalidTreeException {
        if (array == null || array.length == 0) {
            root = null;
        } else {
            root = makeNode(array, 0);
        }
    }

    /**
     * Create a complete binary tree from a string containing a space-delimited
     * sequence of integers.
     */
    public CompleteBinaryTree(String s) throws InvalidTreeException {
        String[] tokens = s.trim().split("\\s+");
        Integer[] values = new Integer[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            try {
                values[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                throw new InvalidTreeException("Node element must be an integer");
            }
        }
        CompleteBinaryTree t = new CompleteBinaryTree(values);
        this.root = t.root;
    }

    /** Helper method to create tree recursively. */
    private TreeNode makeNode(Integer[] array, int index) throws InvalidTreeException {
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

    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root, 0);
        // System.out.println();
    }

    /** Preorder traversal from a subtree */
    private void preorder(TreeNode root, int indent) {
        if (root == null)
            return;
        // System.out.print(root.element + " ");
        System.out.println(" ".repeat(indent) + root.element);
        preorder(root.left, indent + 4);
        preorder(root.right, indent + 4);
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode current) {
        if (current == null)
            return -1;
        return (Math.max(height(current.left), height(current.right)) + 1);
    }

    public boolean isMaxHeap() {
        return isMaxHeap(root);
    }

    private boolean isMaxHeap(TreeNode node) {
        if (node == null)
            return true;

        // Check left child
        if (node.left != null) {
            if (node.element < node.left.element) {
                return false;
            }
        }

        // Check right child
        if (node.right != null) {
            if (node.element > node.right.element) {
                return false;
            }
        }

        // Recursively check left and right subtrees
        return isMaxHeap(node.left) && isMaxHeap(node.right);
    }

    public boolean isBinarySearchTree() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode node, int min, int max) {
        if (node == null)
            return true;
        if (node.element <= min || node.element >= max)
            return false;
        return isBST(node.left, min, node.element) &&
                isBST(node.right, node.element, max);
    }

}
