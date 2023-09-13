package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.BlockCooldownAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MiningSpeedAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MovementSpeedAttributeImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "createPlayerAttributes", at = @At("RETURN"), cancellable = true)
    private static void creo_playerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        BlockCooldownAttributeImpl.addAttributes(cir);
        MovementSpeedAttributeImpl.addPlayerAttributes(cir);
        MiningSpeedAttributeImpl.addAttribute(cir);
    }

    @Inject(method = "getOffGroundSpeed", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo_applyOffGroundFlyingSpeed(CallbackInfoReturnable<Float> cir) {
        MovementSpeedAttributeImpl.applyOffGroundFlyingSpeed((PlayerEntity) (Object) this, cir);
    }

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void creo_applyMiningSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        MiningSpeedAttributeImpl.applyMiningSpeed((PlayerEntity) (Object) this, cir);
    }
}
