package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.StatusEffectModificationImpl;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public interface StatusEffectModification {
    StatusEffectModification INSTANCE = new StatusEffectModificationImpl();

    /**
     * Set the status effect's category.
     * @param statusEffect the status effect
     * @param category the new {@link StatusEffectCategory}
     */
    void setCategory(StatusEffect statusEffect, StatusEffectCategory category);

    /**
     * Set the status effect's color.
     * @param statusEffect the status effect
     * @param color the new color
     */
    void setColor(StatusEffect statusEffect, int color);
}
