package dev.creoii.creoapi.mixin.tag.item;

import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ArmorMaterials.class)
public interface ArmorMaterialsAccessor {
    @SuppressWarnings("deprecation")
    @Accessor("repairIngredientSupplier")
    void setRepairIngredientSupplier(Lazy<Ingredient> repairIngredientSupplier);
}
