package dev.creoii.creoapi.api.blockinjection;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.Set;

public final class BlockInjectionUtils {
    public static Set<BlockState> getStatesOfBlock(Block block) {
        return ImmutableSet.copyOf(block.getStateManager().getStates());
    }
}
