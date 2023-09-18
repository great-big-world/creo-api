package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record SpikeFeatureConfig(BlockStateProvider state, int floorToCeilingSearchRange, IntProvider columnRadius, FloatProvider heightScale, float maxColumnRadiusToCaveHeightRatio, FloatProvider stalactiteBluntness, FloatProvider stalagmiteBluntness, FloatProvider windSpeed, int minRadiusForWind, float minBluntnessForWind) implements FeatureConfig {
    public static final Codec<SpikeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").orElse(30).forGetter(config -> {
            return config.floorToCeilingSearchRange;
        }), IntProvider.createValidatingCodec(1, 60).fieldOf("column_radius").forGetter(config -> {
            return config.columnRadius;
        }), FloatProvider.createValidatedCodec(0.0F, 20.0F).fieldOf("height_scale").forGetter(config -> {
            return config.heightScale;
        }), Codec.floatRange(0.1F, 1.0F).fieldOf("max_column_radius_to_cave_height_ratio").forGetter(config -> {
            return config.maxColumnRadiusToCaveHeightRatio;
        }), FloatProvider.createValidatedCodec(0.1F, 10.0F).fieldOf("stalactite_bluntness").forGetter(config -> {
            return config.stalactiteBluntness;
        }), FloatProvider.createValidatedCodec(0.1F, 10.0F).fieldOf("stalagmite_bluntness").forGetter(config -> {
            return config.stalagmiteBluntness;
        }), FloatProvider.createValidatedCodec(0.0F, 2.0F).fieldOf("wind_speed").forGetter(config -> {
            return config.windSpeed;
        }), Codec.intRange(0, 100).fieldOf("min_radius_for_wind").forGetter(config -> {
            return config.minRadiusForWind;
        }), Codec.floatRange(0.0F, 5.0F).fieldOf("min_bluntness_for_wind").forGetter(config -> {
            return config.minBluntnessForWind;
        })).apply(instance, SpikeFeatureConfig::new);
    });
}