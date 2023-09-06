package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {
    @Mutable @Shadow @Final private static Ingredient DUPLICATION_INGREDIENT;

    static {
        DUPLICATION_INGREDIENT = ItemTagImpl.applyDuplicatesAllays();
    }
}
