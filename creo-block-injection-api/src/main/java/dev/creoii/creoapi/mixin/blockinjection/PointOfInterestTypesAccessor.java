package dev.creoii.creoapi.mixin.blockinjection;

import net.minecraft.block.BlockState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(PointOfInterestTypes.class)
public interface PointOfInterestTypesAccessor {
    @Accessor("POI_STATES_TO_TYPE")
    Map<BlockState, RegistryEntry<PointOfInterestType>> getPOIStatesToType();
}
