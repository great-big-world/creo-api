package dev.creoii.creoapi.api.item;

import dev.creoii.creoapi.impl.item.CreoItemImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class CreoItemApi implements ModInitializer {
    public static final String NAMESPACE = "creo";

    @Override
    public void onInitialize() {
        CreoDataComponentTypes.register();
        PayloadTypeRegistry.playC2S().register(CreoItemImpl.ItemAttack.PACKET_ID, CreoItemImpl.ItemAttack.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(CreoItemImpl.AttackThroughBlock.PACKET_ID, CreoItemImpl.AttackThroughBlock.PACKET_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(CreoItemImpl.AttackThroughBlock.PACKET_ID, (payload, context) -> {
            int entityId = payload.entityId();
            context.server().execute(() -> {
                ServerPlayerEntity serverPlayer = context.player();
                if (serverPlayer.getServer() != null) {
                    ItemStack stack = serverPlayer.getStackInHand(serverPlayer.getActiveHand());
                    if (stack.getItem() instanceof CreoItem creoItem) {
                        Entity entity = serverPlayer.getWorld().getEntityById(entityId);
                        if (entity != null && creoItem.canAttackThroughBlock(serverPlayer, stack, entity)) {
                            serverPlayer.attack(entity);
                            ItemEvents.ATTACK_THROUGH_BLOCK.invoker().onAttackThroughBlock(serverPlayer, stack, entity);
                            creoItem.onAttackThroughBlock(serverPlayer, stack, entity);
                        }
                    }
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(CreoItemImpl.ItemAttack.PACKET_ID, (payload, context) -> {
            int hitResult = payload.hitResultType();
            Vec3d pos = payload.pos();
            context.server().execute(() -> {
                ServerPlayerEntity serverPlayer = context.player();
                if (serverPlayer != null) {
                    ItemStack stack = serverPlayer.getStackInHand(serverPlayer.getActiveHand());
                    if (stack.getItem() instanceof CreoItem creoItem) {
                        creoItem.onAttack(serverPlayer, stack, hitResult == -1 ? null : HitResult.Type.values()[hitResult], pos);
                    }
                }
            });
        });
    }
}
