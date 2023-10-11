package dev.creoii.creoapi.api;

import org.jetbrains.annotations.Nullable;

public class ModProperty<T extends Comparable<T>> {
    private final String name;
    private T value;
    @Nullable
    private final T defaultValue;

    public ModProperty(String name, T initialValue, @Nullable T defaultValue) {
        this.name = name;
        this.value = initialValue;
        this.defaultValue = defaultValue;
    }

    public ModProperty(String name, T initialValue) {
        this(name, initialValue, null);
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    @Nullable
    public T getDefaultValue() {
        return defaultValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void reset() {
        value = defaultValue;
    }
}
