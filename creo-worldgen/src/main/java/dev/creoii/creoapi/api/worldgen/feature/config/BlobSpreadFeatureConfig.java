package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record BlobSpreadFeatureConfig(BlockState state, IntProvider tries, IntProvider xSpread, IntProvider ySpread, IntProvider zSpread) implements FeatureConfig {
    public static final Codec<BlobSpreadFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), IntProvider.POSITIVE_CODEC.fieldOf("tries").orElse(ConstantIntProvider.create(1500)).forGetter(config -> {
            return config.tries;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("x_spread").orElse(ConstantIntProvider.create(8)).forGetter(config -> {
            return config.xSpread;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("y_spread").orElse(ConstantIntProvider.create(12)).forGetter(config -> {
            return config.ySpread;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("z_spread").orElse(ConstantIntProvider.create(8)).forGetter(config -> {
            return config.zSpread;
        })).apply(instance, BlobSpreadFeatureConfig::new);
    });
}
