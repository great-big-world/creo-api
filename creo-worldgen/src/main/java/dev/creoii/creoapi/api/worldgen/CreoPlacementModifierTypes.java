package dev.creoii.creoapi.api.worldgen;

import dev.creoii.creoapi.api.worldgen.placementmodifier.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public final class CreoPlacementModifierTypes {
    /**
     * Conditional placement modifier that places if the noise value at the position is within a range.
     */
    public static final PlacementModifierType<NoisePlacementModifier> NOISE = () -> NoisePlacementModifier.CODEC;
    /**
     * Count placement modifier that places based on the noise value at the position.
     */
    public static final PlacementModifierType<NoiseCountPlacementModifier> NOISE_COUNT = () -> NoiseCountPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the position is as steep or steeper than the steepness value.
     */
    public static final PlacementModifierType<SteepPlacementModifier> STEEP = () -> SteepPlacementModifier.CODEC;
    /**
     * Placement modifier that offsets the position within the chunk. This should be used as a replacement to {@link PlacementModifierType#IN_SQUARE}.
     */
    public static final PlacementModifierType<OffsetPlacementModifier> OFFSET = () -> OffsetPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the density function value at the position is within a range.
     */
    public static final PlacementModifierType<DensityFunctionPlacementModifier> DENSITY_FUNCTION = () -> DensityFunctionPlacementModifier.CODEC;
    /**
     * Count placement modifier that places based on the density function value at the position.
     */
    public static final PlacementModifierType<DensityFunctionCountPlacementModifier> DENSITY_FUNCTION_COUNT = () -> DensityFunctionCountPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the position is within range of the structure.
     */
    public static final PlacementModifierType<NearStructurePlacementModifier> NEAR_STRUCTURE = () -> NearStructurePlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the position is within the bounding box of the structure.
     */
    public static final PlacementModifierType<WithinStructurePlacementModifier> WITHIN_STRUCTURE = () -> WithinStructurePlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the position is open to the sky.
     */
    public static final PlacementModifierType<SkyVisiblePlacementModifier> SKY_VISIBLE = () -> SkyVisiblePlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the position is further than the distance from zero.
     */
    public static final PlacementModifierType<DistanceFromZeroPlacementModifier> DISTANCE_FROM_ZERO = () -> DistanceFromZeroPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that places if the top of the heightmap is within two {@link net.minecraft.world.gen.heightprovider.HeightProvider}.
     */
    public static final PlacementModifierType<HeightFilterPlacementModifier> HEIGHT_FILTER = () -> HeightFilterPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that inverts the input placement modifier.
     */
    public static final PlacementModifierType<NotPlacementModifier> NOT = () -> NotPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that takes a list of placement modifiers and performs an OR check on all of them.
     */
    public static final PlacementModifierType<AnyOfPlacementModifier> ANY_OF = () -> AnyOfPlacementModifier.CODEC;
    /**
     * Conditional placement modifier that takes a list of placement modifiers and performs an AND check on all of them.
     */
    public static final PlacementModifierType<AllOfPlacementModifier> ALL_OF = () -> AllOfPlacementModifier.CODEC;
    /**
     * Placement modifier that takes a list of placement modifiers and performs one of them.
     */
    public static final PlacementModifierType<RandomPlacementModifier> RANDOM = () -> RandomPlacementModifier.CODEC;

    static void register() {
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "noise"), NOISE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "noise_count"), NOISE_COUNT);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "steep"), STEEP);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "offset"), OFFSET);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "density_function"), DENSITY_FUNCTION);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "density_function_count"), DENSITY_FUNCTION_COUNT);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "near_structure"), NEAR_STRUCTURE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "within_structure"), WITHIN_STRUCTURE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "sky_visible"), SKY_VISIBLE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "distance_from_zero"), DISTANCE_FROM_ZERO);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "height_filter"), HEIGHT_FILTER);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "not"), NOT);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "any_of"), ANY_OF);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "all_of"), ALL_OF);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoWorldgen.NAMESPACE, "random"), RANDOM);
    }
}
