package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.feature.CompositeFeature;
import dev.creoii.creoapi.api.worldgen.feature.StructureFeature;
import dev.creoii.creoapi.api.worldgen.feature.config.CompositeFeatureConfig;
import dev.creoii.creoapi.api.worldgen.feature.config.StructureFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public final class CreoFeatures {
    public static final Feature<CompositeFeatureConfig> COMPOSITE = new CompositeFeature(CompositeFeatureConfig.CODEC);
    public static final Feature<StructureFeatureConfig> STRUCTURE = new StructureFeature(StructureFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "composite"), COMPOSITE);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "structure"), STRUCTURE);
    }
}
