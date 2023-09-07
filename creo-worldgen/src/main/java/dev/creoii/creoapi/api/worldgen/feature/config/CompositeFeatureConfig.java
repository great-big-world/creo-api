package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.feature.CompositeFeature;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public record CompositeFeatureConfig(List<RegistryEntry<PlacedFeature>> features, CompositeFeature.FailType failType) implements FeatureConfig {
    public static final Codec<CompositeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacedFeature.REGISTRY_CODEC.listOf().fieldOf("features").forGetter(config -> {
            return config.features;
        }), CompositeFeature.FailType.CODEC.fieldOf("fail_type").orElse(CompositeFeature.FailType.FREE).forGetter(config -> {
            return config.failType;
        })).apply(instance, CompositeFeatureConfig::new);
    });
}
