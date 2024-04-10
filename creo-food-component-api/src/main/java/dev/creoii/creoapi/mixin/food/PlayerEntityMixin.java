package dev.creoii.creoapi.mixin.food;

import dev.creoii.creoapi.impl.food.FoodComponentImpl;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Redirect(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/item/Item;Lnet/minecraft/item/ItemStack;)V"))
    private void gbw$applyCreoHealthFoods(HungerManager instance, Item item, ItemStack stack) {
        FoodComponentImpl.applyFoodHealsHealth((PlayerEntity) (Object) this, instance, stack);
    }
}
