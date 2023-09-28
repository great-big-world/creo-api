package dev.creoii.creoapi.mixin.tag.screen;

import dev.creoii.creoapi.impl.tag.EnchantmentTagImpl;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {
    @Unique private ItemStack creo_item;

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 2, shift = At.Shift.AFTER))
    private void creo_applyGrindstoneIgnoresSlot(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci) {
        EnchantmentTagImpl.applyGrindstoneItemSlotAdd((GrindstoneScreenHandler) (Object) this);
    }

    @Inject(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;"))
    private void creo_applyGrindstoneIgnoresSet(ItemStack item, int damage, int amount, CallbackInfoReturnable<ItemStack> cir) {
        this.creo_item = item;
    }

    @Redirect(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;set(Ljava/util/Map;Lnet/minecraft/item/ItemStack;)V"))
    private void creo_applyGrindstoneIgnoresGrind(Map<Enchantment, Integer> enchantments, ItemStack stack) {
        EnchantmentTagImpl.applyGrindstoneIgnoresGrind(creo_item, stack);
    }
}
