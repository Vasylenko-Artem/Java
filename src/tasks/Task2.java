package tasks;

import static utils.Input.*;

import java.util.*;

public class Task2 {
    public static void run() {
        int degree = readInt("Enter the degree of polynomials: ");

        System.out.println("Enter the coefficients of the first polynomial:");
        Polynomial p1 = readPolynomial(sc, degree);

        System.out.println("Enter the coefficients of the second polynomial:");
        Polynomial p2 = readPolynomial(sc, degree);

        Polynomial sum = p1.add(p2);

        System.out.println("\nThe first polynomial:   " + p1);
        System.out.println("The second polynomial:   " + p2);
        System.out.println("The sum of polynomials:   " + sum);
    }

    private static Polynomial readPolynomial(Scanner sc, int degree) {
        HashMap<Integer, Double> coeffs = new HashMap<>();
        for (int i = degree; i >= 0; i--) {
            System.out.print("The coefficient at x^" + i + ": ");
            coeffs.put(i, sc.nextDouble());
        }
        return new Polynomial(coeffs);
    }

    // Внутрішній клас
    static class Polynomial {
        private HashMap<Integer, Double> coeffs;

        public Polynomial(HashMap<Integer, Double> coeffs) {
            this.coeffs = coeffs;
        }

        public Polynomial add(Polynomial other) {
            HashMap<Integer, Double> result = new HashMap<>(this.coeffs);

            for (Map.Entry<Integer, Double> entry : other.coeffs.entrySet()) {
                int power = entry.getKey();
                double value = entry.getValue();
                result.put(power, result.getOrDefault(power, 0.0) + value);
            }
            return new Polynomial(result);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            List<Integer> powers = new ArrayList<>(coeffs.keySet());
            powers.sort(Collections.reverseOrder());

            for (int p : powers) {
                double c = coeffs.get(p);
                if (c == 0)
                    continue;

                if (sb.length() > 0 && c > 0)
                    sb.append(" + ");
                else if (c < 0)
                    sb.append(" - ");

                double absC = Math.abs(c);
                if (p == 0)
                    sb.append(absC);
                else if (p == 1)
                    sb.append(absC + "x");
                else
                    sb.append(absC + "x^" + p);
            }
            return sb.toString();
        }
    }
}
