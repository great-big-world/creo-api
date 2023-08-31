package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CPlacementModifierTypes;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.structure.Structure;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.Map;

public class NearStructurePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NearStructurePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.STRUCTURE).fieldOf("structure").forGetter(placement -> {
            return placement.structures;
        }), Codec.INT.fieldOf("distance").forGetter(placement -> {
            return placement.distance;
        })).apply(instance, NearStructurePlacementModifier::new);
    });
    private final RegistryEntryList<Structure> structures;
    private final int distance;
    private final int squaredDistanceBlocks;

    public NearStructurePlacementModifier(RegistryEntryList<Structure> structures, int distance) {
        this.structures = structures;
        this.distance = Math.min(4, distance);
        this.squaredDistanceBlocks = MathHelper.square(distance * 16);
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CPlacementModifierTypes.NEAR_STRUCTURE;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        MutableBoolean place = new MutableBoolean(false);
        ChunkPos.stream(new ChunkPos(pos), distance).forEach(chunkPos -> {
            if (shouldPlace(context.getWorld(), chunkPos, pos))
                place.setTrue();
        });
        return place.booleanValue();
    }

    public boolean shouldPlace(StructureWorldAccess world, ChunkPos chunkPos, BlockPos pos) {
        Map<Structure, LongSet> structureReferences = world.getChunk(pos).getStructureReferences();
        for (RegistryEntry<Structure> entry : structures) {
            if (!entry.hasKeyAndValue()) continue;
            if (structureReferences.containsKey(entry.value())) {
                if (distance <= squaredDistanceBlocks) {
                    return true;
                }
            }
        }
        return false;
    }
}