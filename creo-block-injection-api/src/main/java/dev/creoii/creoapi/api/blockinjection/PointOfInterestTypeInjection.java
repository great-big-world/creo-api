package dev.creoii.creoapi.api.blockinjection;

import dev.creoii.creoapi.impl.blockinjection.PointOfInterestTypeInjectionImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;

public final class PointOfInterestTypeInjection {
    public static void inject(RegistryKey<PointOfInterestType> poi, BlockState state) {
        PointOfInterestTypeInjectionImpl.injectInternal(poi, state);
    }

    public static void inject(RegistryKey<PointOfInterestType> poi, Block block) {
        for (BlockState state : BlockInjectionUtils.getStatesOfBlock(block)) {
            inject(poi, state);
        }
    }

    public static void inject(RegistryKey<PointOfInterestType> poi, BlockState... states) {
        for (BlockState state : states) {
            inject(poi, state);
        }
    }

    public static void inject(RegistryKey<PointOfInterestType> poi, Block... blocks) {
        for (Block block : blocks) {
            inject(poi, block);
        }
    }
}
