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
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.HashMap;
import java.util.Map;

public class DensityFunctionPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<DensityFunctionPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(DensityFunction.REGISTRY_ENTRY_CODEC.fieldOf("density_function").forGetter(predicate -> {
            return predicate.densityFunction;
        }), Codec.DOUBLE.optionalFieldOf("min", -1d).forGetter(predicate -> {
            return predicate.min;
        }), Codec.DOUBLE.optionalFieldOf("max", 1d).forGetter(predicate -> {
            return predicate.max;
        })).apply(instance, DensityFunctionPlacementModifier::new);
    });
    protected static final Map<Long, NoiseConfig> CACHED_NOISE_CONFIGS = new HashMap<>();
    private final RegistryEntry<DensityFunction> densityFunction;
    private final double min;
    private final double max;

    public DensityFunctionPlacementModifier(RegistryEntry<DensityFunction> densityFunction, double min, double max) {
        this.densityFunction = densityFunction;
        this.min = min;
        this.max = max;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.DENSITY_FUNCTION;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        StructureWorldAccess world = context.getWorld();
        if (!densityFunction.hasKeyAndValue() || world.isClient()) return false;

        if (!CACHED_NOISE_CONFIGS.containsKey(world.getSeed())) {
            ChunkGeneratorSettings settings = context.getChunkGenerator() instanceof NoiseChunkGenerator noiseChunkGenerator ? noiseChunkGenerator.getSettings().value() : ChunkGeneratorSettings.createMissingSettings();
            CACHED_NOISE_CONFIGS.put(world.getSeed(), NoiseConfig.create(settings, world.getRegistryManager().getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS), world.getSeed()));
        }

        double value = densityFunction.value().apply(new CreoDensityFunctionVisitor(CACHED_NOISE_CONFIGS.get(world))).sample(new DensityFunction.UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
        return value >= min && value < max;
    }
}
