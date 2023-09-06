package dev.creoii.creoapi.mixin.worldgen;

import dev.creoii.creoapi.impl.worldgen.StructurePlacementExtension;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StructurePlacement.class)
public class StructurePlacementMixin implements StructurePlacementExtension {
    @Unique private ChunkGenerator creo_chunkGenerator;
    @Unique private DynamicRegistryManager creo_registryManager;

    @Override
    public ChunkGenerator creo_getChunkGenerator() {
        return creo_chunkGenerator;
    }

    @Override
    public void creo_setChunkGenerator(ChunkGenerator chunkGenerator) {
        this.creo_chunkGenerator = chunkGenerator;
    }

    @Override
    public DynamicRegistryManager creo_getRegistryManager() {
        return creo_registryManager;
    }

    @Override
    public void creo_setRegistryManager(DynamicRegistryManager chunkGenerator) {
        this.creo_registryManager = chunkGenerator;
    }
}
