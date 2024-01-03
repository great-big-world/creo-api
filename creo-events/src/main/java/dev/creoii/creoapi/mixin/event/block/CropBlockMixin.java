package dev.creoii.creoapi.mixin.event.block;

import dev.creoii.creoapi.impl.event.BlockEventImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    @Shadow public abstract BlockState withAge(int age);

    @Inject(method = "applyGrowth", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void creo_applyCropGrowEvent(World world, BlockPos pos, BlockState state, CallbackInfo ci, int i) {
        BlockEventImpl.applyCropGrowEvent(world, pos, state, withAge(i), i, ci);
    }
}
