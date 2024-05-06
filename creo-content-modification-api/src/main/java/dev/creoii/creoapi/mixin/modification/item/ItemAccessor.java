package dev.creoii.creoapi.mixin.modification.item;

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
    void setMaxCount(int maxCount);

    @Accessor("maxDamage")
    void setMaxDamage(int maxDamage);

    @Accessor("fireproof")
    void setFireproof(boolean fireproof);

    @Accessor("recipeRemainder")
    void setRecipeRemainder(Item recipeRemainder);
}