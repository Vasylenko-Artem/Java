package tasks;

import series.Exponential;
import series.Linear;
import series.Series;

public class Task2 {
    public static void run() {
        Series[] seriesArray = new Series[2];

        seriesArray[0] = new Linear(2, 3); // 2, 5, 8, ...
        seriesArray[1] = new Exponential(2, 2); // 2, 4, 8, ...

        for (Series s : seriesArray) {
            System.out.println(s.toString());
            System.out.println("5th element: " + s.getElement(5));
            System.out.println("Sum of the first 5 elements: " + s.getSum(5));
            System.out.println();
        }

        // equals
        Linear l1 = new Linear(2, 3);
        Linear l2 = new Linear(2, 3);
        Exponential e1 = new Exponential(2, 2);

        System.out.println("l1.equals(l2) -> " + l1.equals(l2)); // true
        System.out.println("l1.equals(e1) -> " + l1.equals(e1)); // false
    }
}
