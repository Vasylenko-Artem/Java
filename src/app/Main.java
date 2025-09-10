package app;

import java.util.Scanner;

import tasks.Task1;
import tasks.Task2;
import tasks.Task3;
import tasks.Task4;

public class Main {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            clearConsole();
            printMenu();

            int choice;
            while (true) {
                System.out.print("\nYour choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            }

            clearConsole();

            System.out.println("You have chosen task " + choice);

            switch (choice) {
                case 1:
                    Task1.run();
                    break;
                case 2:
                    Task2.run();
                    break;
                case 3:
                    Task3.run();
                    break;
                case 4:
                    Task4.run();
                    break;
                case 0:
                    clearConsole();
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }

    }

    public static void printMenu() {
        System.out.println("Choose a task:");
        System.out.println("1 - Task 1");
        System.out.println("2 - Task 2");
        System.out.println("3 - Task 3");
        System.out.println("4 - Task 4");
        System.out.println("\n0 - Exit");
    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }

}
