package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WardenEntity.class)
public class WardenEntityMixin {
    @Inject(method = "isValidTarget", at = @At("RETURN"), cancellable = true)
    private void creo_wardenIgnores(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityTypeTagImpl.applyWardenIgnores(entity, cir);
    }
}
