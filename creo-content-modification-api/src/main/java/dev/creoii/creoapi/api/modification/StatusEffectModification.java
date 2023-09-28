package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.StatusEffectModificationImpl;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public interface StatusEffectModification {
    StatusEffectModification INSTANCE = new StatusEffectModificationImpl();

    void setCategory(StatusEffect statusEffect, StatusEffectCategory category);

    void setColor(StatusEffect statusEffect, int color);
}
