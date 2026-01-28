package algorithms.tree_algorithms;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int item) {
        val = item;
        left = right = null;
    }
}

/**
 * Binary Tree Traversals (DFS)
 * Time Complexity: O(N)
 */
public class BinaryTreeTraversals {

    TreeNode root;

    void printPostorder(TreeNode node) {
        if (node == null)
            return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.val + " ");
    }

    void printInorder(TreeNode node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    void printPreorder(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public static void main(String[] args) {
        BinaryTreeTraversals tree = new BinaryTreeTraversals();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        System.out.println("Preorder traversal:");
        tree.printPreorder(tree.root);

        System.out.println("\nInorder traversal:");
        tree.printInorder(tree.root);

        System.out.println("\nPostorder traversal:");
        tree.printPostorder(tree.root);
    }
}
