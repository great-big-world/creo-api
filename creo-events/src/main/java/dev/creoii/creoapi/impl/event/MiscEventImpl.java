package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.misc.FishingEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MiscEventImpl {
    public static void applyFishingRodCastEvent(World world, PlayerEntity user, Hand hand, ItemStack itemStack, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean result = FishingEvents.CAST.invoker().onCast(world, user, hand, itemStack);

        if (!result)
            cir.setReturnValue(TypedActionResult.fail(itemStack));
    }

    public static void applyFishingRodCatchEvent(World world, PlayerEntity user, Hand hand, ItemStack itemStack, int lure, int luck, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean result = FishingEvents.CATCH.invoker().onCatch(world, user, hand, itemStack, lure, luck);

        if (!result)
            cir.setReturnValue(TypedActionResult.fail(itemStack));
    }
}
