package dev.creoii.creoapi.mixin.tag.screen;

import dev.creoii.creoapi.impl.tag.EnchantmentTagImpl;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {
    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 2, shift = At.Shift.AFTER))
    private void creo$applyGrindstoneIgnoresSlot(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci) {
        EnchantmentTagImpl.applyGrindstoneItemSlotAdd((GrindstoneScreenHandler) (Object) this);
    }

    @Redirect(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;apply(Lnet/minecraft/item/ItemStack;Ljava/util/function/Consumer;)Lnet/minecraft/component/type/ItemEnchantmentsComponent;"))
    private ItemEnchantmentsComponent creo$applyGrindstoneIgnoresGrind(ItemStack stack, Consumer<ItemEnchantmentsComponent.Builder> applier) {
        return EnchantmentTagImpl.applyGrindstoneIgnoresGrind(stack);
    }
}
