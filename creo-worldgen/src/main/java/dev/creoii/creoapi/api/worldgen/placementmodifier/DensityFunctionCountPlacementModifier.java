package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.impl.worldgen.util.CreoDensityFunctionVisitor;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DensityFunctionCountPlacementModifier extends PlacementModifier {
    public static final Codec<DensityFunctionCountPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(DensityFunction.REGISTRY_ENTRY_CODEC.fieldOf("density_function").forGetter(predicate -> {
            return predicate.densityFunction;
        }), Codec.DOUBLE.fieldOf("multiplier").forGetter(predicate -> {
            return predicate.multiplier;
        }), Codec.DOUBLE.optionalFieldOf("min_threshold", Double.MIN_VALUE).forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.optionalFieldOf("max_threshold", Double.MAX_VALUE).forGetter(predicate -> {
            return predicate.maxThreshold;
        })).apply(instance, DensityFunctionCountPlacementModifier::new);
    });
    private final RegistryEntry<DensityFunction> densityFunction;
    private final double multiplier;
    private final double minThreshold;
    private final double maxThreshold;

    public DensityFunctionCountPlacementModifier(RegistryEntry<DensityFunction> densityFunction, double multiplier, double minThreshold, double maxThreshold) {
        this.densityFunction = densityFunction;
        this.multiplier = multiplier;

        if (maxThreshold == Double.MAX_VALUE && densityFunction.hasKeyAndValue())
            this.maxThreshold = densityFunction.value().maxValue();
        else this.maxThreshold = maxThreshold;

        if (minThreshold == Double.MIN_VALUE && densityFunction.hasKeyAndValue())
            this.minThreshold = densityFunction.value().minValue();
        else this.minThreshold = minThreshold;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.DENSITY_FUNCTION_COUNT;
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        StructureWorldAccess world = context.getWorld();
        if (!densityFunction.hasKeyAndValue() || world.isClient()) return Stream.of();

        long seed = world.getSeed();
        if (!DensityFunctionPlacementModifier.CACHED_NOISE_CONFIGS.containsKey(seed)) {
            ChunkGeneratorSettings settings = context.getChunkGenerator() instanceof NoiseChunkGenerator noiseChunkGenerator ? noiseChunkGenerator.getSettings().value() : ChunkGeneratorSettings.createMissingSettings();
            DensityFunctionPlacementModifier.CACHED_NOISE_CONFIGS.put(seed, NoiseConfig.create(settings, world.getRegistryManager().getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS), seed));
        }

        double value = densityFunction.value().apply(new CreoDensityFunctionVisitor(DensityFunctionPlacementModifier.CACHED_NOISE_CONFIGS.get(seed))).sample(new DensityFunction.UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
        if (value >= minThreshold && value < maxThreshold)
            return IntStream.range(0, (int) (value * multiplier)).mapToObj(i -> pos);
        return Stream.of();
    }
}
