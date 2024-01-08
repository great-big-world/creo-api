package dev.creoii.creoapi.mixin.modification.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockItem.class)
public interface BlockItemAccessor {
    @Accessor("block")
    void setBlock(Block block);
}
