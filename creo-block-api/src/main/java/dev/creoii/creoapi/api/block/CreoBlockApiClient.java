package dev.creoii.creoapi.api.block;

import dev.creoii.creoapi.impl.block.BlockImpl;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;

public class CreoBlockApiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(BlockImpl.LookAtBlock.PACKET_ID, (payload, context) -> {
            int entityId = payload.entityId();
            BlockHitResult hitResult = payload.hitResult();
            double distance = payload.distance();
            context.client().execute(() -> {
                ClientWorld world = context.client().world;
                if (world != null) {
                    BlockState state = world.getBlockState(hitResult.getBlockPos());
                    if (state.getBlock() instanceof CreoBlock creoBlock) {
                        Entity entity = world.getEntityById(entityId);
                        creoBlock.onLookedAt(world, state, hitResult, entity, distance);
                    }
                }
            });
        });
    }
}
