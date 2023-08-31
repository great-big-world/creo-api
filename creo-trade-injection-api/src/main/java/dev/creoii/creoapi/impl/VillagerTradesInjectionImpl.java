package dev.creoii.creoapi.impl;

import dev.creoii.creoapi.api.VillagerTradesInjector;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.Arrays;

public class VillagerTradesInjectionImpl implements VillagerTradesInjector {
    @Override
    public void inject(VillagerProfession profession, int level, TradeOffers.Factory... tradeOffers) {
        Int2ObjectMap<TradeOffers.Factory[]> prevMap = TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(profession);
        TradeOffers.Factory[] prevOffers = prevMap.get(level);
        TradeOffers.Factory[] newOffers = Arrays.copyOf(prevOffers, prevOffers.length + tradeOffers.length);
        System.arraycopy(tradeOffers, 0, newOffers, prevOffers.length, tradeOffers.length);
        prevMap.replace(level, newOffers);
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.replace(profession, prevMap);
    }

    @Override
    public void injectWanderingTrader(int level, TradeOffers.Factory... tradeOffers) {
        Int2ObjectMap<TradeOffers.Factory[]> prevMap = TradeOffers.WANDERING_TRADER_TRADES;
        TradeOffers.Factory[] prevOffers = prevMap.get(level);
        TradeOffers.Factory[] newOffers = Arrays.copyOf(prevOffers, prevOffers.length + tradeOffers.length);
        System.arraycopy(tradeOffers, 0, newOffers, prevOffers.length, tradeOffers.length);
        prevMap.replace(level, newOffers);
    }
}
