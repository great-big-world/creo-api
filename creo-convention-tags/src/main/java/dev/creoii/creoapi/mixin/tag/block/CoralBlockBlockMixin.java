package dev.creoii.creoapi.mixin.tag.block;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CoralBlockBlock.class)
public class CoralBlockBlockMixin {
    @Inject(method = "isInWater", at = @At(value = "RETURN", ordinal = 0), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_keepCoralAlive(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, Direction[] var3, int var4, int var5, Direction direction, FluidState fluidState) {
        BlockTagImpl.applyKeepsCoralAlive(world, pos, direction, cir);
    }
}
