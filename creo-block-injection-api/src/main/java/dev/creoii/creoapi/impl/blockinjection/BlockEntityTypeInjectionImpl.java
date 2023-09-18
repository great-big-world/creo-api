package dev.creoii.creoapi.impl.blockinjection;

import com.google.common.collect.Sets;
import dev.creoii.creoapi.mixin.blockinjection.BlockEntityTypeAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashSet;

@ApiStatus.Internal
public final class BlockEntityTypeInjectionImpl {
    public static void injectInternal(BlockEntityType<?> blockEntityType, Block block) {
        BlockEntityTypeAccessor accessor = ((BlockEntityTypeAccessor) blockEntityType);
        HashSet<Block> newBlocks = Sets.newHashSet(accessor.getBlocks());
        newBlocks.add(block);
        accessor.setBlocks(newBlocks);
    }
}
