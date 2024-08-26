package dev.creoii.creoapi.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.api.item.CreoComponentTypes;
import dev.creoii.creoapi.impl.item.FoodComponentImpl;
import dev.creoii.creoapi.impl.item.util.AccessibleItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin implements AccessibleItem {
    @Unique private Item.Settings creo$settings;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void creo$accessItemSettings(Item.Settings settings, CallbackInfo ci) {
        this.creo$settings = settings;
    }

    @Override
    public Item.Settings creo$getItemSettings() {
        return creo$settings;
    }

    @Inject(method = "getMaxUseTime", at = @At(value = "HEAD"), cancellable = true)
    private void creo$applyFoodEatTimes(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir) {
        FoodComponentImpl.applyFoodEatSpeed(stack, cir);
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void creo$eatCreoFoodComponent(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        FoodComponentImpl.eatCreoFoodComponentItem(stack, world, user, cir);
    }

    @Inject(method = "use", at = @At(value = "RETURN", ordinal = 2), cancellable = true)
    private void creo$eatCreoFoodComponent(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        FoodComponentImpl.eatCreoFoodComponentPlayer(user, hand, cir);
    }

    @ModifyExpressionValue(method = "getUseAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;contains(Lnet/minecraft/component/ComponentType;)Z"))
    private boolean creo$creoFoodComponentUseAction(boolean original, @Local(argsOnly = true) ItemStack stack) {
        return original || stack.contains(CreoComponentTypes.FOOD);
    }
}