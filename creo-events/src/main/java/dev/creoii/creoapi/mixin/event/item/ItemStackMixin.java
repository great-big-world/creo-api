package dev.creoii.creoapi.mixin.event.item;

import dev.creoii.creoapi.impl.event.ItemEventImpl;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "onCraft", at = @At("HEAD"), cancellable = true)
    private void creo_applyItemCraftEvent(World world, PlayerEntity player, int amount, CallbackInfo ci) {
        ItemEventImpl.applyItemCraftEvent(world, (ItemStack) (Object) this, player, amount, ci);
    }

    @Inject(method = "addEnchantment", at = @At("HEAD"), cancellable = true)
    private void creo_applyItemEnchantEvent(Enchantment enchantment, int level, CallbackInfo ci) {
        ItemEventImpl.applyItemEnchantEvent((ItemStack) (Object) this, enchantment, level, ci);
    }
}
