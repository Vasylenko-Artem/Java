package tasks;

import core.BinaryTree;

public class Task3 {
    public static void run() {
        BinaryTree tree = new BinaryTree();

        // Adding elements
        int[] valuesToAdd = { 50, 30, 70, 20, 40, 60, 80 };
        for (int v : valuesToAdd) {
            tree.add(v);
        }

        System.out.print("Tree after addition: ");
        tree.printInOrder(); // 20 30 40 50 60 70 80

        // Search for elements
        System.out.println("Is there 40? " + tree.contains(40)); // true
        System.out.println("Is there 25? " + tree.contains(25)); // false

        // Removing elements
        tree.remove(20);
        tree.remove(30);
        tree.remove(50);

        System.out.print("Tree after deletions: ");
        tree.printInOrder(); // 40 60 70 80
    }
}
