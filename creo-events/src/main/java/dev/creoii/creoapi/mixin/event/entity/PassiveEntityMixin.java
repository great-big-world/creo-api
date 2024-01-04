package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PassiveEntity.class)
public class PassiveEntityMixin {
    @Inject(method = "setBreedingAge", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/PassiveEntity;onGrowUp()V"), cancellable = true)
    private void creo_applyAnimalGrowUpEvent(int age, CallbackInfo ci) {
        EntityEventImpl.applyAnimalGrowUpEvent((PassiveEntity) (Object) this, age, ci);
    }
}
