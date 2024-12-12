package jar;

public class AggregatorAccumulator {
    long count;
    int minValue;
    int maxValue;
    int sumValue;
    String key;

    public AggregatorAccumulator(String key) {
        this.count = 0;
        this.minValue = Integer.MAX_VALUE;
        this.maxValue = Integer.MIN_VALUE;
        this.sumValue = 0;
        this.key = key;
    }

    public void add(SensorMeasurement value) {
        count++;
        minValue = Math.min(minValue, value.getValue());
        maxValue = Math.max(maxValue, value.getValue());
        sumValue += value.getValue();
    }

    public AggregatorAccumulator merge(AggregatorAccumulator other) {
        AggregatorAccumulator merged = new AggregatorAccumulator(this.key);
        merged.count = this.count + other.count;
        merged.minValue = Math.min(this.minValue, other.minValue);
        merged.maxValue = Math.max(this.maxValue, other.maxValue);
        merged.sumValue = this.sumValue + other.sumValue;
        return merged;
    }

    public String getResult() {
        double average = count > 0 ? (double) sumValue / count : 0.0;
        long windowStart = System.currentTimeMillis() - 10000; // Example window start time, adjust as needed
        long windowEnd = System.currentTimeMillis(); // Example window end time, adjust as needed
        return String.format(
                "{\"key\":\"%s\",\"window_start\":%d,\"window_end\":%d,\"min_value\":%d,\"count\":%d,\"average\":%.2f,\"max_value\":%d}",
                key, // Use the dynamic key
                windowStart,
                windowEnd,
                minValue,
                count,
                average,
                maxValue
        );
    }
}
