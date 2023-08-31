package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CPlacementModifierTypes;
import dev.creoii.creoapi.api.worldgen.CreoDensityFunctionVisitor;
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
        }), Codec.DOUBLE.optionalFieldOf("min", Double.MIN_VALUE).forGetter(predicate -> {
            return predicate.min;
        }), Codec.DOUBLE.optionalFieldOf("max", Double.MAX_VALUE).forGetter(predicate -> {
            return predicate.max;
        })).apply(instance, DensityFunctionCountPlacementModifier::new);
    });
    private final RegistryEntry<DensityFunction> densityFunction;
    private final double multiplier;
    private final double min;
    private final double max;

    public DensityFunctionCountPlacementModifier(RegistryEntry<DensityFunction> densityFunction, double multiplier, double min, double max) {
        this.densityFunction = densityFunction;
        this.multiplier = multiplier;

        if (max == Double.MAX_VALUE && densityFunction.hasKeyAndValue())
            this.max = densityFunction.value().maxValue();
        else this.max = max;

        if (min == Double.MIN_VALUE && densityFunction.hasKeyAndValue())
            this.min = densityFunction.value().minValue();
        else this.min = min;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CPlacementModifierTypes.DENSITY_FUNCTION_COUNT;
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        StructureWorldAccess world = context.getWorld();
        if (!densityFunction.hasKeyAndValue() || world.isClient()) return Stream.of();

        if (!DensityFunctionPlacementModifier.CACHED_NOISE_CONFIGS.containsKey(world)) {
            ChunkGeneratorSettings settings = context.getChunkGenerator() instanceof NoiseChunkGenerator noiseChunkGenerator ? noiseChunkGenerator.getSettings().value() : ChunkGeneratorSettings.createMissingSettings();
            DensityFunctionPlacementModifier.CACHED_NOISE_CONFIGS.put(world, NoiseConfig.create(settings, world.getRegistryManager().getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS), world.getSeed()));
        }

        double value = densityFunction.value().apply(new CreoDensityFunctionVisitor(DensityFunctionPlacementModifier.CACHED_NOISE_CONFIGS.get(world))).sample(new DensityFunction.UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
        if (value >= min && value < max)
            return IntStream.range(0, (int) (value * multiplier)).mapToObj(i -> pos);
        return Stream.of();
    }
}
