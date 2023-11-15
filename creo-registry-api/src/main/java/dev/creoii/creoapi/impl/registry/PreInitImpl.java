package dev.creoii.creoapi.impl.registry;

import dev.creoii.creoapi.api.registry.init.PreInitModInitializer;
import net.fabricmc.loader.impl.FabricLoaderImpl;

public final class PreInitImpl {
    public static void applyPreInit(boolean client) {
        FabricLoaderImpl.INSTANCE.invokeEntrypoints("pre-init", PreInitModInitializer.class, preInitModInitializer -> preInitModInitializer.onPreInitialize(client));
    }
}
