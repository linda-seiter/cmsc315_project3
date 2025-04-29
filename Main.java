import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter a binary tree: ");
            String treeString = input.nextLine();
            CompleteBinaryTree tree = new CompleteBinaryTree(treeString);
            tree.preorder();
            System.out.println("Height of root node: " + tree.height());
            System.out.println("Is a max-heap: " + tree.isMaxHeap());
            System.out.println("Is a binary search tree: " + tree.isBinarySearchTree());
        } catch (InvalidTreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
