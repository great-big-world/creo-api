package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.item.ItemEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public final class ItemEventImpl {
    public static void applyItemCraftEvent(World world, ItemStack stack, PlayerEntity player, int amount, CallbackInfo ci) {
        ItemEvents.CRAFT.invoker().onCraft(world, stack, player, amount);
    }

    public static void applyItemEnchantEvent(ItemStack stack, Enchantment enchantment, int level, CallbackInfo ci) {
        boolean result = ItemEvents.ENCHANT.invoker().shouldEnchant(stack, enchantment, level);

        if (!result)
            ci.cancel();
    }
}
