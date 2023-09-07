package dev.creoii.creoapi.api.worldgen.materialrule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class SteepMaterialCondition implements MaterialRules.MaterialCondition {
    public static final Codec<SteepMaterialCondition> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.intRange(1, 16).fieldOf("min_steepness").forGetter(predicate -> {
            return predicate.minSteepness;
        }), Codec.intRange(1, 16).fieldOf("max_steepness").forGetter(predicate -> {
            return predicate.maxSteepness;
        })).apply(instance, SteepMaterialCondition::new);
    });
    public static final CodecHolder<SteepMaterialCondition> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final int minSteepness;
    private final int maxSteepness;

    public SteepMaterialCondition(int minSteepness, int maxSteepness) {
        this.minSteepness = minSteepness;
        this.maxSteepness = Math.max(maxSteepness, minSteepness);
    }

    @Override
    public CodecHolder<? extends MaterialRules.MaterialCondition> codec() {
        return CODEC_HOLDER;
    }

    @Override
    public MaterialRules.BooleanSupplier apply(final MaterialRules.MaterialRuleContext context) {
        int x = context.blockX & 0xf;
        int z = context.blockZ & 0xf;
        Chunk chunk = context.chunk;

        int max = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, x, Math.max(z - 1, 0));
        int min = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, x, Math.min(z + 1, 15));
        int steepness = Math.abs(min - max);
        if (steepness >= minSteepness && steepness <= maxSteepness) {
            return () -> true;
        }

        max = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, Math.max(x - 1, 0), z);
        min = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, Math.min(x + 1, 15), z);
        steepness = Math.abs(max - min);
        if (steepness >= minSteepness && steepness <= maxSteepness)
            return () -> true;
        return () -> false;
    }
}