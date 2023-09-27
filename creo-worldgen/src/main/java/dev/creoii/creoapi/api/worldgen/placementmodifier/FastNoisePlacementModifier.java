package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class FastNoisePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<FastNoisePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(FastNoiseLite.REGISTRY_ENTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.optionalFieldOf("min_threshold", -1d).forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.optionalFieldOf("max_threshold", 1d).forGetter(predicate -> {
            return predicate.maxThreshold;
        })).apply(instance, FastNoisePlacementModifier::new);
    });
    private final RegistryEntry<FastNoiseLite> noise;
    private final double minThreshold;
    private final double maxThreshold;

    public FastNoisePlacementModifier(RegistryEntry<FastNoiseLite> noise, double minThreshold, double maxThreshold) {
        this.noise = noise;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.FAST_NOISE;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (!noise.hasKeyAndValue())
            return false;
        FastNoiseLite fastNoiseLite = noise.value().seed(context.getWorld().getSeed());
        double noiseValue = fastNoiseLite.getNoise(pos.getX(), pos.getY(), pos.getZ());
        return noiseValue >= minThreshold && noiseValue <= maxThreshold;
    }
}