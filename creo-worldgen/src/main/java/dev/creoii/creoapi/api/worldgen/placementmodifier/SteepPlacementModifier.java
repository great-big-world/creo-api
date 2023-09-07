package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class SteepPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<SteepPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.intRange(1, 16).fieldOf("min_steepness").orElse(4).forGetter(predicate -> {
            return predicate.minSteepness;
        }), Codec.intRange(1, 16).fieldOf("max_steepness").orElse(4).forGetter(predicate -> {
            return predicate.maxSteepness;
        })).apply(instance, SteepPlacementModifier::new);
    });
    private final int minSteepness;
    private final int maxSteepness;

    public SteepPlacementModifier(int minSteepness, int maxSteepness) {
        this.minSteepness = minSteepness;
        this.maxSteepness = Math.max(maxSteepness, minSteepness);
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.STEEP;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        int x = pos.getX() & 0xf;
        int z = pos.getZ() & 0xf;
        Chunk chunk = context.getWorld().getChunk(pos);

        int max = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, x, Math.max(z - 1, 0));
        int min = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, x, Math.min(z + 1, 15));
        int steepness = Math.abs(min - max);
        if (steepness >= minSteepness && steepness <= maxSteepness) {
            return true;
        }

        max = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, Math.max(x - 1, 0), z);
        min = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, Math.min(x + 1, 15), z);
        steepness = Math.abs(max - min);
        if (steepness >= minSteepness && steepness <= maxSteepness)
            return true;
        return false;
    }
}
