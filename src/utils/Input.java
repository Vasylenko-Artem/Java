package utils;

import java.util.Scanner;

public class Input {
    public static final Scanner sc = new Scanner(System.in);

    private static int readIntInternal(String prompt) {
        if (prompt != null)
            System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please try again.");
            sc.nextLine();
            if (prompt != null)
                System.out.print(prompt);
        }
        int num = sc.nextInt();
        sc.nextLine(); // consume newline
        return num;
    }

    private static double readDoubleInternal(String prompt) {
        if (prompt != null)
            System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please try again.");
            sc.nextLine();
            if (prompt != null)
                System.out.print(prompt);
        }
        double num = sc.nextDouble();
        sc.nextLine(); // consume newline
        return num;
    }

    public static int readInt(String prompt) {
        return readIntInternal(prompt);
    }

    public static int readInt() {
        return readIntInternal(null);
    }

    public static double readDouble(String prompt) {
        return readDoubleInternal(prompt);
    }

    public static double readDouble() {
        return readDoubleInternal(null);
    }

    public static String readLine(String prompt) {
        if (prompt != null)
            System.out.print(prompt);
        return sc.nextLine();
    }

    public static String readLine() {
        return readLine(null);
    }
}
