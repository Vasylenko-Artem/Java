package tasks;

import core.QuadraticEquation;
import java.util.Arrays;

public class Task2 {
    public static void run() {
        QuadraticEquation eq1 = new QuadraticEquation(1, -3, 2); // x^2 - 3x + 2 = 0
        QuadraticEquation eq2 = new QuadraticEquation(1, 2, 1); // x^2 + 2x + 1 = 0
        QuadraticEquation eq3 = new QuadraticEquation(1, 0, 1); // x^2 + 1 = 0

        QuadraticEquation[] equations = { eq1, eq2, eq3 };

        for (QuadraticEquation eq : equations) {
            System.out.println("Рівняння: " + eq);
            System.out.println("Кількість коренів: " + eq.getNumberOfRoots());
            double[] roots = eq.getRoots();
            System.out.println("Корені: " + (roots != null ? Arrays.toString(roots) : "немає коренів"));
            System.out.println();
        }
    }
}
