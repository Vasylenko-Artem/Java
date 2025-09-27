package series;

public class Linear extends Series {
    public Linear(double first, double difference) {
        super(first, difference);
    }

    @Override
    public double getElement(int n) {
        return first + (n - 1) * ratio;
    }

    @Override
    public double getSum(int n) {
        return (n / 2.0) * (2 * first + (n - 1) * ratio);
    }

    @Override
    public String toString() {
        return "Arithmetic progression [first=" + first + ", difference=" + ratio + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Linear))
            return false;
        Linear linear = (Linear) o;
        return Double.compare(linear.first, first) == 0 &&
                Double.compare(linear.ratio, ratio) == 0;
    }
}
