package series2;

public class Exponential implements Series {
    private double first;
    private double denominator;

    public Exponential(double first, double denominator) {
        this.first = first;
        this.denominator = denominator;
    }

    @Override
    public double getElement(int n) {
        return first * Math.pow(denominator, n - 1);
    }

    @Override
    public double getSum(int n) {
        if (denominator == 1) {
            return first * n;
        }
        return first * (1 - Math.pow(denominator, n)) / (1 - denominator);
    }

    @Override
    public String toString() {
        return "Geometric progression [first=" + first + ", denominator=" + denominator + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Exponential))
            return false;
        Exponential exp = (Exponential) o;
        return Double.compare(exp.first, first) == 0 &&
                Double.compare(exp.denominator, denominator) == 0;
    }
}
