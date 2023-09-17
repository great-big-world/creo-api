package dev.creoii.creoapi.api.worldgen.materialrule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import dev.creoii.creoapi.impl.worldgen.util.WorldAwareNoiseConfig;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public record FastNoiseMaterialCondition(RegistryEntry<FastNoiseLite> noise, double minThreshold, double maxThreshold) implements MaterialRules.MaterialCondition {
    public static final CodecHolder<FastNoiseMaterialCondition> CODEC_HOLDER = CodecHolder.of(RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(FastNoiseLite.REGISTRY_ENTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.fieldOf("min_threshold").forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.fieldOf("max_threshold").forGetter(predicate -> {
            return predicate.maxThreshold;
        })).apply(instance, FastNoiseMaterialCondition::new);
    }));

    @Override
    public CodecHolder<? extends MaterialRules.MaterialCondition> codec() {
        return CODEC_HOLDER;
    }

    @Override
    public MaterialRules.BooleanSupplier apply(final MaterialRules.MaterialRuleContext materialRuleContext) {
        return new FastNoisePredicate(materialRuleContext);
    }

    private class FastNoisePredicate extends MaterialRules.HorizontalLazyAbstractPredicate {
        FastNoisePredicate(MaterialRules.MaterialRuleContext materialRuleContext) {
            super(materialRuleContext);
        }

        @Override
        protected boolean test() {
            if (!noise.hasKeyAndValue())
                return false;
            FastNoiseLite fastNoiseLite = noise.value().seed(((WorldAwareNoiseConfig) context.noiseConfig).creo_getWorld().getSeed());
            double value = fastNoiseLite.getNoise(context.blockX, context.chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, context.blockX, context.blockZ), context.blockZ);
            return value >= FastNoiseMaterialCondition.this.minThreshold && value <= FastNoiseMaterialCondition.this.maxThreshold;
        }
    }
}