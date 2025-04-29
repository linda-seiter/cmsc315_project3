import java.util.Scanner;

public class MainSolution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            // Integer[] values = { 53, 28, 83, 11, 61, 67 };
            // CompleteBinaryTreeSolution tree = new CompleteBinaryTreeSolution(values);
            System.out.print("Enter a binary tree: ");
            String treeString = input.nextLine();
            CompleteBinaryTreeSolution tree = new CompleteBinaryTreeSolution(treeString);
            tree.preorder();
            // System.out.println("Inorder List: " + tree.inorderList());
            // System.out.println("Is a max-heap: " + tree.isMaxHeap());
            // System.out.println("Is a binary search tree: " + tree.isBinarySearchTree());
        } catch (InvalidTreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
