package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.FoodComponentImpl;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "eatFood", at = @At("HEAD"), cancellable = true)
    private void creo$applyFoodEatDurabilityDamage(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        FoodComponentImpl.applyFoodEatLiving(world, (LivingEntity) (Object) this, stack, cir);
    }
}
