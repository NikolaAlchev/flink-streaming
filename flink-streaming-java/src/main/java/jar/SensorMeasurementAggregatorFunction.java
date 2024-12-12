package jar;

import org.apache.flink.api.common.functions.AggregateFunction;

public class SensorMeasurementAggregatorFunction implements AggregateFunction<SensorMeasurement, AggregatorAccumulator, String> {

    @Override
    public AggregatorAccumulator createAccumulator() {
        return new AggregatorAccumulator("");
    }

    @Override
    public AggregatorAccumulator add(SensorMeasurement value, AggregatorAccumulator accumulator) {
        if (accumulator.key.isEmpty()) {
            accumulator.key = value.getKey();
        }
        accumulator.add(value);
        return accumulator;
    }

    @Override
    public AggregatorAccumulator merge(AggregatorAccumulator a, AggregatorAccumulator b) {
        return a.merge(b);
    }

    @Override
    public String getResult(AggregatorAccumulator accumulator) {
        return accumulator.getResult();
    }
}
