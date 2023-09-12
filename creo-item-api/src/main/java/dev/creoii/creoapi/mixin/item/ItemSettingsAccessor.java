package dev.creoii.creoapi.mixin.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Settings.class)
public interface ItemSettingsAccessor {
    @Accessor("maxCount")
    int getMaxCount();

    @Accessor("maxDamage")
    int getMaxDamage();

    @Accessor("recipeRemainder")
    Item getRecipeRemainder();

    @Accessor("rarity")
    Rarity getRarity();

    @Accessor("foodComponent")
    FoodComponent getFoodComponent();

    @Accessor("fireproof")
    boolean isFireproof();

    @Accessor("maxCount")
    void setMaxCount(int maxCount);

    @Accessor("maxDamage")
    void setMaxDamage(int maxDamage);

    @Accessor("recipeRemainder")
    void setRecipeRemainder(Item recipeRemainder);

    @Accessor("rarity")
    void setRarity(Rarity rarity);

    @Accessor("foodComponent")
    void setFoodComponent(FoodComponent foodComponent);

    @Accessor("fireproof")
    void setFireproof(boolean fireproof);
}
