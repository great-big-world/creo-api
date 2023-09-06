package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class BlockTagImpl {
    public static void applySignalFireBaseBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.SIGNAL_FIRE_BASE_BLOCKS))
            cir.setReturnValue(true);
    }

    public static void applyCocoaBeansPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.COCOA_BEANS_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static void applyCactusPlantableOn(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!world.getBlockState(pos.up()).isLiquid()) {
            if (state.isIn(CreoBlockTags.CACTUS_PLANTABLE_ON))
                cir.setReturnValue(true);
        }
    }

    public static void applyNetherWartPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.NETHER_WART_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static void applyKeepsFarmlandMoist(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).isIn(CreoBlockTags.KEEPS_FARMLAND_MOIST))
            cir.setReturnValue(true);
    }

    public static void applyKeepsCoralAlive(BlockView world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.offset(direction)).isIn(CreoBlockTags.KEEPS_CORAL_ALIVE))
            cir.setReturnValue(true);
    }

    public static void applyAnvilSofteners(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.down()).isIn(CreoBlockTags.ANVIL_SOFTENERS))
            cir.setReturnValue(false);
    }
}
