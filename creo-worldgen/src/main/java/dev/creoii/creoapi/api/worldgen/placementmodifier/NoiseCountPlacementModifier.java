package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NoiseCountPlacementModifier extends PlacementModifier {
    public static final Codec<NoiseCountPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryKey.createCodec(RegistryKeys.NOISE_PARAMETERS).fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.fieldOf("multiplier").forGetter(predicate -> {
            return predicate.multiplier;
        }), Codec.DOUBLE.optionalFieldOf("min_threshold", Double.MIN_VALUE).forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.optionalFieldOf("max_threshold", Double.MAX_VALUE).forGetter(predicate -> {
            return predicate.maxThreshold;
        })).apply(instance, NoiseCountPlacementModifier::new);
    });
    private final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise;
    private final double multiplier;
    private final double minThreshold;
    private final double maxThreshold;

    public NoiseCountPlacementModifier(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, double multiplier, double minThreshold, double maxThreshold) {
        this.noise = noise;
        this.multiplier = multiplier;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.NOISE_COUNT;
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (context.getWorld().getChunkManager() instanceof ServerChunkManager chunkManager) {
            DoublePerlinNoiseSampler sampler = chunkManager.getNoiseConfig().getOrCreateSampler(noise);
            double noiseValue = sampler.sample(pos.getX(), pos.getY(), pos.getZ()) * multiplier;
            if (noiseValue >= minThreshold && noiseValue < maxThreshold)
                return IntStream.range(0, (int) (noiseValue * multiplier)).mapToObj(i -> pos);
        }
        return Stream.of();
    }
}
