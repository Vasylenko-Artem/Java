package tasks;

import core.BinaryTree;

public class Task3 {
    public static void run() {
        BinaryTree tree = new BinaryTree();

        // Добавляем элементы
        int[] valuesToAdd = { 50, 30, 70, 20, 40, 60, 80 };
        for (int v : valuesToAdd) {
            tree.add(v);
        }

        System.out.print("Дерево після додавання: ");
        tree.printInOrder(); // 20 30 40 50 60 70 80

        // Проверка поиска
        System.out.println("Чи є 40? " + tree.contains(40)); // true
        System.out.println("Чи є 25? " + tree.contains(25)); // false

        // Удаляем элементы
        tree.remove(20); // лист
        tree.remove(30); // узел с одним ребенком
        tree.remove(50); // узел с двумя детьми

        System.out.print("Дерево після видалень: ");
        tree.printInOrder(); // 40 60 70 80
    }
}
