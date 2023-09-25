package dev.creoii.creoapi.api.worldgen.structure.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructurePlacementTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FixedStructurePlacement extends StructurePlacement {
    public static final Codec<FixedStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockPos.CODEC.listOf().fieldOf("positions").forGetter(placement -> {
            return placement.positions;
        })).and(buildCodec(instance)).apply(instance, FixedStructurePlacement::new);
    });
    private final List<BlockPos> positions;
    private final List<BlockPos> blacklist;

    public FixedStructurePlacement(List<BlockPos> positions, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, FrequencyReductionMethod.DEFAULT, 1f, 0, Optional.empty());
        this.positions = positions;
        blacklist = new LinkedList<>();
    }

    @Override
    public StructurePlacementType<?> getType() {
        return CreoStructurePlacementTypes.FIXED;
    }

    @Override
    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (positions.isEmpty())
            return false;

        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        for (BlockPos pos : positions) {
            if (blacklist.contains(pos))
                continue;
            long x = chunkPos.x * 16L;
            long z = chunkPos.z * 16L;
            boolean bl1 = pos.getX() >= x;
            boolean bl2 = pos.getX() <= x + 15;
            boolean bl3 = pos.getZ() >= z;
            boolean bl4 = pos.getZ() <= z + 15;
            if (bl1 && bl2 && bl3 && bl4) {
                blacklist.add(pos);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldGenerate(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        return isStartChunk(calculator, chunkX, chunkZ);
    }
}
