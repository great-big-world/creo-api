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
        return instance.group(Codec.intRange(1, 16).fieldOf("steepness").orElse(4).forGetter(predicate -> {
            return predicate.steepness;
        })).apply(instance, SteepPlacementModifier::new);
    });
    private final int steepness;

    public SteepPlacementModifier(int steepness) {
        this.steepness = steepness;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.STEEP;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        int i = pos.getX() & 0xf;
        int j = pos.getZ() & 0xf;
        int k = Math.max(j - 1, 0);
        int l = Math.min(j + 1, 15);
        Chunk chunk = context.getWorld().getChunk(pos);
        int n = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, i, l);
        if (n >= chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, i, k) + steepness) {
            return true;
        }
        k = Math.max(i - 1, 0);
        l = Math.min(i + 1, 15);
        n = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, k, j);
        return n >= chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, l, j) + steepness;
    }
}
