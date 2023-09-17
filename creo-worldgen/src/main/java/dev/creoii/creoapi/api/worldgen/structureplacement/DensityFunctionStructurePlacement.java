package dev.creoii.creoapi.api.worldgen.structureplacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructurePlacementTypes;
import dev.creoii.creoapi.impl.worldgen.util.DensityFunctionCache;
import dev.creoii.creoapi.impl.worldgen.util.WorldAwareNoiseConfig;
import dev.creoii.creoapi.impl.worldgen.util.CreoDensityFunctionVisitor;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DensityFunctionStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<DensityFunctionStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(DensityFunction.REGISTRY_ENTRY_CODEC.fieldOf("density_function").forGetter(predicate -> {
            return predicate.densityFunction;
        }), Codec.DOUBLE.fieldOf("min").forGetter(predicate -> {
            return predicate.min;
        }), Codec.DOUBLE.fieldOf("max").forGetter(predicate -> {
            return predicate.max;
        }), Vec3i.createOffsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(predicate -> {
            return predicate.getLocateOffset();
        }), FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(predicate -> {
            return predicate.getFrequencyReductionMethod();
        }), Codec.floatRange(0f, 1f).optionalFieldOf("frequency", 1f).forGetter(predicate -> {
            return predicate.getFrequency();
        }), Codecs.NONNEGATIVE_INT.fieldOf("salt").forGetter(predicate -> {
            return predicate.getSalt();
        }), ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(predicate -> {
            return predicate.getExclusionZone();
        }), Codec.intRange(0, 4096).fieldOf("spacing").forGetter(RandomSpreadStructurePlacement::getSpacing), Codec.intRange(0, 4096).fieldOf("separation").forGetter(RandomSpreadStructurePlacement::getSeparation), SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::getSpreadType)).apply(instance, DensityFunctionStructurePlacement::new);
    });
    private final RegistryEntry<DensityFunction> densityFunction;
    private final double min;
    private final double max;

    public DensityFunctionStructurePlacement(RegistryEntry<DensityFunction> densityFunction, double min, double max, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.densityFunction = densityFunction;
        this.min = min;
        this.max = max;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return CreoStructurePlacementTypes.DENSITY_FUNCTION;
    }

    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (!densityFunction.hasKeyAndValue())
            return false;

        long seed = ((WorldAwareNoiseConfig) calculator.getNoiseConfig()).creo_getWorld().getSeed();
        if (!DensityFunctionCache.CACHED_NOISE_CONFIGS.containsKey(seed)) {
            ChunkGenerator chunkGenerator = ((WorldAwareNoiseConfig) calculator.getNoiseConfig()).creo_getWorld().getChunkManager().getChunkGenerator();
            ChunkGeneratorSettings settings = chunkGenerator instanceof NoiseChunkGenerator noiseChunkGenerator ? noiseChunkGenerator.getSettings().value() : ChunkGeneratorSettings.createMissingSettings();
            DensityFunctionCache.CACHED_NOISE_CONFIGS.put(seed, NoiseConfig.create(settings, ((WorldAwareNoiseConfig) calculator.getNoiseConfig()).creo_getWorld().getRegistryManager().getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS), seed));
        }

        BlockPos pos = getLocatePos(new ChunkPos(chunkX, chunkZ));
        double value = densityFunction.value().apply(new CreoDensityFunctionVisitor(DensityFunctionCache.CACHED_NOISE_CONFIGS.get(seed))).sample(new DensityFunction.UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
        return value >= min && value < max;
    }

    @Override
    public BlockPos getLocatePos(ChunkPos chunkPos) {
        return chunkPos.getStartPos().add(getLocateOffset());
    }
}
