package dev.creoii.creoapi.api.compatibility.property;

import dev.creoii.creoapi.api.compatibility.storage.ModStorage;

public class StringModProperty extends ModProperty<String> {
    public String value;

    private StringModProperty(String name) {
        super(name);
    }

    private StringModProperty(String name, String defaultValue) {
        this(name);
        value = defaultValue;
    }

    public static void addStringProperty(ModStorage modStorage, String name) {
        modStorage.addProperty(name, new StringModProperty(name));
    }

    public static void addStringProperty(ModStorage modStorage, String name, String defaultValue) {
        modStorage.addProperty(name, new StringModProperty(name, defaultValue));
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
