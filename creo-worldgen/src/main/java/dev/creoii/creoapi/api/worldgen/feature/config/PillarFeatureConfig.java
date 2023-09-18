package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record PillarFeatureConfig(BlockStateProvider state, IntProvider radius) implements FeatureConfig {
    public static final Codec<PillarFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(config -> {
            return config.radius;
        })).apply(instance, PillarFeatureConfig::new);
    });
}