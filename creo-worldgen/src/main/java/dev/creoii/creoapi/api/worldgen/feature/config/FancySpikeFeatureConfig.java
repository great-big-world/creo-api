package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record FancySpikeFeatureConfig(BlockStateProvider state, IntProvider baseHeight, IntProvider extraHeight, float extraHeightChance) implements FeatureConfig {
    public static final Codec<FancySpikeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), IntProvider.POSITIVE_CODEC.fieldOf("base_height").forGetter(config -> {
            return config.baseHeight;
        }), IntProvider.POSITIVE_CODEC.fieldOf("extra_height").forGetter(config -> {
            return config.extraHeight;
        }), Codec.FLOAT.fieldOf("extra_height_chance").forGetter(config -> {
            return config.extraHeightChance;
        })).apply(instance, FancySpikeFeatureConfig::new);
    });
}
