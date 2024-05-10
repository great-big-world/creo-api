package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoItemTags;
import dev.creoii.creoapi.mixin.tag.item.ArmorMaterialAccessor;
import dev.creoii.creoapi.mixin.tag.item.ToolMaterialsAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class ItemTagImpl {
    public static void applyTripwireIgnores(Entity entity, CallbackInfo ci) {
        if (entity instanceof ItemEntity itemEntity && itemEntity.getStack().isIn(CreoItemTags.TRIPWIRE_IGNORES))
            ci.cancel();
    }

    public static void applyRespawnAnchorCharges(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isIn(CreoItemTags.CHARGES_RESPAWN_ANCHOR))
            cir.setReturnValue(true);
    }

    public static void applyHopperIgnores(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.isIn(CreoItemTags.HOPPER_IGNORES))
            cir.setReturnValue(stack);
    }

    public static Ingredient applyDuplicatesAllays() {
        return Ingredient.fromTag(CreoItemTags.DUPLICATES_ALLAYS);
    }

    public static boolean applyDolphinIgnores(ItemEntity itemEntity) {
        return !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.isTouchingWater() && !itemEntity.getStack().isIn(CreoItemTags.DOLPHIN_IGNORES);
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

    public static void applyUnframeable(ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {
        if (stack.isIn(CreoItemTags.UNFRAMEABLE))
            cir.setReturnValue(ActionResult.PASS);
    }

    public static boolean applyFoxIgnores(ItemEntity itemEntity) {
        return !itemEntity.cannotPickup() && itemEntity.isAlive() && !itemEntity.getStack().isIn(CreoItemTags.FOX_IGNORES);
    }

    public static boolean applyBrewingFuel(ItemStack stack) {
        return stack.isIn(CreoItemTags.BREWING_FUEL);
    }

    public static Slot applyEnchantingFuel(ScreenHandler screenHandler, Slot slot) {
        return screenHandler.addSlot(new EnchantingFuelSlot(slot.inventory, 1, 35, 47));
    }

    public static boolean applyEnchantingFuelQuickMove(ItemStack stack) {
        return stack.isIn(CreoItemTags.ENCHANTING_FUEL);
    }

    public static boolean applyBlocksEndermanStare(ItemStack stack) {
        return stack.isIn(CreoItemTags.BLOCKS_ENDERMAN_STARE);
    }

    public static void applyArmorRepairIngredients() {
        ((ArmorMaterialAccessor) ArmorMaterials.LEATHER).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_LEATHER));
        ((ArmorMaterialAccessor) ArmorMaterials.CHAIN).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_CHAINMAIL));
        ((ArmorMaterialAccessor) ArmorMaterials.IRON).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_IRON));
        ((ArmorMaterialAccessor) ArmorMaterials.GOLD).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_GOLD));
        ((ArmorMaterialAccessor) ArmorMaterials.DIAMOND).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_DIAMOND));
        ((ArmorMaterialAccessor) ArmorMaterials.NETHERITE).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_NETHERITE));
        ((ArmorMaterialAccessor) ArmorMaterials.TURTLE).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_TURTLE));
    }

    public static void applyToolRepairIngredients() {
        ((ToolMaterialsAccessor) (Object) ToolMaterials.WOOD).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_WOOD));
        ((ToolMaterialsAccessor) (Object) ToolMaterials.STONE).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_STONE));
        ((ToolMaterialsAccessor) (Object) ToolMaterials.IRON).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_IRON));
        ((ToolMaterialsAccessor) (Object) ToolMaterials.GOLD).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_GOLD));
        ((ToolMaterialsAccessor) (Object) ToolMaterials.DIAMOND).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_DIAMOND));
        ((ToolMaterialsAccessor) (Object) ToolMaterials.NETHERITE).setRepairIngredient(() -> Ingredient.fromTag(CreoItemTags.REPAIRS_NETHERITE));
    }

    public static class EnchantingFuelSlot extends Slot {
        public EnchantingFuelSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isIn(CreoItemTags.ENCHANTING_FUEL);
        }
    }
}
