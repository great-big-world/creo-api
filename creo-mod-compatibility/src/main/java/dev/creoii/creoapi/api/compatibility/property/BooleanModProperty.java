package dev.creoii.creoapi.api.compatibility.property;

import dev.creoii.creoapi.api.compatibility.storage.ModStorage;

public class BooleanModProperty extends ModProperty<Boolean> {
    public boolean value;

    private BooleanModProperty(String name) {
        super(name);
    }

    private BooleanModProperty(String name, boolean defaultValue) {
        this(name);
        value = defaultValue;
    }

    public static void addBooleanProperty(ModStorage modStorage, String name) {
        modStorage.addProperty(name, new BooleanModProperty(name));
    }

    public static void addBooleanProperty(ModStorage modStorage, String name, boolean defaultValue) {
        modStorage.addProperty(name, new BooleanModProperty(name, defaultValue));
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
