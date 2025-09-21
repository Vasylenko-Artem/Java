package core;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    // Конструктор
    public QuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("a не може бути 0 для квадратного рівняння");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Метод для обчислення кількості коренів
    public int getNumberOfRoots() {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0)
            return 2;
        if (discriminant == 0)
            return 1;
        return 0;
    }

    // Метод для обчислення коренів (повертає масив або null)
    public double[] getRoots() {
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0)
            return null; // коренів немає

        if (discriminant == 0) {
            double root = -b / (2 * a);
            return new double[] { root };
        } else {
            double sqrtD = Math.sqrt(discriminant);
            double root1 = (-b + sqrtD) / (2 * a);
            double root2 = (-b - sqrtD) / (2 * a);
            return new double[] { root1, root2 };
        }
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
