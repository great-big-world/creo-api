package dev.creoii.creoapi.api.compatibility.property;

import dev.creoii.creoapi.api.compatibility.storage.ModStorage;

public class DoubleModProperty extends ModProperty<Double> {
    private double value;
    private final double min;
    private final double max;

    private DoubleModProperty(String name) {
        super(name);
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
    }

    private DoubleModProperty(String name, double defaultValue) {
        this(name);
        value = defaultValue;
    }

    private DoubleModProperty(String name, double defaultValue, double min, double max) {
        super(name);
        value = defaultValue;
        this.min = min;
        this.max = max;
    }

    public static void addDoubleProperty(ModStorage modStorage, String name) {
        modStorage.addProperty(name, new DoubleModProperty(name));
    }

    public static void addDoubleProperty(ModStorage modStorage, String name, double defaultValue) {
        modStorage.addProperty(name, new DoubleModProperty(name, defaultValue));
    }

    public static void addDoubleProperty(ModStorage modStorage, String name, double defaultValue, double min, double max) {
        modStorage.addProperty(name, new DoubleModProperty(name, defaultValue, min, max));
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
