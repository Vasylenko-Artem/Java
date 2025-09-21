package core;

public class BinaryTree {
    private Node root;

    // Internal class Node
    private static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    // Adding an element
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value);
        }
        // if value == node.value, we do nothing (no duplicates)
        return node;
    }

    // Search for an item
    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node node, int value) {
        if (node == null)
            return false;
        if (value == node.value)
            return true;
        return value < node.value
                ? containsRecursive(node.left, value)
                : containsRecursive(node.right, value);
    }

    // Removing an element
    public void remove(int value) {
        root = removeRecursive(root, value);
    }

    private Node removeRecursive(Node node, int value) {
        if (node == null)
            return null;

        if (value < node.value) {
            node.left = removeRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = removeRecursive(node.right, value);
        } else {
            // Node found
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            // A node with two children
            int smallestValue = findSmallestValue(node.right);
            node.value = smallestValue;
            node.right = removeRecursive(node.right, smallestValue);
        }
        return node;
    }

    private int findSmallestValue(Node node) {
        return node.left == null ? node.value : findSmallestValue(node.left);
    }

    // Tree seal is in-order
    public void printInOrder() {
        printInOrderRecursive(root);
        System.out.println();
    }

    private void printInOrderRecursive(Node node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.print(node.value + " ");
            printInOrderRecursive(node.right);
        }
    }
}
