package tasks;

import app.Main;

import java.util.Scanner;

public class Task1 {
    public static double formula(double x, double y) {
        return x * y + Math.pow(x + y, 3) / (x * x + y * y) * (x - y);
    }

    public static void run() {
        Scanner sc = Main.sc;

        // 1) double => double
        System.out.print("Input x (double): ");
        double x1 = sc.nextDouble();
        System.out.print("Input y (double): ");
        double y1 = sc.nextDouble();
        double result1 = formula(x1, y1);
        System.out.println("Result (double => double): " + result1);

        // 2) int => double
        System.out.print("\nInput x (int): ");
        int x2 = sc.nextInt();
        System.out.print("Input y (int): ");
        int y2 = sc.nextInt();
        double result2 = formula(x2, y2);
        System.out.println("Result (int =>double): " + result2);

        // 3) double => int
        System.out.print("\nInput x (double): ");
        double x3 = sc.nextDouble();
        System.out.print("Input y (double): ");
        double y3 = sc.nextDouble();
        int result3 = (int) formula(x3, y3);
        System.out.println("Result (double => int): " + result3);

        sc.nextLine();
    }
}
