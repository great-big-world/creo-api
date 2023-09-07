package dev.creoii.creoapi.impl.worldgen.util;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public interface AwareNoiseConfig {
    ServerWorld creo_getWorld();

    void creo_setWorld(ServerWorld serverWorld);

    ChunkGenerator creo_getChunkGenerator();

    void creo_setChunkGenerator(ChunkGenerator chunkGenerator);
}
