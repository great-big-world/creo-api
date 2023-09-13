package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract EntityType<?> getType();

    @Inject(method = "canAvoidTraps", at = @At("HEAD"), cancellable = true)
    private void creo_lib_canAvoidTraps(CallbackInfoReturnable<Boolean> cir) {
        EntityTypeTagImpl.applyAvoidsTraps(getType(), cir);
    }

    @Inject(method = "isCollidable", at = @At("HEAD"), cancellable = true)
    private void creo_lib_isCollidable(CallbackInfoReturnable<Boolean> cir) {
        EntityTypeTagImpl.applyCollidable(getType(), cir);
    }
}
