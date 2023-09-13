package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MovementSpeedAttributeImpl {
    public static void addLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.GENERIC_SWIMMING_SPEED).add(CreoEntityAttributes.GENERIC_CLIMBING_SPEED);
    }

    public static void addPlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.PLAYER_FLYING_SPEED);
    }

    public static float applySwimSpeed(LivingEntity livingEntity) {
        return (float) livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_SWIMMING_SPEED);
    }

    public static void applyUpwardSwimSpeed(LivingEntity livingEntity, CallbackInfo ci) {
        livingEntity.setVelocity(livingEntity.getVelocity().add(0d, livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_SWIMMING_SPEED) * 2d, 0d));
        ci.cancel();
    }

    public static void applyOffGroundFlyingSpeed(PlayerEntity player, CallbackInfoReturnable<Float> cir) {
        if (!player.isSpectator()) {
            float speed = (float) player.getAttributeValue(CreoEntityAttributes.PLAYER_FLYING_SPEED);
            cir.setReturnValue(player.isSprinting() ? speed * 2f : speed);
        }
    }

    public static void applySpectatorFlyingSpeed(ClientPlayerEntity player) {
        if (!player.isSpectator())
            player.getAbilities().setFlySpeed((float) player.getAttributeValue(CreoEntityAttributes.PLAYER_FLYING_SPEED));
    }

    public static double applyClimbingSpeed(LivingEntity livingEntity) {
        return livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_CLIMBING_SPEED);
    }
}
