package dev.creoii.creoapi.api.tradeinjection;

import dev.creoii.creoapi.impl.tradeinjection.VillagerTradeInjectionImpl;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public interface VillagerTradeInjection {
    VillagerTradeInjection INSTANCE = new VillagerTradeInjectionImpl();

    /**
     * @param profession The {@link VillagerProfession} to inject into.
     * @param level The villager level to inject into.
     * @param tradeOffers The trade offers to inject.
     * Injects one or more trade offers into the {@param profession} at the specified level.
     */
    void inject(VillagerProfession profession, int level, TradeOffers.Factory... tradeOffers);

    /**
     * @param level The villager level to inject into.
     * @param tradeOffers The trade offers to inject.
     * Injects one or more trade offsers into the Wandering Trader at the specified level.
     */
    void injectWanderingTrader(int level, TradeOffers.Factory... tradeOffers);
}
