package dev.creoii.creoapi.mixin.modification.block;

import dev.creoii.creoapi.impl.modification.BlockModificationImpl;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Shadow protected abstract Block asBlock();

    @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
    private void creo_applyHardness(CallbackInfoReturnable<Float> cir) {
        BlockModificationImpl.applyHardness(asBlock(), cir);
    }
}
