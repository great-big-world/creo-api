package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoEnchantmentTags;
import dev.creoii.creoapi.impl.tag.util.GrindstoneItemSlot;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class EnchantmentTagImpl {
    public static void applyCursed(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(TagUtil.isIn(CreoEnchantmentTags.CURSED, enchantment));
    }

    public static void applyTreasure(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(TagUtil.isIn(CreoEnchantmentTags.TREASURE, enchantment));
    }

    public static void applyNotOfferedByLibrarians(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!TagUtil.isIn(CreoEnchantmentTags.NOT_OFFERED_BY_LIBRARIANS, enchantment));
    }

    public static void applyNotRandomlySelectable(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!TagUtil.isIn(CreoEnchantmentTags.NOT_RANDOMLY_SELECTABLE, enchantment));
    }

    public static void applyGrindstoneItemSlotAdd(GrindstoneScreenHandler screenHandler) {
        screenHandler.addSlot(new GrindstoneItemSlot(screenHandler));
    }

    public static ItemEnchantmentsComponent applyGrindstoneIgnoresGrind(ItemStack stack) {
        return EnchantmentHelper.apply(stack, components -> {
            components.remove(enchantment -> {
                return !TagUtil.isIn(CreoEnchantmentTags.GRINDSTONE_IGNORES, enchantment.value());
            });
        });
    }
}
