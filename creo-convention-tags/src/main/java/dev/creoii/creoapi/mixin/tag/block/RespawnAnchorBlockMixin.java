package dev.creoii.creoapi.mixin.tag.block;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin {
    @Inject(method = "isChargeItem", at = @At("HEAD"), cancellable = true)
    private static void creo_respawnAnchorCharges(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        ItemTagImpl.applyRespawnAnchorCharges(stack, cir);
    }
}
