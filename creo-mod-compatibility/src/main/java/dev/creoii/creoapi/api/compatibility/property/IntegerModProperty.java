package dev.creoii.creoapi.api.compatibility.property;

public class IntegerModProperty extends ModProperty<Integer> {
    private int value;
    private final int min;
    private final int max;

    public IntegerModProperty(String name) {
        super(name);
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
    }

    public IntegerModProperty(String name, int defaultValue) {
        this(name);
        value = defaultValue;
    }

    public IntegerModProperty(String name, int defaultValue, int min, int max) {
        super(name);
        value = defaultValue;
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        if (value < min || value > max)
            throw new UnsupportedOperationException("New value is outside the range [" + min + "-" + max + "].");
        this.value = value;
    }
}
