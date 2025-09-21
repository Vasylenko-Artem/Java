package core;

public class Rational implements Comparable<Rational> {
    private int numerator;
    private int denominator;

    // Designer
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменник не може бути нулем");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        reduce(); // immediately cut the fraction
    }

    // Private Method of Reduction of Frace
    private void reduce() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        // denominator is always a positive
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // The largest common divisor (Euclid algorithm)
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Arithmetic operations
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
            throw new ArithmeticException("Division by zero is impossible.");
        }
        return new Rational(
                this.numerator * other.denominator,
                this.denominator * other.numerator);
    }

    // Comparison
    @Override
    public int compareTo(Rational other) {
        long diff = (long) this.numerator * other.denominator - (long) other.numerator * this.denominator;
        return Long.compare(diff, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rational))
            return false;
        if (this == obj)
            return true;
        Rational other = (Rational) obj;
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    @Override
    public String toString() {
        return denominator == 1 ? String.valueOf(numerator) : numerator + "/" + denominator;
    }

    // grips
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}
