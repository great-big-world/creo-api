package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.ItemEventImpl;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Inject(method = "onPlayerCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;triggerItemPickedUpByEntityCriteria(Lnet/minecraft/entity/ItemEntity;)V", shift = At.Shift.AFTER))
    private void creo$applyItemPickUpEvent(PlayerEntity player, CallbackInfo ci) {
        ItemEventImpl.applyItemPickUpEvent((ItemEntity) (Object) this, player);
    }
}
