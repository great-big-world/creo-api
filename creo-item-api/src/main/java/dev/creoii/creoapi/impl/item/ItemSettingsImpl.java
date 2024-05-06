package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.api.item.ItemEvents;
import dev.creoii.creoapi.impl.item.util.AccessibleItem;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@ApiStatus.Internal
public final class ItemSettingsImpl {
    public static int applyDespawnTime(ItemStack stack, int defaultDespawnTime) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getDespawnTime();
        }
        return defaultDespawnTime;
    }

    public static int applyPickupDelay(ItemStack stack, int defaultPickupDelay) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getPickupDelay();
        }
        return defaultPickupDelay;
    }

    public static void applyBuoyancy(ItemStack stack, CallbackInfo ci) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings && !creoItemSettings.isBuoyant()) {
            ci.cancel();
        }
    }

    public static void applyGravity(ItemStack stack, CallbackInfoReturnable<Double> cir) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            cir.setReturnValue(creoItemSettings.getGravity());
        }
    }

    public static float applyRotationModifier(ItemStack stack, float original) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getRotationModifier();
        }
        return original;
    }

    public static boolean applyHoverAnimation(ItemStack stack, boolean original) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.hasHoverAnimation();
        }
        return original;
    }

    public static void applyHopperTransferRate(HopperBlockEntity hopperBlockEntity) {
        ItemStack stack = getStack(hopperBlockEntity);
        if (stack.isEmpty())
            return;

        Item.Settings settings = ((AccessibleItem) stack.getItem()).creo$getItemSettings();
        if (settings instanceof CreoItemSettings creoItemSettings) {
            hopperBlockEntity.setTransferCooldown(creoItemSettings.getHopperTransferRate());
            return;
        }

        hopperBlockEntity.setTransferCooldown(8);
    }

    private static ItemStack getStack(HopperBlockEntity hopperBlockEntity) {
        for (int i = 0; i < hopperBlockEntity.size(); ++i) {
            ItemStack stack = hopperBlockEntity.getStack(i);
            if (!stack.isEmpty())
                return stack;
        }
        return ItemStack.EMPTY;
    }

    public static boolean canApplyClickPickup(ItemStack stack) {
        if (((AccessibleItem) stack.getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.doesClickPickup();
        }
        return false;
    }

    public static ActionResult applyClickPickup(PlayerEntity player, ItemEntity entity, int pickupDelay, @Nullable UUID owner) {
        if (((AccessibleItem) entity.getStack().getItem()).creo$getItemSettings() instanceof CreoItemSettings creoItemSettings && creoItemSettings.doesClickPickup() && pickupDelay == 0) {
            if (!entity.getWorld().isClient) {
                ItemStack stack = entity.getStack();
                Item item = stack.getItem();
                int i = stack.getCount();
                if ((owner == null || owner.equals(player.getUuid())) && player.getInventory().insertStack(stack)) {
                    player.sendPickup(entity, i);
                    if (stack.isEmpty()) {
                        entity.discard();
                        stack.setCount(i);
                    }
                    ItemEvents.CLICK_PICKUP.invoker().onClickPickUp(entity, player);
                    player.increaseStat(Stats.PICKED_UP.getOrCreateStat(item), i);
                    player.triggerItemPickedUpByEntityCriteria(entity);
                }
            }
            return ActionResult.success(player.getWorld().isClient);
        }
        return ActionResult.PASS;
    }
}
