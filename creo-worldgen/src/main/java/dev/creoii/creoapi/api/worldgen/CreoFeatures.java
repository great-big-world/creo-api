package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.feature.*;
import dev.creoii.creoapi.api.worldgen.feature.config.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;

public final class CreoFeatures {
    public static final Feature<CompositeFeatureConfig> COMPOSITE = new CompositeFeature(CompositeFeatureConfig.CODEC);
    public static final Feature<StructureFeatureConfig> STRUCTURE = new StructureFeature(StructureFeatureConfig.CODEC);
    public static final Feature<PoolFeatureConfig> POOL = new PoolFeature(PoolFeatureConfig.CODEC);
    public static final Feature<CraterFeatureConfig> CRATER = new CraterFeature(CraterFeatureConfig.CODEC);
    public static final Feature<PillarFeatureConfig> PILLAR = new PillarFeature(PillarFeatureConfig.CODEC);
    public static final Feature<RockFeatureConfig> ROCK = new RockFeature(RockFeatureConfig.CODEC);
    public static final Feature<SpikeFeatureConfig> SPIKE = new SpikeFeature(SpikeFeatureConfig.CODEC);
    public static final Feature<FancySpikeFeatureConfig> FANCY_SPIKE = new FancySpikeFeature(FancySpikeFeatureConfig.CODEC);
    public static final Feature<BlobSpreadFeatureConfig> BLOB_SPREAD = new BlobSpreadFeature(BlobSpreadFeatureConfig.CODEC);
    public static final Feature<SimpleBlockFeatureConfig> CORAL_CLAW = new CoralClawFeature(SimpleBlockFeatureConfig.CODEC);
    public static final Feature<SimpleBlockFeatureConfig> CORAL_MUSHROOM = new CoralMushroomFeature(SimpleBlockFeatureConfig.CODEC);
    public static final Feature<SimpleBlockFeatureConfig> CORAL_TREE = new CoralTreeFeature(SimpleBlockFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "composite"), COMPOSITE);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "structure"), STRUCTURE);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "pool"), POOL);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "crater"), CRATER);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "pillar"), PILLAR);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "rock"), ROCK);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "spike"), SPIKE);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "fancy_spike"), FANCY_SPIKE);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "blob_spread"), BLOB_SPREAD);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "coral_claw"), CORAL_CLAW);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "coral_mushroom"), CORAL_MUSHROOM);
        Registry.register(Registries.FEATURE, new Identifier(CreoWorldgen.NAMESPACE, "coral_tree"), CORAL_TREE);
    }
}
