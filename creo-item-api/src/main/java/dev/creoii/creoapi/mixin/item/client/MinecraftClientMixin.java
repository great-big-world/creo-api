package dev.creoii.creoapi.mixin.item.client;

import dev.creoii.creoapi.impl.item.CreoItemImpl;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "doAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/hit/HitResult;getType()Lnet/minecraft/util/hit/HitResult$Type;"))
    private void creo$applyItemAttack(CallbackInfoReturnable<Boolean> cir) {
        CreoItemImpl.applyItemAttack((MinecraftClient) (Object) this);
    }
}
