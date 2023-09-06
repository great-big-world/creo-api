package dev.creoii.creoapi.impl.worldgen.util;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public interface ExtraAware {
    ChunkGenerator creo_getChunkGenerator();

    void creo_setChunkGenerator(ChunkGenerator chunkGenerator);

    DynamicRegistryManager creo_getRegistryManager();

    void creo_setRegistryManager(DynamicRegistryManager chunkGenerator);
}
