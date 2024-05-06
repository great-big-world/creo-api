package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.FoodComponentImpl;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Redirect(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean creo$applySprintEdibles(ClientPlayerEntity instance) {
        return FoodComponentImpl.applyFoodSprintEdibles(instance);
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean creo$applySprintEdiblesMovement(ClientPlayerEntity instance) {
        return FoodComponentImpl.applyFoodSprintEdibles(instance);
    }
}
