package dev.creoii.creoapi.api.compatibility.property;

import dev.creoii.creoapi.api.compatibility.storage.ModStorage;

public class IntegerModProperty extends ModProperty<Integer> {
    private int value;
    private final int min;
    private final int max;

    private IntegerModProperty(String name) {
        super(name);
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
    }

    private IntegerModProperty(String name, int defaultValue) {
        this(name);
        value = defaultValue;
    }

    private IntegerModProperty(String name, int defaultValue, int min, int max) {
        super(name);
        value = defaultValue;
        this.min = min;
        this.max = max;
    }

    public static void addIntegerProperty(ModStorage modStorage, String name) {
        modStorage.addProperty(name, new IntegerModProperty(name));
    }

    public static void addIntegerProperty(ModStorage modStorage, String name, int defaultValue) {
        modStorage.addProperty(name, new IntegerModProperty(name, defaultValue));
    }

    public static void addIntegerProperty(ModStorage modStorage, String name, int defaultValue, int min, int max) {
        modStorage.addProperty(name, new IntegerModProperty(name, defaultValue, min, max));
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
