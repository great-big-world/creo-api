package dev.creoii.creoapi.mixin.modification.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemAccessor {
    @Accessor("rarity")
    Rarity getRarity();

    @Accessor("rarity")
    void setRarity(Rarity rarity);

    @Accessor("maxCount")
    int getMaxCount();

    @Accessor("maxCount")
    void setMaxCount(int maxCount);

    @Accessor("maxDamage")
    int getMaxDamage();

    @Accessor("maxDamage")
    void setMaxDamage(int maxDamage);

    @Accessor("fireproof")
    boolean isFireproof();

    @Accessor("fireproof")
    void setFireproof(boolean fireproof);

    @Accessor("recipeRemainder")
    Item getRecipeRemainder();

    @Accessor("recipeRemainder")
    void setRecipeRemainder(Item recipeRemainder);

    @Accessor("foodComponent")
    FoodComponent getFoodComponent();

    @Accessor("foodComponent")
    void setFoodComponent(FoodComponent foodComponent);
}