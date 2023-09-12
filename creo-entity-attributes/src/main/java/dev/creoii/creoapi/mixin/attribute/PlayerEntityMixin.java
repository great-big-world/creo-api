package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.BlockCooldownAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MovementSpeedAttributeImpl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "createPlayerAttributes", at = @At("RETURN"), cancellable = true)
    private static void creo_playerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        BlockCooldownAttributeImpl.addAttributes(cir);
        MovementSpeedAttributeImpl.addPlayerAttributes(cir);
    }

    @Inject(method = "getOffGroundSpeed", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo_applyOffGroundFlyingSpeed(CallbackInfoReturnable<Float> cir) {
        MovementSpeedAttributeImpl.applyOffGroundFlyingSpeed((PlayerEntity) (Object) this, cir);
    }

    @ModifyConstant(method = "adjustMovementForSneaking", constant = @Constant(doubleValue = .05d))
    private double creo_applyCrouchingSpeed(double constant) {
        return MovementSpeedAttributeImpl.applyCrouchingSpeed((PlayerEntity) (Object) this);
    }

    @ModifyConstant(method = "adjustMovementForSneaking", constant = @Constant(doubleValue = -.05d))
    private double creo_applyNegativeCrouchingSpeed(double constant) {
        return -MovementSpeedAttributeImpl.applyCrouchingSpeed((PlayerEntity) (Object) this);
    }
}
