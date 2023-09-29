package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.StatusEffectModification;
import dev.creoii.creoapi.mixin.modification.StatusEffectAccessor;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class StatusEffectModificationImpl implements StatusEffectModification {
    @Override
    public void setCategory(StatusEffect statusEffect, StatusEffectCategory category) {
        ((StatusEffectAccessor) statusEffect).setCategory(category);
    }

    @Override
    public void setColor(StatusEffect statusEffect, int color) {
        ((StatusEffectAccessor) statusEffect).setColor(color);
    }
}
