package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.impl.item.util.AccessibleItem;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ApiStatus.Internal
public final class ItemEntityImpl {
    public static int applyCreoItemSettings(ItemStack stack, MutableInt despawnTime, MutableBoolean buoyant, MutableDouble gravity) {
        if (((AccessibleItem) stack.getItem()).creo_getItemSettings() instanceof CreoItemSettings settings) {
            despawnTime.setValue(settings.getDespawnTime());
            buoyant.setValue(settings.isBuoyant());
            gravity.setValue(settings.getGravity());
            return settings.getPickupDelay();
        }
        return 10;
    }

    public static int applyDespawnTime(MutableInt despawnTime) {
        return despawnTime.intValue();
    }

    public static int applyPickupDelay(MutableInt pickupDelay) {
        return pickupDelay.intValue();
    }

    public static void applyBuoyancy(MutableBoolean buoyant, CallbackInfo ci) {
        if (!buoyant.booleanValue())
            ci.cancel();
    }

    public static double applyGravity(MutableDouble gravity) {
        return gravity.doubleValue();
    }
}
