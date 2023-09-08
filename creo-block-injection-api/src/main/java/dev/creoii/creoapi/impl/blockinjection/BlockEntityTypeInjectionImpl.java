package dev.creoii.creoapi.impl.blockinjection;

import com.google.common.collect.Sets;
import dev.creoii.creoapi.mixin.blockinjection.BlockEntityTypeAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

import java.util.HashSet;

public final class BlockEntityTypeInjectionImpl {
    public static void injectInternal(BlockEntityType<?> blockEntityType, Block block) {
        BlockEntityTypeAccessor accessor = ((BlockEntityTypeAccessor) blockEntityType);
        HashSet<Block> newBlocks = Sets.newHashSet(accessor.getBlocks());
        newBlocks.add(block);
        accessor.setBlocks(newBlocks);
    }
}
