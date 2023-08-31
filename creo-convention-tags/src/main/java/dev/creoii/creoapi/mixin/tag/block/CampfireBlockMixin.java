package dev.creoii.creoapi.mixin.tag.block;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {
    @Inject(method = "isSignalFireBaseBlock", at = @At("HEAD"), cancellable = true)
    private void creo_signalFireBaseBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        BlockTagImpl.applySignalFireBaseBlocks(state, cir);
    }
}
