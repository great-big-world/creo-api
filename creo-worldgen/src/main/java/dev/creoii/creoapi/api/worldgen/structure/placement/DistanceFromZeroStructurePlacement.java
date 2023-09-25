package dev.creoii.creoapi.api.worldgen.structure.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructurePlacementTypes;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.math.BigDecimal;
import java.util.Optional;

public class DistanceFromZeroStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<DistanceFromZeroStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("min_squared_distance").forGetter(predicate -> {
            return predicate.minSquaredDistance;
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
        }), Codec.intRange(0, 4096).fieldOf("spacing").forGetter(RandomSpreadStructurePlacement::getSpacing), Codec.intRange(0, 4096).fieldOf("separation").forGetter(RandomSpreadStructurePlacement::getSeparation), SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::getSpreadType)).apply(instance, DistanceFromZeroStructurePlacement::new);
    });
    private final int minSquaredDistance;

    public DistanceFromZeroStructurePlacement(int minSquaredDistance, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.minSquaredDistance = minSquaredDistance;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return CreoStructurePlacementTypes.DISTANCE_FROM_ZERO;
    }

    @Override
    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        ChunkPos chunkPos = getStartChunk(calculator.getStructureSeed(), chunkX, chunkZ);
        BlockPos startPos = chunkPos.getStartPos();
        long squaredDistance = BigDecimal.valueOf(startPos.getSquaredDistance(0, startPos.getY(), 0)).longValue();
        if (squaredDistance >= minSquaredDistance) {
            return chunkPos.x == chunkX && chunkPos.z == chunkZ;
        } return false;
    }
}
