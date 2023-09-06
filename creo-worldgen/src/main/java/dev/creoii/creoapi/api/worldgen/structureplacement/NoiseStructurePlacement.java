package dev.creoii.creoapi.api.worldgen.structureplacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructurePlacementTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.Optional;

public class NoiseStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<NoiseStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryKey.createCodec(RegistryKeys.NOISE_PARAMETERS).fieldOf("noise_parameters").forGetter(predicate -> {
            return predicate.noise;
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
        }), Codec.intRange(0, 4096).fieldOf("spacing").forGetter(RandomSpreadStructurePlacement::getSpacing), Codec.intRange(0, 4096).fieldOf("separation").forGetter(RandomSpreadStructurePlacement::getSeparation), SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::getSpreadType)).apply(instance, NoiseStructurePlacement::new);
    });
    private final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise;
    private final double min;
    private final double max;

    public NoiseStructurePlacement(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, double min, double max, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.noise = noise;
        this.min = min;
        this.max = max;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return CreoStructurePlacementTypes.NOISE;
    }

    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        ChunkPos chunkPos = getStartChunk(calculator.getStructureSeed(), chunkX, chunkZ);
        DoublePerlinNoiseSampler sampler = calculator.getNoiseConfig().getOrCreateSampler(noise);
        BlockPos pos = getLocatePos(new ChunkPos(chunkX, chunkZ));
        double noiseValue = sampler.sample(pos.getX(), 0d, pos.getZ());
        if (noiseValue >= min && noiseValue < max) {
            return chunkPos.x == chunkX && chunkPos.z == chunkZ;
        }
        return false;
    }

    @Override
    public BlockPos getLocatePos(ChunkPos chunkPos) {
        return chunkPos.getStartPos().add(getLocateOffset());
    }
}
