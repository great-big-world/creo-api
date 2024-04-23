package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.placementmodifier.FastNoisePlacementModifier;
import dev.creoii.creoapi.api.worldgen.placementmodifier.NoisePlacementModifier;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.Optional;

public record GiantLogFeatureConfig(BlockStateProvider outerState, BlockStateProvider innerState, IntProvider length, IntProvider endOffset, IntProvider radius, Optional<Either<NoisePlacementModifier, FastNoisePlacementModifier>> noisePlacementModifier) implements FeatureConfig {
    public static final Codec<GiantLogFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("outer_state").forGetter(config -> {
            return config.outerState;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("inner_state").orElse(SimpleBlockStateProvider.of(Blocks.AIR)).forGetter(config -> {
            return config.innerState;
        }), IntProvider.createValidatingCodec(0, 32).fieldOf("length").forGetter(config -> {
            return config.length;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("end_offset").forGetter(config -> {
            return config.endOffset;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("radius").forGetter(config -> {
            return config.radius;
        }), Codec.either(NoisePlacementModifier.CODEC, FastNoisePlacementModifier.CODEC).optionalFieldOf("noise_modifier").orElse(Optional.empty()).forGetter(config -> {
            return config.noisePlacementModifier;
        })).apply(instance, GiantLogFeatureConfig::new);
    });
}
