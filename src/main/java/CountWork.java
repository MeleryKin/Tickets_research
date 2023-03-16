import java.util.ArrayList;
import java.util.Collections;

public class CountWork {

    public long getAverageFlightTime(ArrayList<Long> flightTime) {
        return flightTime.stream().reduce(0L, Long::sum) / flightTime.size();
    }

    public double getPercentileFlightTime(ArrayList<Double> latencies, double percentile) {
        Collections.sort(latencies);
        int index = (int) Math.ceil(percentile / 100.0 * latencies.size());
        return latencies.get(index - 1) / 60 + 1;
    }
}
