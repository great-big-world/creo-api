package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectorRegistry;
import net.minecraft.advancement.AdvancementRewards;

public class RewardsInjector extends AdvancementInjectorRegistry.Injector {
    private final AdvancementRewards rewards;

    public RewardsInjector(AdvancementRewards rewards) {
        this.rewards = rewards;
    }

    public RewardsInjector(AdvancementRewards.Builder builder) {
        this(builder.build());
    }

    @Override
    public AdvancementInjectorRegistry.Type getType() {
        return AdvancementInjectorRegistry.Type.REWARDS;
    }

    public AdvancementRewards getRewards() {
        return rewards;
    }
}
