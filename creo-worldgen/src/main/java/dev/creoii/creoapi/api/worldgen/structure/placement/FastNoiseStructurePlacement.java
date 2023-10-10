package dev.creoii.creoapi.api.worldgen.structure.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructurePlacementTypes;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseParameters;
import dev.creoii.creoapi.impl.worldgen.util.WorldAwareNoiseConfig;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.Optional;

public class FastNoiseStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<FastNoiseStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(FastNoiseParameters.REGISTRY_ENTRY_CODEC.fieldOf("fast_noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.fieldOf("min_threshold").forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.fieldOf("max_threshold").forGetter(predicate -> {
            return predicate.maxThreshold;
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
        }), Codec.intRange(0, 4096).fieldOf("spacing").forGetter(RandomSpreadStructurePlacement::getSpacing), Codec.intRange(0, 4096).fieldOf("separation").forGetter(RandomSpreadStructurePlacement::getSeparation), SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::getSpreadType)).apply(instance, FastNoiseStructurePlacement::new);
    });
    private final RegistryEntry<FastNoiseParameters> noise;
    private final double minThreshold;
    private final double maxThreshold;

    public FastNoiseStructurePlacement(RegistryEntry<FastNoiseParameters> noise, double minThreshold, double maxThreshold, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.noise = noise;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return CreoStructurePlacementTypes.FAST_NOISE;
    }

    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (!noise.hasKeyAndValue())
            return false;
        ChunkPos chunkPos = getStartChunk(calculator.getStructureSeed(), chunkX, chunkZ);
        BlockPos pos = getLocatePos(new ChunkPos(chunkX, chunkZ));
        FastNoiseLite fastNoiseLite = new FastNoiseLite(noise.value());
        if (noise.value().seed() == 1337L)
            fastNoiseLite.seed(((WorldAwareNoiseConfig) calculator.getNoiseConfig()).creo_getWorld().getSeed());
        double noiseValue = fastNoiseLite.getNoise(pos.getX(), 0f, pos.getZ());
        if (noiseValue >= minThreshold && noiseValue < maxThreshold) {
            return chunkPos.x == chunkX && chunkPos.z == chunkZ;
        }
        return false;
    }

    @Override
    public BlockPos getLocatePos(ChunkPos chunkPos) {
        return chunkPos.getStartPos().add(getLocateOffset());
    }
}
