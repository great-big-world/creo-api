package dev.creoii.creoapi.mixin.tag.item;

import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ToolMaterials.class)
public interface ToolMaterialsAccessor {
    @SuppressWarnings("deprecation")
    @Accessor("repairIngredient")
    void setRepairIngredient(Lazy<Ingredient> repairIngredient);
}
