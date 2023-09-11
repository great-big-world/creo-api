package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;
import net.minecraft.advancement.AdvancementRewards;

public class RewardsInjector extends AdvancementInjectionRegistry.Injector {
    private final AdvancementRewards rewards;

    public RewardsInjector(AdvancementRewards rewards) {
        this.rewards = rewards;
    }

    public RewardsInjector(AdvancementRewards.Builder builder) {
        this(builder.build());
    }

    @Override
    public AdvancementInjectionRegistry.Type getType() {
        return AdvancementInjectionRegistry.Type.REWARDS;
    }

    public AdvancementRewards getRewards() {
        return rewards;
    }
}
