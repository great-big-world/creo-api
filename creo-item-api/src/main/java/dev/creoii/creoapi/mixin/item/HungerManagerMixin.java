package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.FoodComponentImpl;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    private void creo$eatCreoFoodComponent(ItemStack stack, CallbackInfo ci) {
        FoodComponentImpl.eatCreoFoodComponent((HungerManager) (Object) this, stack, ci);
    }
}
