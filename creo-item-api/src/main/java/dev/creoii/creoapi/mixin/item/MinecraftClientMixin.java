package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.CreoItemImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.HitResult;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Unique private final MutableObject<HitResult> xrayResult = new MutableObject<>();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;updateTargetedEntity(F)V"))
    private void gbw$updateXrayHitResult(CallbackInfo ci) {
        CreoItemImpl.updateAttackThroughBlock((MinecraftClient) (Object) this, xrayResult);
    }

    @Inject(method = "doAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/hit/HitResult;getType()Lnet/minecraft/util/hit/HitResult$Type;"))
    private void gbw$doItemClicks(CallbackInfoReturnable<Boolean> cir) {
        CreoItemImpl.applyAttackThroughBlock((MinecraftClient) (Object) this, xrayResult);
    }
}
