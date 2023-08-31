package dev.creoii.creoapi.api.advancement;

import net.minecraft.advancement.AdvancementRewards;

public class RewardsInjector extends AdvancementInjector.Injector {
    private final AdvancementRewards rewards;

    public RewardsInjector(AdvancementRewards rewards) {
        this.rewards = rewards;
    }

    public RewardsInjector(AdvancementRewards.Builder builder) {
        this(builder.build());
    }

    @Override
    public AdvancementInjector.Type getType() {
        return AdvancementInjector.Type.REWARDS;
    }

    public AdvancementRewards getRewards() {
        return rewards;
    }
}
