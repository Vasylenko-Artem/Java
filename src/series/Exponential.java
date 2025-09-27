package series;

public class Exponential extends Series {
    public Exponential(double first, double denominator) {
        super(first, denominator);
    }

    @Override
    public double getElement(int n) {
        return first * Math.pow(ratio, n - 1);
    }

    @Override
    public double getSum(int n) {
        if (ratio == 1) {
            return first * n;
        }
        return first * (1 - Math.pow(ratio, n)) / (1 - ratio);
    }

    @Override
    public String toString() {
        return "Geometric progression [first=" + first + ", denominator=" + ratio + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Exponential))
            return false;
        Exponential exp = (Exponential) o;
        return Double.compare(exp.first, first) == 0 &&
                Double.compare(exp.ratio, ratio) == 0;
    }
}
