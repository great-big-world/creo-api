package dev.creoii.creoapi.api.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record StructureFeatureConfig(List<Identifier> structures, RegistryEntry<StructureProcessorList> processors, boolean randomRotation, @Nullable Heightmap.Type forcedHeightmap) implements FeatureConfig {
    public static final Codec<StructureFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Identifier.CODEC.listOf().fieldOf("structures").forGetter(config -> {
            return config.structures;
        }), StructureProcessorType.REGISTRY_CODEC.fieldOf("processors").forGetter(config -> {
            return config.processors;
        }), Codec.BOOL.optionalFieldOf("random_rotation", true).forGetter(config -> {
            return config.randomRotation;
        }), Heightmap.Type.CODEC.optionalFieldOf("forced_heightmap", null).forGetter(config -> {
            return config.forcedHeightmap;
        })).apply(instance, StructureFeatureConfig::new);
    });
}
