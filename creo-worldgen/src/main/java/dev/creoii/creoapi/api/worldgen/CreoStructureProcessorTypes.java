package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.structure.processor.NoiseStructureProcessor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;

public final class CreoStructureProcessorTypes {
    public static final StructureProcessorType<NoiseStructureProcessor> NOISE = () -> NoiseStructureProcessor.CODEC;

    public static void register() {
        Registry.register(Registries.STRUCTURE_PROCESSOR, new Identifier(CreoWorldgen.NAMESPACE, "noise"), NOISE);
    }
}
