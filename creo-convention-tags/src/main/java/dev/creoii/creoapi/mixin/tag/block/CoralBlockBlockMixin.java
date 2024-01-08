package dev.creoii.creoapi.mixin.tag.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralBlockBlock.class)
public class CoralBlockBlockMixin {
    @Inject(method = "isInWater", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo_keepCoralAlive(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, @Local Direction direction) {
        BlockTagImpl.applyKeepsCoralAlive(world, pos, direction, cir);
    }
}
