package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.BlockModification;
import net.minecraft.block.Block;

public class BlockModificationImpl implements BlockModification {
    @Override
    public void setHardness(Block block, float hardness) {

    }

    @Override
    public float getHardness(Block block) {
        return 0;
    }
}