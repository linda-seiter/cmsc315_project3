import java.util.Scanner;

public class MainSolution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            // Integer[] values = { 90, 70, 50, 20, 40 };
            // CompleteBinaryTreeSolution tree = new CompleteBinaryTreeSolution(values);
            System.out.print("Enter a binary tree: ");
            String treeString = input.nextLine();
            CompleteBinaryTreeSolution tree = new CompleteBinaryTreeSolution(treeString);
            tree.preorder();
            System.out.println("Is a max-heap: " + tree.isMaxHeap());
            System.out.println("Is a binary search tree: " + tree.isBinarySearchTree());
            System.out.println("Inorder List: " + tree.inorderList());

        } catch (InvalidTreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
