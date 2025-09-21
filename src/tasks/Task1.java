package tasks;

import core.Rational;

public class Task1 {
    public static void run() {
        Rational r1 = new Rational(2, 4);
        Rational r2 = new Rational(3, 6);

        System.out.println("r1 = " + r1); // 1/2
        System.out.println("r2 = " + r2); // 1/2

        System.out.println("r1 + r2 = " + r1.add(r2)); // 1
        System.out.println("r1 - r2 = " + r1.subtract(r2)); // 0
        System.out.println("r1 * r2 = " + r1.multiply(r2)); // 1/4
        System.out.println("r1 / r2 = " + r1.divide(r2)); // 1

        System.out.println("r1 == r2 ? " + r1.equals(r2)); // true
        System.out.println("r1 > r2 ? " + (r1.compareTo(r2) > 0)); // false
    }
}
