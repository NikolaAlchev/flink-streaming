package jar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorMeasurement {
    private String key;

    private int value;

    private long timestamp;

    @Override
    public String toString() {
        return "SensorMeasurement{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}