package dev.creoii.creoapi.mixin.tag.enchantment;

import dev.creoii.creoapi.impl.tag.EnchantmentTagImpl;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void creo$applyCursed(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentTagImpl.applyCursed((Enchantment) (Object) this, cir);
    }

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void creo$applyTreasure(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentTagImpl.applyTreasure((Enchantment) (Object) this, cir);
    }

    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    private void creo$applyNotOfferedByLibrarians(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentTagImpl.applyNotOfferedByLibrarians((Enchantment) (Object) this, cir);
    }

    @Inject(method = "isAvailableForRandomSelection", at = @At("HEAD"), cancellable = true)
    private void creo$applyNotRandomlySelectable(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentTagImpl.applyNotRandomlySelectable((Enchantment) (Object) this, cir);
    }
}
