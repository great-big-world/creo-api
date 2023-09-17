package dev.creoii.creoapi.impl.worldgen.util;

import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.HashMap;
import java.util.Map;

public final class DensityFunctionCache {
    public static final Map<Long, NoiseConfig> CACHED_NOISE_CONFIGS = new HashMap<>();
}
