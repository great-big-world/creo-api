package dev.creoii.creoapi.api.advancement.injector;

import net.minecraft.advancement.AdvancementRewards;

public record RewardsInjector(AdvancementRewards rewards) implements Injector {
    public RewardsInjector(AdvancementRewards.Builder builder) {
        this(builder.build());
    }

    @Override
    public Type getType() {
        return Type.REWARDS;
    }
}
