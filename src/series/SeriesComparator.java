package series;

import java.util.Comparator;

public class SeriesComparator implements Comparator<Series> {
    @Override
    public int compare(Series s1, Series s2) {
        return Double.compare(s1.getFirst(), s2.getFirst());
    }
}
