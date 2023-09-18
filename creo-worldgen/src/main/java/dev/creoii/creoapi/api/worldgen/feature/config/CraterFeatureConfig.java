package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CraterFeatureConfig(IntProvider radius, IntProvider depth) implements FeatureConfig {
    public static final Codec<CraterFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(config -> {
            return config.radius;
        }), IntProvider.VALUE_CODEC.fieldOf("depth").forGetter(config -> {
            return config.depth;
        })).apply(instance, CraterFeatureConfig::new);
    });
}
