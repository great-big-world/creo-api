package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "addExperienceLevels", at = @At("HEAD"), cancellable = true)
    private void creo_applyPlayerLevelUpEvent(int levels, CallbackInfo ci) {
        EntityEventImpl.applyPlayerLevelUpEvent((PlayerEntity) (Object) this, levels, ci);
    }
}
