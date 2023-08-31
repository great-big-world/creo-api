package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class ItemTagImpl {
    public static void applyTripwireIgnores(Entity entity, CallbackInfo ci) {
        if (entity instanceof ItemEntity itemEntity && itemEntity.getStack().isIn(CItemTags.TRIPWIRE_IGNORES))
            ci.cancel();
    }

    public static void applyRespawnAnchorCharges(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isIn(CItemTags.RESPAWN_ANCHOR_CHARGES))
            cir.setReturnValue(true);
    }
}
