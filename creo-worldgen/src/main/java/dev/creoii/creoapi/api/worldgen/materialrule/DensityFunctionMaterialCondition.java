package dev.creoii.creoapi.api.worldgen.materialrule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.impl.worldgen.util.AwareNoiseConfig;
import dev.creoii.creoapi.impl.worldgen.util.CreoDensityFunctionVisitor;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.HashMap;
import java.util.Map;

public record DensityFunctionMaterialCondition(RegistryEntry<DensityFunction> densityFunction, double minThreshold, double maxThreshold) implements MaterialRules.MaterialCondition {
    static final Map<Long, NoiseConfig> CACHED_NOISE_CONFIGS = new HashMap<>();
    public static final CodecHolder<DensityFunctionMaterialCondition> CODEC_HOLDER = CodecHolder.of(RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(DensityFunction.REGISTRY_ENTRY_CODEC.fieldOf("density_function").forGetter(predicate -> {
            return predicate.densityFunction;
        }), Codec.DOUBLE.fieldOf("min_threshold").forGetter(predicate -> {
            return predicate.minThreshold;
        }), Codec.DOUBLE.fieldOf("max_threshold").forGetter(predicate -> {
            return predicate.maxThreshold;
        })).apply(instance, DensityFunctionMaterialCondition::new);
    }));

    @Override
    public CodecHolder<? extends MaterialRules.MaterialCondition> codec() {
        return CODEC_HOLDER;
    }

    @Override
    public MaterialRules.BooleanSupplier apply(final MaterialRules.MaterialRuleContext materialRuleContext) {
        return new DensityFunctionThresholdPredicate(materialRuleContext);
    }

    private class DensityFunctionThresholdPredicate extends MaterialRules.HorizontalLazyAbstractPredicate {
        DensityFunctionThresholdPredicate(MaterialRules.MaterialRuleContext materialRuleContext) {
            super(materialRuleContext);
        }

        @Override
        protected boolean test() {
            if (!densityFunction.hasKeyAndValue()) return false;

            long seed = ((AwareNoiseConfig) context.noiseConfig).creo_getWorld().getSeed();
            if (!CACHED_NOISE_CONFIGS.containsKey(seed)) {
                ChunkGenerator chunkGenerator = ((AwareNoiseConfig) context.noiseConfig).creo_getWorld().getChunkManager().getChunkGenerator();
                ChunkGeneratorSettings settings = chunkGenerator instanceof NoiseChunkGenerator noiseChunkGenerator ? noiseChunkGenerator.getSettings().value() : ChunkGeneratorSettings.createMissingSettings();
                CACHED_NOISE_CONFIGS.put(seed, NoiseConfig.create(settings, ((AwareNoiseConfig) context.noiseConfig).creo_getWorld().getRegistryManager().getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS), seed));
            }

            double value = DensityFunctionMaterialCondition.this.densityFunction.value().apply(new CreoDensityFunctionVisitor(CACHED_NOISE_CONFIGS.get(seed))).sample(new DensityFunction.UnblendedNoisePos(context.blockX, context.chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, context.blockX, context.blockZ), context.blockZ));
            return value >= DensityFunctionMaterialCondition.this.minThreshold && value <= DensityFunctionMaterialCondition.this.maxThreshold;
        }
    }
}
