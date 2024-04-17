package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.impl.item.util.AccessibleItem;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ApiStatus.Internal
public final class ItemSettingsImpl {
    public static void applyCreoItemSettings(ItemStack stack, MutableBoolean buoyant) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings settings) {
            buoyant.setValue(settings.isBuoyant());
        }
    }

    public static int applyDespawnTime(ItemStack stack, int defaultDespawnTime) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getDespawnTime();
        }
        return defaultDespawnTime;
    }

    public static int applyPickupDelay(ItemStack stack, int defaultPickupDelay) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getPickupDelay();
        }
        return defaultPickupDelay;
    }

    public static void applyBuoyancy(ItemStack stack, CallbackInfo ci) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings creoItemSettings && !creoItemSettings.isBuoyant()) {
            ci.cancel();
        }
    }

    public static double applyGravity(ItemStack stack, double defaultGravity) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getGravity();
        }
        return defaultGravity;
    }

    public static float applyRotationModifier(ItemStack stack, float original) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.getRotationModifier();
        }
        return original;
    }

    public static boolean applyHoverAnimation(ItemStack stack, boolean original) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings creoItemSettings) {
            return creoItemSettings.hasHoverAnimation();
        }
        return original;
    }

    public static void applyHopperTransferRate(HopperBlockEntity hopperBlockEntity) {
        ItemStack stack = getStack(hopperBlockEntity);
        if (stack.isEmpty())
            return;

        Item.Settings settings = ((AccessibleItem) stack.getItem()).creo_getItemSettings();
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
}
