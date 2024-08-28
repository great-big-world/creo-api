package dev.creoii.creoapi.api.block.client;

import dev.creoii.creoapi.api.block.CreoBlock;
import dev.creoii.creoapi.impl.block.BlockImpl;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class CreoBlockApiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playS2C().register(BlockImpl.LookAtBlock.PACKET_ID, BlockImpl.LookAtBlock.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(BlockImpl.CollideAdjacent.PACKET_ID, BlockImpl.CollideAdjacent.PACKET_CODEC);

        ClientPlayNetworking.registerGlobalReceiver(BlockImpl.LookAtBlock.PACKET_ID, (payload, context) -> {
            ClientWorld world = context.client().world;
            if (world != null) {
                BlockHitResult hitResult = payload.hitResult();
                double distance = payload.distance();
                Entity entity = world.getEntityById(payload.entityId());
                BlockState state = world.getBlockState(hitResult.getBlockPos());
                context.client().execute(() -> {
                    if (state.getBlock() instanceof CreoBlock creoBlock) {
                        creoBlock.onLookedAt(world, state, hitResult, entity, distance);
                    }
                });
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(BlockImpl.CollideAdjacent.PACKET_ID, (payload, context) -> {
            ClientWorld world = context.client().world;
            if (world != null) {
                BlockPos pos = payload.pos();
                Entity entity = world.getEntityById(payload.entityId());
                BlockState state = world.getBlockState(pos);
                context.client().execute(() -> {
                    if (state.getBlock() instanceof CreoBlock creoBlock && creoBlock.canEntityCollideAdjacent(entity, state, pos)) {
                        creoBlock.onAdjacentEntityCollision(entity, state, pos);
                    }
                });
            }
        });
    }
}
