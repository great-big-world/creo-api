package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MiningSpeedAttributeImpl {
    public static void addAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.PLAYER_MINING_SPEED);
    }

    public static void applyMiningSpeed(PlayerEntity player, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValue() * (float) player.getAttributeValue(CreoEntityAttributes.PLAYER_MINING_SPEED));
    }
}
