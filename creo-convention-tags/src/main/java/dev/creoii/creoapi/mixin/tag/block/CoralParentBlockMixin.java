package dev.creoii.creoapi.mixin.tag.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralParentBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CoralParentBlock.class)
public class CoralParentBlockMixin {
    @Inject(method = "isInWater", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private static void creo_keepCoralAlive(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, @Local Direction direction) {
        BlockTagImpl.applyKeepsCoralAlive(world, pos, direction, cir);
    }
}
