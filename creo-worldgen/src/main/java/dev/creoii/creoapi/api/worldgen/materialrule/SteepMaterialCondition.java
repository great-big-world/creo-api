package dev.creoii.creoapi.api.worldgen.materialrule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class SteepMaterialCondition implements MaterialRules.MaterialCondition {
    public static final Codec<SteepMaterialCondition> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.intRange(1, 16).fieldOf("steepness").forGetter(predicate -> {
            return predicate.steepness;
        })).apply(instance, SteepMaterialCondition::new);
    });
    public static final CodecHolder<SteepMaterialCondition> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final int steepness;

    public SteepMaterialCondition(int steepness) {
        this.steepness = steepness;
    }

    @Override
    public CodecHolder<? extends MaterialRules.MaterialCondition> codec() {
        return CODEC_HOLDER;
    }

    @Override
    public MaterialRules.BooleanSupplier apply(final MaterialRules.MaterialRuleContext context) {
        int i = context.blockX & 0xf;
        int j = context.blockZ & 0xf;
        int k = Math.max(j - 1, 0);
        int l = Math.min(j + 1, 15);
        Chunk chunk = context.chunk;
        int n = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, i, l);
        if (n >= chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, i, k) + steepness) {
            return () -> true;
        }
        int o = Math.max(i - 1, 0);
        int p = Math.min(i + 1, 15);
        int q = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, o, j);
        return () -> q >= chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, p, j) + steepness;
    }
}