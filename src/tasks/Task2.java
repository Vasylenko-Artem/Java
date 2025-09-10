package tasks;

import app.Main;

import java.util.Scanner;

public class Task2 {

    public static void run() {
        Scanner sc = Main.sc;

        System.out.print("Input n (<=300): ");
        int n = sc.nextInt();

        if (n <= 0 || n > 300) {
            System.out.println("n must be between 1 and 300");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Input " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Find the longest chain
        int bestLength = 1;
        int bestValue = arr[0];
        int currentLength = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                currentLength++;
            } else {
                currentLength = 1;
            }

            if (currentLength > bestLength) {
                bestLength = currentLength;
                bestValue = arr[i];
            }
        }

        // Output
        System.out.print("Longest chain (" + bestLength + " elements): ");
        for (int i = 0; i < bestLength; i++) {
            System.out.print(bestValue + " ");
        }
        System.out.println();

        sc.nextLine();
    }
}
