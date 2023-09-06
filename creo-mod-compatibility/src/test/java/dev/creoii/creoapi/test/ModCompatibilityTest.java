package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.compatibility.CreoModCompatibility;
import dev.creoii.creoapi.api.compatibility.storage.ModStorage;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ModCompatibilityTest implements ModInitializer {
    @Override
    public void onInitialize() {
        ModStorage modStorage = new ModStorage("test");
        CreoModCompatibility.registerModStorage(new Identifier("test", "test"), modStorage);
    }
}
