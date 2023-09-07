package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.materialrule.DensityFunctionMaterialCondition;
import dev.creoii.creoapi.api.worldgen.materialrule.FastNoiseMaterialCondition;
import dev.creoii.creoapi.api.worldgen.materialrule.SteepMaterialCondition;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class CreoMaterialConditions {
    public static void register() {
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "steep"), SteepMaterialCondition.CODEC_HOLDER.codec());
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "density_function"), DensityFunctionMaterialCondition.CODEC_HOLDER.codec());
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoWorldgen.NAMESPACE, "fast_noise"), FastNoiseMaterialCondition.CODEC_HOLDER.codec());
    }
}
