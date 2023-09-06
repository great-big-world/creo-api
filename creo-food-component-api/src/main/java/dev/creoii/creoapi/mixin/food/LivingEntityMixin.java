package dev.creoii.creoapi.mixin.food;

import dev.creoii.creoapi.api.food.CreoFoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Redirect(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void creo_applyFoodEatDurabilityDamage(ItemStack instance, int amount) {
        if (instance.getItem().getFoodComponent() instanceof CreoFoodComponent creoFoodComponent && creoFoodComponent.hasEatDurability()) {
            instance.damage(1, (LivingEntity) (Object) this, stack -> {});
        } else
            instance.decrement(amount);
    }
}
