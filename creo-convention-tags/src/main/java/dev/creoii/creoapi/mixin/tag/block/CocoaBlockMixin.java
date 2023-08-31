package dev.creoii.creoapi.mixin.tag.block;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CocoaBlock.class)
public class CocoaBlockMixin {
    @Inject(method = "canPlaceAt", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_cocoaPlaceableOn(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, BlockState blockState) {
        BlockTagImpl.applyCocoaBeansPlantableOn(blockState, cir);
    }
}
