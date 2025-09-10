package tasks;

import app.Main;

import java.util.Scanner;

public class Task3 {
    public static void run() {
        Scanner sc = Main.sc;

        System.out.print("Input n (<=15): ");
        int n = sc.nextInt();

        if (n <= 0 || n > 15) {
            System.out.println("n must be between 1 and 15");
            return;
        }

        int[][] X = new int[n][n];
        System.out.println("Input matrix " + n + "x" + n + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                X[i][j] = sc.nextInt();
            }
        }

        boolean[] L = new boolean[n];

        // Vector
        for (int i = 0; i < n; i++) {
            int negatives = 0;
            int positives = 0;
            for (int j = 0; j < n; j++) {
                if (X[i][j] < 0)
                    negatives++;
                else if (X[i][j] > 0)
                    positives++;
            }
            L[i] = (negatives > positives);
        }

        // Output
        System.out.println("Logical vector L:");
        for (int i = 0; i < n; i++) {
            System.out.print(L[i] + " ");
        }
        System.out.println();

        sc.nextLine();
    }
}
