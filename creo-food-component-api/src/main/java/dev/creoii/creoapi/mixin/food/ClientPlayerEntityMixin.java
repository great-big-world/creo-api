package dev.creoii.creoapi.mixin.food;

import dev.creoii.creoapi.impl.food.FoodComponentImpl;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Redirect(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean creo_applySprintEdibles(ClientPlayerEntity instance) {
        return FoodComponentImpl.applyFoodSprintEdibles(instance);
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean creo_applySprintEdiblesMovement(ClientPlayerEntity instance) {
        return FoodComponentImpl.applyFoodSprintEdibles(instance);
    }
}