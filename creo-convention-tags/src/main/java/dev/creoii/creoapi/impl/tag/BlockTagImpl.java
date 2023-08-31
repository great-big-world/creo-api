package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.text.html.parser.Entity;

public final class BlockTagImpl {
    public static void applySignalFireBaseBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CBlockTags.SIGNAL_FIRE_BASE_BLOCKS))
            cir.setReturnValue(true);
    }

    public static void applyCocoaBeansPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CBlockTags.COCOA_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static void applyCactusPlantableOn(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!world.getBlockState(pos.up()).isLiquid()) {
            if (state.isIn(CBlockTags.SIGNAL_FIRE_BASE_BLOCKS))
                cir.setReturnValue(true);
        }
    }

    public static void applyNetherWartPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CBlockTags.NETHER_WART_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static void applyKeepsFarmlandMoist(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).isIn(CBlockTags.KEEPS_FARMLAND_MOIST))
            cir.setReturnValue(true);
    }

    public static void applyKeepsCoralAlive(BlockView world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.offset(direction)).isIn(CBlockTags.KEEPS_CORAL_ALIVE)) {
            cir.setReturnValue(true);
        }
    }
}
