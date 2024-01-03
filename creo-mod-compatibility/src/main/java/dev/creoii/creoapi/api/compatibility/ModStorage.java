package dev.creoii.creoapi.api.compatibility;

import com.google.common.collect.Sets;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

public class ModStorage {
    private final String modId;
    private final Set<ModProperty<?>> properties;
    private final File file;

    public ModStorage(String modId) {
        this.modId = modId;
        properties = Sets.newHashSet();
        file = new File(MinecraftClient.getInstance().runDirectory + "/modstorage/", modId + ".txt");

        try {
            if (!file.getParentFile().exists())
                Files.createDirectories(file.getParentFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getModId() {
        return modId;
    }

    public Set<ModProperty<?>> getProperties() {
        return properties;
    }

    public void reset() {
        properties.forEach(ModProperty::reset);
    }

    public void save() {
        if (properties.isEmpty())
            return;
        try {
            if (!file.exists()) {
                Files.createFile(file.toPath());
            }
            final FileWriter fileWriter = new FileWriter(file);
            try {
                for (ModProperty<?> property : properties) {
                    fileWriter.write(property.getName() + "=" + property.getValue() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Couldn't save mod storage file for mod " + modId);
                e.printStackTrace();
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Couldn't save mod storage file for mod " + modId);
            e.printStackTrace();
        }
    }

    public <T extends Comparable<T>> void addProperty(ModProperty<T> property) {
        if (properties.contains(property))
            CreoModCompatibility.LOGGER.error("Property " + property.getName() + " already exists in storage " + CreoModCompatibility.MOD_STORAGE.getId(this));
        properties.add(property);
        save();
    }

    public <T extends Comparable<T>> void addProperty(String name, T initialValue, @Nullable T defaultValue) {
        addProperty(new ModProperty<>(name, initialValue, defaultValue));
    }

    public <T extends Comparable<T>> void addProperty(String name, T initialValue) {
        addProperty(name, initialValue, null);
    }

    @Nullable
    public ModProperty<?> getProperty(String propertyName) {
        for (ModProperty<?> property : properties) {
            if (property.getName().equals(propertyName))
                return property;
        }
        CreoModCompatibility.LOGGER.error("Property " + propertyName + " not found in storage " + CreoModCompatibility.MOD_STORAGE.getId(this));
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void setProperty(String propertyName, T value, boolean save) {
        ModProperty<T> property = (ModProperty<T>) getProperty(propertyName);
        if (property != null) {
            property.setValue(value);

            if (save)
                save();
        }
    }

    public <T extends Comparable<T>> void setProperty(String propertyName, T value) {
        setProperty(propertyName, value, true);
    }
}
