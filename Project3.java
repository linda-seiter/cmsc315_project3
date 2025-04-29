import java.util.*;

public class Project3 {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String response;
        do {
            System.out.print("Enter a binary tree: ");
            String treeString = input.nextLine();
            try {
                OldBinaryTree tree = new OldBinaryTree(treeString);
                tree.display();
                if (!tree.isBinarySearchTree()) {
                    System.out.println("It is not a binary search tree");
                    makeBalancedTree(tree);
                } else if (tree.isBalanced())
                    System.out.println("It is a balanced binary search tree");
                else {
                    System.out.println("It is a binary search tree but it is not balanced");
                    makeBalancedTree(tree);
                }
            } catch (InvalidTreeSyntax invalidTreeSyntax) {
                System.out.println(invalidTreeSyntax.getMessage());
            }
            System.out.print("More trees? Y or N: ");
            response = input.nextLine();
        } while (response.equals("Y"));
    }

    private static void makeBalancedTree(OldBinaryTree tree) {
        ArrayList<Integer> list = tree.inorderList();
        Collections.sort(list);
        OldBinaryTree balancedTree = new OldBinaryTree(list);
        balancedTree.display();
        System.out.println("Original tree has height " + tree.height());
        System.out.println("Balanced tree has height " + balancedTree.height());
    }
}
