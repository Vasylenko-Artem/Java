package series;

public abstract class Series {
    protected double first; // a1
    protected double ratio; // d

    public Series(double first, double ratio) {
        this.first = first;
        this.ratio = ratio;
    }

    // abstract methods
    public abstract double getElement(int n); // calculating the nth term

    public abstract double getSum(int n); // calculating the sum of n terms

    // getters
    public double getFirst() {
        return first;
    }

    public double getRatio() {
        return ratio;
    }
}
