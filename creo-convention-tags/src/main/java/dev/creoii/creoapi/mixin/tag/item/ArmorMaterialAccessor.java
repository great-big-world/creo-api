package dev.creoii.creoapi.mixin.tag.item;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(ArmorMaterial.class)
public interface ArmorMaterialAccessor {
    @Accessor("repairIngredient")
    void setRepairIngredient(Supplier<Ingredient> repairIngredient);
}
