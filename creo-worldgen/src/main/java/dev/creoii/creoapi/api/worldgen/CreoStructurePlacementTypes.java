package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.structure.placement.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public final class CreoStructurePlacementTypes {
    /**
     * Places the structure at one or more fixed positions in the world.
     */
    public static StructurePlacementType<FixedStructurePlacement> FIXED = () -> FixedStructurePlacement.CODEC;
    /**
     * Places the structure in a random spread after a certain distance from a position.
     */
    public static StructurePlacementType<DistanceFromPosStructurePlacement> DISTANCE_FROM_POS = () -> DistanceFromPosStructurePlacement.CODEC;
    /**
     * Places the structure in a random spread within a range of noise values.
     */
    public static StructurePlacementType<NoiseStructurePlacement> NOISE = () -> NoiseStructurePlacement.CODEC;
    /**
     * Places the structure in a random spread within a range of fast noise values.
     */
    public static StructurePlacementType<FastNoiseStructurePlacement> FAST_NOISE = () -> FastNoiseStructurePlacement.CODEC;
    /**
     * Places the structure in a random spread within a density function range.
     */
    public static StructurePlacementType<DensityFunctionStructurePlacement> DENSITY_FUNCTION = () -> DensityFunctionStructurePlacement.CODEC;

    static void register() {
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "fixed"), FIXED);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "distance_from_pos"), DISTANCE_FROM_POS);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "noise"), NOISE);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "fast_noise"), FAST_NOISE);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoWorldgen.NAMESPACE, "density_function"), DENSITY_FUNCTION);
    }
}
