package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

public final class ItemTagImpl {
    public static void applyTripwireIgnores(Entity entity, CallbackInfo ci) {
        if (entity instanceof ItemEntity itemEntity && itemEntity.getStack().isIn(CreoItemTags.TRIPWIRE_IGNORES))
            ci.cancel();
    }

    public static void applyRespawnAnchorCharges(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isIn(CreoItemTags.RESPAWN_ANCHOR_CHARGES))
            cir.setReturnValue(true);
    }

    public static void applyHopperIgnores(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.isIn(CreoItemTags.HOPPER_IGNORES))
            cir.setReturnValue(stack);
    }

    public static Ingredient applyDuplicatesAllays() {
        return Ingredient.fromTag(CreoItemTags.DUPLICATES_ALLAYS);
    }

    public static Predicate<ItemEntity> applyDolphinIgnores() {
        return itemEntity -> {
            return !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.isTouchingWater() && !itemEntity.getStack().isIn(CreoItemTags.DOLPHIN_IGNORES);
        };
    }

    public static Ingredient applyFuelsFurnaceMinecarts() {
        return Ingredient.fromTag(CreoItemTags.FUELS_FURNACE_MINECARTS);
    }

    public static boolean applyRepairsIronGolems(ItemStack stack) {
        return stack.isIn(CreoItemTags.REPAIRS_IRON_GOLEMS);
    }

    public static void applyDisablesShield(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (livingEntity.getMainHandStack().isIn(CreoItemTags.DISABLES_SHIELD))
            cir.setReturnValue(true);
    }
}
