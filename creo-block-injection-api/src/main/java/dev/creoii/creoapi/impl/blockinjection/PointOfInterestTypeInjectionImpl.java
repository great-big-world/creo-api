package dev.creoii.creoapi.impl.blockinjection;

import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class PointOfInterestTypeInjectionImpl {
    public static void injectInternal(RegistryKey<PointOfInterestType> poi, BlockState state) {
        PointOfInterestTypes.POI_STATES_TO_TYPE.put(state, Registries.POINT_OF_INTEREST_TYPE.entryOf(poi));
    }
}
