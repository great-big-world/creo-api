package dev.creoii.creoapi.mixin.modification.enchantment;

import dev.creoii.creoapi.impl.modification.EnchantmentImpl;
import dev.creoii.creoapi.impl.modification.util.ExtendedEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(Enchantment.class)
public class EnchantmentMixin implements ExtendedEnchantment {
    @Unique @Nullable private Predicate<ItemStack> creo$acceptableItemPredicate = null;
    @Unique private int creo$minLevel = -1;
    @Unique private int creo$maxLevel = -1;

    @Override
    public void creo$setAcceptableItemPredicate(Predicate<ItemStack> acceptableItemPredicate) {
        this.creo$acceptableItemPredicate = acceptableItemPredicate;
    }

    @Override
    public void creo$setMinLevel(int minLevel) {
        this.creo$minLevel = minLevel;
    }

    @Override
    public void creo$setMaxLevel(int maxLevel) {
        this.creo$maxLevel = maxLevel;
    }

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void creo_applyAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        EnchantmentImpl.applyAcceptableItemPredicate(creo$acceptableItemPredicate, stack, cir);
    }

    @Inject(method = "getMinLevel", at = @At("HEAD"), cancellable = true)
    private void creo_applyMinLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentImpl.applyMinMaxLevel(creo$minLevel, cir);
    }

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void creo_applyMaxLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentImpl.applyMinMaxLevel(creo$maxLevel, cir);
    }
}
