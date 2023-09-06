package dev.creoii.creoapi.api.food;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;

import java.util.List;

public class CreoFoodComponent extends FoodComponent {
    private final int eatSpeed;
    private final int eatDurability;

    public CreoFoodComponent(int hunger, float saturationModifier, int eatSpeed, int eatDurability, boolean meat, boolean alwaysEdible, boolean snack, List<Pair<StatusEffectInstance, Float>> statusEffects) {
        super(hunger, saturationModifier, meat, alwaysEdible, snack, statusEffects);
        this.eatSpeed = eatSpeed;
        this.eatDurability = eatDurability;
    }

    public static CreoFoodComponent copyOf(FoodComponent foodComponent) {
        return new CreoFoodComponent(foodComponent.getHunger(), foodComponent.getSaturationModifier(), foodComponent.isSnack() ? 16 : 32, 1, foodComponent.isMeat(), foodComponent.isAlwaysEdible(), foodComponent.isSnack(), foodComponent.getStatusEffects());
    }

    public int getEatSpeed() {
        return eatSpeed;
    }

    public boolean hasEatDurability() {
        return eatDurability > 1;
    }

    public int getEatDurability() {
        return eatDurability;
    }

    public static class Builder {
        private int hunger;
        private float saturationModifier;
        private int eatSpeed = 32;
        private int eatDurability = 1;
        private boolean meat;
        private boolean alwaysEdible;
        private boolean snack;
        private final List<Pair<StatusEffectInstance, Float>> statusEffects = Lists.newArrayList();

        public Builder() {}

        public Builder hunger(int hunger) {
            this.hunger = hunger;
            return this;
        }

        public Builder saturationModifier(float saturationModifier) {
            this.saturationModifier = saturationModifier;
            return this;
        }

        public Builder eatSpeed(int eatSpeed) {
            this.eatSpeed = eatSpeed;
            return this;
        }

        public Builder eatDurability(int eatDurability) {
            this.eatDurability = eatDurability;
            return this;
        }

        public Builder meat() {
            meat = true;
            return this;
        }

        public Builder alwaysEdible() {
            alwaysEdible = true;
            return this;
        }

        public Builder snack() {
            snack = true;
            eatSpeed = 16;
            return this;
        }

        public Builder statusEffect(StatusEffectInstance effect, float chance) {
            statusEffects.add(Pair.of(effect, chance));
            return this;
        }

        public CreoFoodComponent build() {
            return new CreoFoodComponent(hunger, saturationModifier, eatSpeed, eatDurability, meat, alwaysEdible, snack, statusEffects);
        }
    }
}
