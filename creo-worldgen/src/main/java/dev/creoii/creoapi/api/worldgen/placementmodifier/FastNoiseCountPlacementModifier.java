package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FastNoiseCountPlacementModifier extends PlacementModifier {
    public static final Codec<FastNoiseCountPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(FastNoiseLite.REGISTRY_ENTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.fieldOf("multiplier").forGetter(predicate -> {
            return predicate.multiplier;
        }), Codec.DOUBLE.optionalFieldOf("min_threshold", -1d).forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.optionalFieldOf("max_threshold", 1d).forGetter(predicate -> {
            return predicate.maxThreshold;
        })).apply(instance, FastNoiseCountPlacementModifier::new);
    });
    private final RegistryEntry<FastNoiseLite> noise;
    private final double multiplier;
    private final double minThreshold;
    private final double maxThreshold;

    public FastNoiseCountPlacementModifier(RegistryEntry<FastNoiseLite> noise, double multiplier, double minThreshold, double maxThreshold) {
        this.noise = noise;
        this.multiplier = multiplier;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.FAST_NOISE;
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (!noise.hasKeyAndValue())
            return Stream.of(pos);
        FastNoiseLite fastNoiseLite = noise.value().seed(context.getWorld().getSeed());
        double noiseValue = fastNoiseLite.getNoise(pos.getX(), pos.getY(), pos.getZ()) * multiplier;
        if (noiseValue >= minThreshold && noiseValue < maxThreshold)
            return IntStream.range(0, (int) (noiseValue * multiplier)).mapToObj(i -> pos);
        return Stream.of();
    }
}