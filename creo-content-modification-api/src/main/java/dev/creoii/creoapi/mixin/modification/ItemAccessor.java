package dev.creoii.creoapi.mixin.modification;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemAccessor {
    @Accessor("rarity")
    Rarity getRarity();

    @Accessor("maxCount")
    int getMaxCount();

    @Accessor("maxDamage")
    int getMaxDamage();

    @Accessor("fireproof")
    boolean isFireproof();

    @Accessor("recipeRemainder")
    Item getRecipeRemainder();

    @Accessor("foodComponent")
    FoodComponent getFoodComponent();
}