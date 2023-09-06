package dev.creoii.creoapi.api.compatibility.storage;

import com.google.common.collect.Maps;
import dev.creoii.creoapi.api.compatibility.CreoModCompatibility;
import dev.creoii.creoapi.api.compatibility.property.ModProperty;
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

    @Nullable
    public ModProperty<?> getProperty(String propertyName) {
        return properties.get(propertyName);
    }
}
