package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.BlockModificationImpl;
import net.minecraft.block.Block;

public interface BlockModification {
    BlockModification INSTANCE = new BlockModificationImpl();

    void setHardness(Block block, float hardness);

    float getHardness(Block block);
}