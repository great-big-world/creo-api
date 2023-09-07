package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.structureplacement.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public final class CreoStructurePlacementTypes {
    public static StructurePlacementType<FixedStructurePlacement> FIXED = () -> FixedStructurePlacement.CODEC;
    public static StructurePlacementType<DistanceFromZeroStructurePlacement> DISTANCE_FROM_ZERO = () -> DistanceFromZeroStructurePlacement.CODEC;
    public static StructurePlacementType<NoiseStructurePlacement> NOISE = () -> NoiseStructurePlacement.CODEC;
    public static StructurePlacementType<FastNoiseStructurePlacement> FAST_NOISE = () -> FastNoiseStructurePlacement.CODEC;
    public static StructurePlacementType<DensityFunctionStructurePlacement> DENSITY_FUNCTION = () -> DensityFunctionStructurePlacement.CODEC;

    static void register() {
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "fixed"), FIXED);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "distance_from_zero"), DISTANCE_FROM_ZERO);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "noise"), NOISE);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "fast_noise"), FAST_NOISE);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "density_function"), DENSITY_FUNCTION);
    }
}
