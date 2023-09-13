package dev.creoii.creoapi.api.blockinjection;

import com.google.common.collect.ImmutableSet;
import dev.creoii.creoapi.impl.blockinjection.PointOfInterestTypeInjectionImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Set;

public final class PointOfInterestTypeInjection {
    public static void inject(RegistryKey<PointOfInterestType> poi, BlockState state) {
        PointOfInterestTypeInjectionImpl.injectInternal(poi, state);
    }

    public static void inject(RegistryKey<PointOfInterestType> poi, Block block) {
        for (BlockState state : getStatesOfBlock(block)) {
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

    private static Set<BlockState> getStatesOfBlock(Block block) {
        return ImmutableSet.copyOf(block.getStateManager().getStates());
    }
}
