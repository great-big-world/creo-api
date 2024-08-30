package dev.creoii.creoapi.api.block;

import dev.creoii.creoapi.impl.block.BlockImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class CreoBlockApi implements ModInitializer {
    public static final String NAMESPACE = "creo_api";

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playS2C().register(BlockImpl.LookAtBlock.PACKET_ID, BlockImpl.LookAtBlock.PACKET_CODEC);
    }
}
