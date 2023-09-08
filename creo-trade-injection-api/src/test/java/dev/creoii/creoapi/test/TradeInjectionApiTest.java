package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.tradeinjection.VillagerTradeInjection;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class TradeInjectionApiTest implements ModInitializer {
    @Override
    public void onInitialize() {
        VillagerTradeInjection.INSTANCE.inject(VillagerProfession.LIBRARIAN, 1, new TradeOffers.SellItemFactory(Blocks.BEACON, 1, 1, 1, 1));
        VillagerTradeInjection.INSTANCE.injectWanderingTrader(1, new TradeOffers.SellItemFactory(Blocks.BEACON, 1, 1, 1, 1));
    }
}
