package dev.creoii.creoapi.api.compatibility;

import dev.creoii.creoapi.api.compatibility.storage.ModStorage;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CreoModCompatibility {
    public static final Logger LOGGER = LoggerFactory.getLogger(CreoModCompatibility.class);
    public static final RegistryKey<Registry<ModStorage>> MOD_STORAGE_KEY = RegistryKey.ofRegistry(new Identifier("creo", "mod_storage"));
    public static final Registry<ModStorage> MOD_STORAGE = FabricRegistryBuilder.createSimple(MOD_STORAGE_KEY).buildAndRegister();

    public static void registerModStorage(Identifier id, ModStorage modStorage) {
        Registry.register(CreoModCompatibility.MOD_STORAGE, id, modStorage);
    }
}
