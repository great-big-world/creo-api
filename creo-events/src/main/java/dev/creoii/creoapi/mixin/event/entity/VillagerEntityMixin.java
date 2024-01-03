package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Inject(method = "wakeUp", at = @At("HEAD"), cancellable = true)
    private void creo_applySleepWakeUpEvent(CallbackInfo ci) {
        MiscEventImpl.applySleepWakeUpEvent((VillagerEntity) (Object) this, false, false, ci);
    }
}
