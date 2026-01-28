package DSA.tree_adt;

/**
 * Binary Search Tree (BST)
 */
public class BST {
    class Node {
        int key;
        Node left, right;

        Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }

    public boolean search(int key) {
        return searchRec(root, key) != null;
    }

    private Node searchRec(Node root, int key) {
        if (root == null || root.key == key)
            return root;
        if (key < root.key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    public int findMin() {
        Node curr = root;
        while (curr.left != null)
            curr = curr.left;
        return curr.key;
    }

    public int findMax() {
        Node curr = root;
        while (curr.right != null)
            curr = curr.right;
        return curr.key;
    }
}
