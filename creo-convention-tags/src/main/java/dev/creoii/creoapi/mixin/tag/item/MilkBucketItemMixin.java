package dev.creoii.creoapi.mixin.tag.item;

import dev.creoii.creoapi.impl.tag.StatusEffectTagImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {
    @Inject(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z"), cancellable = true)
    private void creo_applyMilkDoesNotCure(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        StatusEffectTagImpl.cureStatusEffects(stack, user, cir);
    }
}
