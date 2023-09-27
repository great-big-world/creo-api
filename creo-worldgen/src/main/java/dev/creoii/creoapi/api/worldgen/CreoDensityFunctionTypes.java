package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.densityfunction.FastNoiseDensityFunction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class CreoDensityFunctionTypes {
    static void register() {
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "fast_noise"), FastNoiseDensityFunction.CODEC);
    }
}
