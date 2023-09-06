package dev.creoii.creoapi.api.compatibility.property;

public abstract class ModProperty<T> {
    private final String name;

    public ModProperty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract T getValue();

    public abstract void setValue(T value);
}
