package dev.creoii.creoapi.api.compatibility.property;

import dev.creoii.creoapi.api.compatibility.storage.ModStorage;

public class EnumModProperty<T extends Enum<T>> extends ModProperty<T> {
    public T value;

    private EnumModProperty(String name) {
        super(name);
    }

    private EnumModProperty(String name, T defaultValue) {
        this(name);
        value = defaultValue;
    }

    public static <T extends Enum<T>> void addStringProperty(ModStorage modStorage, String name) {
        modStorage.addProperty(name, new EnumModProperty<T>(name));
    }

    public static <T extends Enum<T>> void addStringProperty(ModStorage modStorage, String name, T defaultValue) {
        modStorage.addProperty(name, new EnumModProperty<T>(name, defaultValue));
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
