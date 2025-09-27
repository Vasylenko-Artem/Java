package series2;

public class Linear implements Series {
    private double first;
    private double difference;

    public Linear(double first, double difference) {
        this.first = first;
        this.difference = difference;
    }

    @Override
    public double getElement(int n) {
        return first + (n - 1) * difference;
    }

    @Override
    public double getSum(int n) {
        return (n / 2.0) * (2 * first + (n - 1) * difference);
    }

    @Override
    public String toString() {
        return "Arithmetic progression [first=" + first + ", difference=" + difference + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Linear))
            return false;
        Linear linear = (Linear) o;
        return Double.compare(linear.first, first) == 0 &&
                Double.compare(linear.difference, difference) == 0;
    }
}
