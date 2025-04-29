import java.util.*;

public class OldBinaryTree {
    private class Node {
        private Node left, right;
        private int data;

        public Node(Node left, int data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }
    }

    private Node root;
    private int indent;

    public OldBinaryTree(String treeString) throws InvalidTreeSyntax {
        treeString = treeString.replaceAll("\\s", "");
        StringTokenizer tokenizer = new StringTokenizer(treeString, "*()", true);
        try {
            root = makeNode(tokenizer);
        } catch (NoSuchElementException exception) {
            throw new InvalidTreeSyntax("Incomplete Tree");
        } catch (NumberFormatException exception) {
            throw new InvalidTreeSyntax("Data is Not an Integer");
        }
        if (tokenizer.hasMoreTokens())
            throw new InvalidTreeSyntax("Extra Characters at the End");
    }

    public OldBinaryTree(ArrayList<Integer> list) {
        Integer[] values = list.toArray(new Integer[list.size()]);
        root = makeNode(values, 0, values.length - 1);
    }

    private Node makeNode(StringTokenizer tokenizer) throws InvalidTreeException {
        String token = tokenizer.nextToken();
        if (token.equals("*"))
            return null;
        if (!token.equals("("))
            throw new InvalidTreeException("Missing Left Parenthesis");
        token = tokenizer.nextToken();
        int data = Integer.parseInt(token);
        Node left = makeNode(tokenizer);
        Node right = makeNode(tokenizer);
        token = tokenizer.nextToken();
        if (!token.equals(")"))
            throw new InvalidTreeException("Missing Right Parentheses");
        return new Node(left, data, right);
    }

    private Node makeNode(Integer[] values, int begin, int end) {
        if (begin > end)
            return null;
        int middle = (begin + end) / 2;
        Node left = makeNode(values, begin, middle - 1);
        Node right = makeNode(values, middle + 1, end);
        return new Node(left, values[middle], right);
    }

    public void display() {
        indent = 0;
        display(root);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root);
    }

    public int height() {
        return height(root);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    public ArrayList<Integer> inorderList() {
        return inorderList(root);
    }

    private void display(Node current) {
        if (current == null)
            return;
        indent++;
        for (int i = 0; i < indent; i++)
            System.out.print("   ");
        System.out.println(current.data);
        display(current.left);
        display(current.right);
        indent--;
    }

    // private boolean isBinarySearchTree(Node current) {
    // if (current == null)
    // return true;
    // boolean leftValid = current.left == null || current.data > current.left.data,
    // rightValid = current.right == null || current.data < current.right.data;
    // return leftValid && rightValid && isBinarySearchTree(current.left)
    // && isBinarySearchTree(current.right);
    // }

    private boolean isBinarySearchTree(Node node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node node, int min, int max) {
        if (node == null)
            return true;
        if (node.data <= min || node.data >= max)
            return false;
        return isBST(node.left, min, node.data) &&
                isBST(node.right, node.data, max);
    }

    private int height(Node current) {
        if (current == null)
            return -1;
        return (Math.max(height(current.left), height(current.right)) + 1);
    }

    private boolean isBalanced(Node current) {
        if (current == null)
            return true;
        boolean balanced = Math.abs(height(current.left) - height(current.right)) <= 1;
        return balanced && isBalanced(current.left) && isBalanced(current.right);
    }

    private ArrayList<Integer> inorderList(Node current) {
        if (current == null)
            return new ArrayList<Integer>();
        ArrayList<Integer> list = inorderList(current.left);
        list.add(current.data);
        list.addAll(inorderList(current.right));
        return list;
    }
}
