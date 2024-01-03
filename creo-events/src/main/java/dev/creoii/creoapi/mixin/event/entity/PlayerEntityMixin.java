package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import dev.creoii.creoapi.impl.event.MiscEventImpl;
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

    @Inject(method = "wakeUp(ZZ)V", at = @At("HEAD"), cancellable = true)
    private void creo_applySleepWakeUpEvent(boolean skipSleepTimer, boolean updateSleepingPlayers, CallbackInfo ci) {
        MiscEventImpl.applySleepWakeUpEvent((PlayerEntity) (Object) this, skipSleepTimer, updateSleepingPlayers, ci);
    }
}
