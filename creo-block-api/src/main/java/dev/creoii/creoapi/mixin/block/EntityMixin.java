package dev.creoii.creoapi.mixin.block;

import dev.creoii.creoapi.impl.block.BlockImpl;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "baseTick", at = @At("TAIL"))
    private void creo_lookAtBlock(CallbackInfo ci) {
        BlockImpl.applyLookAtBlock((Entity) (Object) this);
    }
}