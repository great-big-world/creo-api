package dev.creoii.creoapi.api.worldgen;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.noise.NoiseConfig;

public record CreoDensityFunctionVisitor(NoiseConfig noiseConfig) implements DensityFunction.DensityFunctionVisitor {
    @Override
    public DensityFunction apply(DensityFunction densityFunction) {
        return densityFunction;
    }

    @Override
    public DensityFunction.Noise apply(DensityFunction.Noise noiseDensityFunction) {
        RegistryEntry<DoublePerlinNoiseSampler.NoiseParameters> noiseEntry = noiseDensityFunction.noiseData();
        return new DensityFunction.Noise(noiseEntry, noiseConfig.getOrCreateSampler(noiseEntry.getKey().orElseThrow()));
    }
}
