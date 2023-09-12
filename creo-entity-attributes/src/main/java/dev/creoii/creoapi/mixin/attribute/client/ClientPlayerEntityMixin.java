package dev.creoii.creoapi.mixin.attribute.client;

import dev.creoii.creoapi.impl.attribute.MovementSpeedAttributeImpl;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void creo_applyFlightSpeed(CallbackInfo ci) {
        MovementSpeedAttributeImpl.applySpectatorFlyingSpeed((ClientPlayerEntity) (Object) this);
    }
}