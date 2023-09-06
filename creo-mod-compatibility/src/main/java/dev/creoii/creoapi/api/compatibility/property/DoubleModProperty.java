package dev.creoii.creoapi.api.compatibility.property;

public class DoubleModProperty extends ModProperty<Double> {
    private double value;
    private final double min;
    private final double max;

    public DoubleModProperty(String name) {
        super(name);
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
    }

    public DoubleModProperty(String name, double defaultValue) {
        this(name);
        value = defaultValue;
    }

    public DoubleModProperty(String name, double defaultValue, double min, double max) {
        super(name);
        value = defaultValue;
        this.min = min;
        this.max = max;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        if (value < min || value > max)
            throw new UnsupportedOperationException("New value is outside the range [" + min + "-" + max + "].");
        this.value = value;
    }
}
