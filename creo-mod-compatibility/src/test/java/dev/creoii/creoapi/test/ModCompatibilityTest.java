package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.compatibility.CreoModCompatibility;
import dev.creoii.creoapi.api.compatibility.ModStorage;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ModCompatibilityTest implements ModInitializer {
    @Override
    public void onInitialize() {
        ModStorage modStorage = new ModStorage("test");
        CreoModCompatibility.registerModStorage(new Identifier("test", "test"), modStorage);
        modStorage.addProperty("test_bool", true, true);
        modStorage.addProperty("test_int", 1, 1);

        System.err.println(modStorage.getProperty("test_bool").getValue());
        System.err.println(modStorage.getProperty("test_int").getValue());

        modStorage.setProperty("test_bool", false);

        modStorage.addProperty("test_string", "Hello, world!");

        System.err.println(modStorage.getProperty("test_bool").getValue());
        System.err.println(modStorage.getProperty("test_string").getValue());
    }
}
