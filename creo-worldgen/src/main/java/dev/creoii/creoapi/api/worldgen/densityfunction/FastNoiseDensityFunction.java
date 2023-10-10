package dev.creoii.creoapi.api.worldgen.densityfunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseParameters;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public class FastNoiseDensityFunction implements DensityFunction {
    public static final Codec<FastNoiseDensityFunction> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                FastNoiseParameters.REGISTRY_ENTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
                    return predicate.noise;
                }),
                Codec.DOUBLE.fieldOf("xz_scale").forGetter(predicate -> {
                    return predicate.xzScale;
                }),
                Codec.DOUBLE.fieldOf("y_scale").forGetter(predicate -> {
                    return predicate.yScale;
                })
        ).apply(instance, FastNoiseDensityFunction::new);
    });
    public static final CodecHolder<FastNoiseDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final RegistryEntry<FastNoiseParameters> noise;
    private final double xzScale;
    private final double yScale;

    public FastNoiseDensityFunction(RegistryEntry<FastNoiseParameters> noise, double xzScale, double yScale) {
        this.noise = noise;
        this.xzScale = xzScale;
        this.yScale = yScale;
    }

    @Override
    public double sample(NoisePos pos) {
        if (!noise.hasKeyAndValue())
            throw new IllegalArgumentException("FastNoise value " + noise.getKey().orElseThrow().getValue() + " is not present.");
        FastNoiseLite fastNoiseLite = new FastNoiseLite(noise.value());
        return fastNoiseLite.getNoise((float) (pos.blockX() * xzScale), (float) (pos.blockY() * yScale), (float) (pos.blockZ() * xzScale));
    }

    @Override
    public void fill(double[] densities, EachApplier applier) {
        applier.fill(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunctionVisitor visitor) {
        return visitor.apply(new FastNoiseDensityFunction(noise, xzScale, yScale));
    }

    @Override
    public double minValue() {
        return -2d;
    }

    @Override
    public double maxValue() {
        return 2d;
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }
}
