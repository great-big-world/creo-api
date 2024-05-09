package dev.creoii.creoapi.api.item;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.impl.item.util.PacketCodecHelper;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.HungerConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public record CreoFoodComponent(int nutrition, float saturation, boolean canAlwaysEat, boolean canSprintEat, float eatSeconds, boolean healsHealth, List<FoodComponent.StatusEffectEntry> effects) {
    public static final Codec<CreoFoodComponent> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                Codecs.NONNEGATIVE_INT.fieldOf("nutrition").forGetter(CreoFoodComponent::nutrition),
                Codec.FLOAT.fieldOf("saturation").forGetter(CreoFoodComponent::saturation),
                Codec.BOOL.optionalFieldOf("can_always_eat", false).forGetter(CreoFoodComponent::canAlwaysEat),
                Codec.BOOL.optionalFieldOf("can_sprint_eat", false).forGetter(CreoFoodComponent::canSprintEat),
                Codecs.POSITIVE_FLOAT.optionalFieldOf("eat_seconds", 1.6f).forGetter(CreoFoodComponent::eatSeconds),
                Codec.BOOL.optionalFieldOf("heals_health", false).forGetter(CreoFoodComponent::healsHealth),
                FoodComponent.StatusEffectEntry.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(CreoFoodComponent::effects)
        ).apply(instance, CreoFoodComponent::new);
    });
    public static final PacketCodec<RegistryByteBuf, CreoFoodComponent> PACKET_CODEC = PacketCodecHelper.tuple7(
            PacketCodecs.VAR_INT, CreoFoodComponent::nutrition,
            PacketCodecs.FLOAT, CreoFoodComponent::saturation,
            PacketCodecs.BOOL, CreoFoodComponent::canAlwaysEat,
            PacketCodecs.BOOL, CreoFoodComponent::canSprintEat,
            PacketCodecs.FLOAT, CreoFoodComponent::eatSeconds,
            PacketCodecs.BOOL, CreoFoodComponent::healsHealth,
            FoodComponent.StatusEffectEntry.PACKET_CODEC.collect(PacketCodecs.toList()), CreoFoodComponent::effects,
            CreoFoodComponent::new);

    public static CreoFoodComponent copyOf(FoodComponent foodComponent) {
        return new CreoFoodComponent(foodComponent.nutrition(), foodComponent.saturation(), foodComponent.canAlwaysEat(), false, foodComponent.eatSeconds(), false, foodComponent.effects());
    }

    public int getEatTicks() {
        return (int) (eatSeconds * 20f);
    }

    public static class Builder {
        private int nutrition;
        private float saturationModifier;
        private float eatSeconds = 1.6f;
        private boolean canAlwaysEat;
        private boolean canSprintEat;
        private boolean healsHealth;
        private final ImmutableList.Builder<FoodComponent.StatusEffectEntry> effects = ImmutableList.builder();

        public Builder() {}

        public Builder nutrition(int nutrition) {
            this.nutrition = nutrition;
            return this;
        }

        public Builder saturationModifier(float saturationModifier) {
            this.saturationModifier = saturationModifier;
            return this;
        }

        public Builder eatSeconds(float eatSeconds) {
            this.eatSeconds = eatSeconds;
            return this;
        }

        public Builder canAlwaysEat() {
            canAlwaysEat = true;
            return this;
        }

        public Builder canSprintEat() {
            canSprintEat = true;
            return this;
        }

        public Builder healsHealth() {
            healsHealth = true;
            return this;
        }

        public Builder snack() {
            eatSeconds = .8f;
            return this;
        }

        public Builder statusEffect(StatusEffectInstance effect, float chance) {
            effects.add(new FoodComponent.StatusEffectEntry(effect, chance));
            return this;
        }

        public CreoFoodComponent build() {
            float f = HungerConstants.calculateSaturation(nutrition, saturationModifier);
            return new CreoFoodComponent(nutrition, f, canAlwaysEat, canSprintEat, eatSeconds, healsHealth, effects.build());
        }
    }
}
