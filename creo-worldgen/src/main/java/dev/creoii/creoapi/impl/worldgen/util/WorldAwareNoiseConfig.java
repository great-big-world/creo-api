package dev.creoii.creoapi.impl.worldgen.util;

import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface WorldAwareNoiseConfig {
    ServerWorld creo$getWorld();

    void creo$setWorld(ServerWorld serverWorld);
}
