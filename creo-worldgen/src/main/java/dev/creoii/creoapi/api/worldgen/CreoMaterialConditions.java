package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseParameters;
import dev.creoii.creoapi.api.worldgen.materialrule.DensityFunctionMaterialCondition;
import dev.creoii.creoapi.api.worldgen.materialrule.ExposedMaterialCondition;
import dev.creoii.creoapi.api.worldgen.materialrule.FastNoiseMaterialCondition;
import dev.creoii.creoapi.api.worldgen.materialrule.SteepMaterialCondition;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public final class CreoMaterialConditions {
    static void register() {
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "steep"), SteepMaterialCondition.CODEC_HOLDER.codec());
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "density_function"), DensityFunctionMaterialCondition.CODEC_HOLDER.codec());
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "fast_noise"), FastNoiseMaterialCondition.CODEC_HOLDER.codec());
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "exposed"), ExposedMaterialCondition.CODEC_HOLDER.codec());
    }

    public static MaterialRules.MaterialCondition steep(int minSteepness, int maxSteepness) {
        return new SteepMaterialCondition(minSteepness, maxSteepness);
    }

    public static MaterialRules.MaterialCondition steep(int steepness) {
        return new SteepMaterialCondition(steepness, steepness);
    }

    public static MaterialRules.MaterialCondition densityFunction(RegistryEntry<DensityFunction> densityFunction, double minThreshold, double maxThreshold) {
        return new DensityFunctionMaterialCondition(densityFunction, minThreshold, maxThreshold);
    }

    public static MaterialRules.MaterialCondition fastNoise(RegistryEntry<FastNoiseParameters> noise, double minThreshold, double maxThreshold) {
        return new FastNoiseMaterialCondition(noise, minThreshold, maxThreshold);
    }

    public static MaterialRules.MaterialCondition exposed(RegistryEntryList<Block> canBeExposedTo, int minExposedFaces, Direction.Type axis) {
        return new ExposedMaterialCondition(canBeExposedTo, minExposedFaces, axis.name());
    }
}
