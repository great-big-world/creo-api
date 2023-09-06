package dev.creoii.creoapi.api.compatibility.storage;

import com.google.common.collect.Maps;
import dev.creoii.creoapi.api.compatibility.CreoModCompatibility;
import dev.creoii.creoapi.api.compatibility.property.*;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ModStorage {
    private final String modId;
    private final Map<String, ModProperty<?>> properties;

    public ModStorage(String modId) {
        this.modId = modId;
        properties = Maps.newHashMap();
    }

    public String getModId() {
        return modId;
    }

    public Map<String, ModProperty<?>> getProperties() {
        return properties;
    }

    public void addProperty(String propertyName, ModProperty<?> property) {
        if (properties.containsKey(propertyName))
            CreoModCompatibility.LOGGER.error("Property with name " + propertyName + " already exists in " + CreoModCompatibility.MOD_STORAGE.getId(this) + ".");
        properties.put(propertyName, property);
    }

    public void addBooleanProperty(String name) {
        addProperty(name, new BooleanModProperty(name));
    }

    public void addBooleanProperty(String name, boolean defaultValue) {
        addProperty(name, new BooleanModProperty(name, defaultValue));
    }

    public void addIntegerProperty(String name) {
        addProperty(name, new IntegerModProperty(name));
    }

    public void addIntegerProperty(String name, int defaultValue) {
        addProperty(name, new IntegerModProperty(name, defaultValue));
    }

    public void addIntegerProperty(String name, int defaultValue, int min, int max) {
        addProperty(name, new IntegerModProperty(name, defaultValue, min, max));
    }

    public void addDoubleProperty(String name) {
        addProperty(name, new DoubleModProperty(name));
    }

    public void addDoubleProperty( String name, double defaultValue) {
        addProperty(name, new DoubleModProperty(name, defaultValue));
    }

    public void addDoubleProperty(String name, double defaultValue, double min, double max) {
        addProperty(name, new DoubleModProperty(name, defaultValue, min, max));
    }

    public void addStringProperty(String name) {
        addProperty(name, new StringModProperty(name));
    }

    public void addStringProperty(String name, String defaultValue) {
        addProperty(name, new StringModProperty(name, defaultValue));
    }

    @Nullable
    public ModProperty<?> getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    @Nullable
    public BooleanModProperty getBooleanProperty(String propertyName) {
        ModProperty<?> property = getProperty(propertyName);
        if (property instanceof BooleanModProperty booleanModProperty)
            return booleanModProperty;
        return null;
    }

    @Nullable
    public IntegerModProperty getIntegerProperty(String propertyName) {
        ModProperty<?> property = getProperty(propertyName);
        if (property instanceof IntegerModProperty integerModProperty)
            return integerModProperty;
        return null;
    }

    @Nullable
    public DoubleModProperty getDoubleProperty(String propertyName) {
        ModProperty<?> property = getProperty(propertyName);
        if (property instanceof DoubleModProperty doubleModProperty)
            return doubleModProperty;
        return null;
    }

    @Nullable
    public StringModProperty getStringProperty(String propertyName) {
        ModProperty<?> property = getProperty(propertyName);
        if (property instanceof StringModProperty stringModProperty)
            return stringModProperty;
        return null;
    }
}
