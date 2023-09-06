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
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class NoisePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NoisePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryKey.createCodec(RegistryKeys.NOISE_PARAMETERS).fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.optionalFieldOf("min", -1d).forGetter(predicate -> {
            return predicate.min;
        }), Codec.DOUBLE.optionalFieldOf("max", 1d).forGetter(predicate -> {
            return predicate.max;
        })).apply(instance, NoisePlacementModifier::new);
    });
    private final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise;
    private final double min;
    private final double max;

    public NoisePlacementModifier(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, double min, double max) {
        this.noise = noise;
        this.min = min;
        this.max = max;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.NOISE;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (context.getWorld().getChunkManager() instanceof ServerChunkManager chunkManager) {
            DoublePerlinNoiseSampler sampler = chunkManager.getNoiseConfig().getOrCreateSampler(noise);
            double noiseValue = sampler.sample(pos.getX(), pos.getY(), pos.getZ());
            return noiseValue >= min && noiseValue < max;
        }
        return false;
    }
}