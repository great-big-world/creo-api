package dev.creoii.creoapi.api.worldgen.fastnoise;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoWorldgen;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public record FastNoiseParameters(long seed, float frequency, FastNoiseLite.NoiseType noiseType, FastNoiseLite.RotationType3D rotationType3D, Fractal fractal, Cellular cellular, DomainWarp domainWarp) {
    public static final RegistryKey<Registry<FastNoiseParameters>> REGISTRY_KEY = RegistryKey.ofRegistry(new Identifier(CreoWorldgen.NAMESPACE, "worldgen/fast_noise"));
    public static final Codec<RegistryEntry<FastNoiseParameters>> REGISTRY_ENTRY_CODEC = RegistryElementCodec.of(REGISTRY_KEY, FastNoiseParameters.CODEC);
    public static class Fractal {
        public static final Fractal DEFAULT = new Fractal(FastNoiseLite.FractalType.NONE, 3, 2f, .5f, 0f, 2f);
        public static final Codec<Fractal> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(FastNoiseLite.FractalType.CODEC.fieldOf("type").orElse(FastNoiseLite.FractalType.NONE).forGetter(fractal -> {
                return fractal.type;
            }), Codec.INT.fieldOf("octaves").orElse(3).forGetter(fractal -> {
                return fractal.octaves;
            }), Codec.FLOAT.fieldOf("lacunarity").orElse(2f).forGetter(fractal -> {
                return fractal.lacunarity;
            }), Codec.FLOAT.fieldOf("gain").orElse(.5f).forGetter(fractal -> {
                return fractal.gain;
            }), Codec.FLOAT.fieldOf("weighted_strength").orElse(0f).forGetter(fractal -> {
                return fractal.weightedStrength;
            }), Codec.FLOAT.fieldOf("ping_pong_strength").orElse(2f).forGetter(fractal -> {
                return fractal.pingPongStrength;
            })).apply(instance, Fractal::new);
        });
        public FastNoiseLite.FractalType type;
        public int octaves;
        public float lacunarity;
        public float gain;
        public float weightedStrength;
        public float pingPongStrength;

        public Fractal(FastNoiseLite.FractalType type, int octaves, float lacunarity, float gain, float weightedStrength, float pingPongStrength) {
            this.type = type;
            this.octaves = octaves;
            this.lacunarity = lacunarity;
            this.gain = gain;
            this.weightedStrength = weightedStrength;
            this.pingPongStrength = pingPongStrength;
        }

        public void setType(FastNoiseLite.FractalType type) {
            this.type = type;
        }

        public void setOctaves(int octaves) {
            this.octaves = octaves;
        }

        public void setLacunarity(float lacunarity) {
            this.lacunarity = lacunarity;
        }

        public void setGain(float gain) {
            this.gain = gain;
        }

        public void setWeightedStrength(float weightedStrength) {
            this.weightedStrength = weightedStrength;
        }

        public void setPingPongStrength(float pingPongStrength) {
            this.pingPongStrength = pingPongStrength;
        }
    }

    public static class Cellular {
        public static final Cellular DEFAULT = new Cellular(FastNoiseLite.CellularDistanceFunction.EUCLIDEAN_SQ, FastNoiseLite.CellularReturnType.DISTANCE, 1);
        public static final Codec<Cellular> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(FastNoiseLite.CellularDistanceFunction.CODEC.fieldOf("distance_function").orElse(FastNoiseLite.CellularDistanceFunction.EUCLIDEAN_SQ).forGetter(fastNoiseLite -> {
                return fastNoiseLite.distanceFunction;
            }), FastNoiseLite.CellularReturnType.CODEC.fieldOf("return_type").orElse(FastNoiseLite.CellularReturnType.DISTANCE).forGetter(fastNoiseLite -> {
                return fastNoiseLite.returnType;
            }), Codec.FLOAT.fieldOf("jitter_modifier").orElse(1f).forGetter(fastNoiseLite -> {
                return fastNoiseLite.jitterModifier;
            })).apply(instance, Cellular::new);
        });
        public FastNoiseLite.CellularDistanceFunction distanceFunction;
        public FastNoiseLite.CellularReturnType returnType;
        public float jitterModifier;

        public Cellular(FastNoiseLite.CellularDistanceFunction distanceFunction, FastNoiseLite.CellularReturnType returnType, float jitterModifier) {
            this.distanceFunction = distanceFunction;
            this.returnType = returnType;
            this.jitterModifier = jitterModifier;
        }

        public void setDistanceFunction(FastNoiseLite.CellularDistanceFunction distanceFunction) {
            this.distanceFunction = distanceFunction;
        }

        public void setReturnType(FastNoiseLite.CellularReturnType returnType) {
            this.returnType = returnType;
        }

        public void setJitterModifier(float jitterModifier) {
            this.jitterModifier = jitterModifier;
        }
    }

    public static class DomainWarp {
        public static final DomainWarp DEFAULT = new DomainWarp(FastNoiseLite.DomainWarpType.OPEN_SIMPLEX_2, 30f, .002f);
        public static final Codec<DomainWarp> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(FastNoiseLite.DomainWarpType.CODEC.fieldOf("type").orElse(FastNoiseLite.DomainWarpType.OPEN_SIMPLEX_2).forGetter(fastNoiseLite -> {
                return fastNoiseLite.type;
            }), Codec.FLOAT.fieldOf("amplitude").orElse(30f).forGetter(fastNoiseLite -> {
                return fastNoiseLite.amplitude;
            }), Codec.FLOAT.fieldOf("frequency").orElse(.005f).forGetter(fastNoiseLite -> {
                return fastNoiseLite.frequency;
            })).apply(instance, DomainWarp::new);
        });
        public FastNoiseLite.DomainWarpType type;
        public float amplitude;
        public float frequency;

        public DomainWarp(FastNoiseLite.DomainWarpType type, float amplitude, float frequency) {
            this.type = type;
            this.amplitude = amplitude;
            this.frequency = frequency;
        }

        public void setType(FastNoiseLite.DomainWarpType type) {
            this.type = type;
        }

        public void setAmplitude(float amplitude) {
            this.amplitude = amplitude;
        }

        public void setFrequency(float frequency) {
            this.frequency = frequency;
        }
    }

    public static final Codec<FastNoiseParameters> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.LONG.optionalFieldOf("seed", 1337L).forGetter(fastNoiseLite -> {
            return fastNoiseLite.seed;
        }), Codec.FLOAT.fieldOf("frequency").orElse(.01f).forGetter(fastNoiseLite -> {
            return fastNoiseLite.frequency;
        }), FastNoiseLite.NoiseType.CODEC.fieldOf("noise_type").orElse(FastNoiseLite.NoiseType.OPEN_SIMPLEX_2).forGetter(fastNoiseLite -> {
            return fastNoiseLite.noiseType;
        }), FastNoiseLite.RotationType3D.CODEC.fieldOf("rotation_type_3d").orElse(FastNoiseLite.RotationType3D.NONE).forGetter(fastNoiseLite -> {
            return fastNoiseLite.rotationType3D;
        }), Fractal.CODEC.fieldOf("fractal").orElse(Fractal.DEFAULT).forGetter(fastNoiseLite -> {
            return fastNoiseLite.fractal;
        }), Cellular.CODEC.fieldOf("cellular").orElse(Cellular.DEFAULT).forGetter(fastNoiseLite -> {
            return fastNoiseLite.cellular;
        }), DomainWarp.CODEC.fieldOf("domain_warp").orElse(DomainWarp.DEFAULT).forGetter(fastNoiseLite -> {
            return fastNoiseLite.domainWarp;
        })).apply(instance, FastNoiseParameters::new);
    });
}
