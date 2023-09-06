package dev.creoii.creoapi.api.worldgen.structureplacement;

import dev.creoii.creoapi.impl.worldgen.util.ExtraAware;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;

import java.util.Optional;

public abstract class ExtraAwareStructurePlacement extends RandomSpreadStructurePlacement implements ExtraAware {
    private ChunkGenerator chunkGenerator;
    private DynamicRegistryManager registryManager;

    public ExtraAwareStructurePlacement(Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
    }

    public ExtraAwareStructurePlacement(int spacing, int separation, SpreadType spreadType, int salt) {
        super(spacing, separation, spreadType, salt);
    }

    @Override
    public ChunkGenerator creo_getChunkGenerator() {
        return chunkGenerator;
    }

    @Override
    public void creo_setChunkGenerator(ChunkGenerator chunkGenerator) {
        this.chunkGenerator = chunkGenerator;
    }

    @Override
    public DynamicRegistryManager creo_getRegistryManager() {
        return registryManager;
    }

    @Override
    public void creo_setRegistryManager(DynamicRegistryManager chunkGenerator) {
        this.registryManager = chunkGenerator;
    }
}
