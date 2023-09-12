package dev.creoii.creoapi.mixin.attribute.client;

import dev.creoii.creoapi.impl.attribute.BlockCooldownAttributeImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isItemEnabled(Lnet/minecraft/resource/featuretoggle/FeatureSet;)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyBlockPlaceSpeed(CallbackInfo ci, Hand[] var1, int var2, int var3, Hand hand, ItemStack itemStack) {
        BlockCooldownAttributeImpl.applyBlockPlaceSpeed((MinecraftClient) (Object) this, itemStack);
    }
}
