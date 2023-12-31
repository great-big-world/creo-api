package dev.creoii.creoapi.impl.modification;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@ApiStatus.Internal
public final class EnchantmentImpl {
    public static void applyAcceptableItemPredicate(Predicate<ItemStack> acceptableItemPredicate, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (acceptableItemPredicate != null)
            cir.setReturnValue(acceptableItemPredicate.test(stack));
    }

    public static void applyMinMaxLevel(int level, CallbackInfoReturnable<Integer> cir) {
        if (level != -1)
            cir.setReturnValue(level);
    }
}
