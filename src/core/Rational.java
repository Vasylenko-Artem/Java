package core;

public class Rational implements Comparable<Rational> {
    private int numerator;
    private int denominator;

    // Конструктор
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменник не може бути нулем");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        reduce(); // одразу скорочуємо дріб
    }

    // Приватний метод скорочення дробу
    private void reduce() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        // знаменник завжди додатній
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // Найбільший спільний дільник (Алгоритм Евкліда)
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Арифметичні операції
    public Rational add(Rational other) {
        return new Rational(
                this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator);
    }

    public Rational subtract(Rational other) {
        return new Rational(
                this.numerator * other.denominator - other.numerator * this.denominator,
                this.denominator * other.denominator);
    }

    public Rational multiply(Rational other) {
        return new Rational(
                this.numerator * other.numerator,
                this.denominator * other.denominator);
    }

    public Rational divide(Rational other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Ділення на нульовий дріб неможливе");
        }
        return new Rational(
                this.numerator * other.denominator,
                this.denominator * other.numerator);
    }

    // Порівняння
    @Override
    public int compareTo(Rational other) {
        long diff = (long) this.numerator * other.denominator - (long) other.numerator * this.denominator;
        return Long.compare(diff, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Rational))
            return false;
        Rational other = (Rational) obj;
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    @Override
    public String toString() {
        return denominator == 1 ? String.valueOf(numerator) : numerator + "/" + denominator;
    }

    // Геттери
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // Тестування
    public static void main(String[] args) {
        Rational r1 = new Rational(2, 4);
        Rational r2 = new Rational(3, 6);

        System.out.println("r1 = " + r1); // 1/2
        System.out.println("r2 = " + r2); // 1/2

        System.out.println("r1 + r2 = " + r1.add(r2)); // 1/2 + 1/2 = 1
        System.out.println("r1 - r2 = " + r1.subtract(r2)); // 0
        System.out.println("r1 * r2 = " + r1.multiply(r2)); // 1/4
        System.out.println("r1 / r2 = " + r1.divide(r2)); // 1

        System.out.println("r1 == r2 ? " + r1.equals(r2)); // true
        System.out.println("r1 > r2 ? " + (r1.compareTo(r2) > 0)); // false
    }
}
