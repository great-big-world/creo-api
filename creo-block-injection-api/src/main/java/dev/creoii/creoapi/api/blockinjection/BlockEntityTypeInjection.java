package dev.creoii.creoapi.api.blockinjection;

import dev.creoii.creoapi.impl.blockinjection.BlockEntityTypeInjectionImpl;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

public final class BlockEntityTypeInjection {
    public static void inject(BlockEntityType<?> blockEntityType, Block block) {
        BlockEntityTypeInjectionImpl.injectInternal(blockEntityType, block);
    }

    public static void inject(BlockEntityType<?> blockEntityType, Block... blocks) {
        for (Block block : blocks) {
            inject(blockEntityType, block);
        }
    }
}
