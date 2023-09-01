package dev.creoii.creoapi.mixin.modification.block;

import dev.creoii.creoapi.impl.modification.BlockModificationImpl;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin implements BlockModificationImpl.Modifiable {
    @Shadow protected abstract Block asBlock();
    @Unique boolean modified;

    @Inject(method = "getBlastResistance", at = @At("HEAD"), cancellable = true)
    private void creo_applyBlastResistance(CallbackInfoReturnable<Float> cir) {
        BlockModificationImpl.applyResistance(asBlock(), cir);
    }

    @Override
    public boolean creo_isModified() {
        return modified;
    }

    @Override
    public void creo_setModified(boolean modified) {
        this.modified = modified;
    }
}