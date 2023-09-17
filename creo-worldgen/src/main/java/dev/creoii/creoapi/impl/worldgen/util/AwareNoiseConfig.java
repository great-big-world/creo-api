package dev.creoii.creoapi.impl.worldgen.util;

import net.minecraft.server.world.ServerWorld;

public interface AwareNoiseConfig {
    ServerWorld creo_getWorld();

    void creo_setWorld(ServerWorld serverWorld);
}
