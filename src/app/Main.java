package app;

import static utils.Input.*;

import tasks.Task1;
import tasks.Task2;

public class Main {
    public static void main(String[] args) {
        while (true) {
            clearConsole();
            printMenu();

            int choice = readInt("\nYour choice: ");

            clearConsole();

            System.out.println("You have chosen task " + choice);

            switch (choice) {
                case 1:
                    Task1.run();
                    break;
                case 2:
                    Task2.run();
                    break;
                case 0:
                    clearConsole();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }

            readLine("\nPress Enter to continue...");
        }
    }

    private static void printMenu() {
        System.out.println("Choose a task:");
        System.out.println("1 - Task 1");
        System.out.println("2 - Task 2");
        System.out.println("\n0 - Exit");
    }

    private static void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
