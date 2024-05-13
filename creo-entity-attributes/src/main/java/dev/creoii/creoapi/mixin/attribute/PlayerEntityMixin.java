package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.BlockCooldownAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MovementSpeedAttributeImpl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "createPlayerAttributes", at = @At("RETURN"))
    private static void creo$playerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        BlockCooldownAttributeImpl.addAttributes(cir.getReturnValue());
    }

    @Inject(method = "getOffGroundSpeed", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo$applyOffGroundFlyingSpeed(CallbackInfoReturnable<Float> cir) {
        MovementSpeedAttributeImpl.applyOffGroundFlyingSpeed((PlayerEntity) (Object) this, cir);
    }
}
