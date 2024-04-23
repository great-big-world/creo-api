package dev.creoii.creoapi.api.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class CreoItemApi implements ModInitializer {
    public static final Identifier ATTACK_THROUGH_BLOCK_PACKET_ID = new Identifier("creo", "attack_through_block");
    public static final Identifier ITEM_ATTACK_PACKET_ID = new Identifier("creo", "item_attack");

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(ATTACK_THROUGH_BLOCK_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            server.execute(() -> {
                if (stack.getItem() instanceof CreoItem creoItem) {
                    Entity entity = player.getWorld().getEntityById(entityId);
                    if (entity != null && creoItem.canAttackThroughBlock(player, stack, entity)) {
                        player.attack(entity);
                        ItemEvents.ATTACK_THROUGH_BLOCK.invoker().onAttackThroughBlock(player, stack, entity);
                        creoItem.onAttackThroughBlock(player, stack, entity);
                    }
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(ITEM_ATTACK_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            int hitResult = buf.readInt();
            System.out.println("index " + buf.readerIndex());
            System.out.println("is readable " + buf.isReadable());
            System.out.println("bytes " + buf.readableBytes());
            Vec3d pos = buf.readVec3d();
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            server.execute(() -> {
                if (stack.getItem() instanceof CreoItem creoItem) {
                    creoItem.onAttack(player, stack, hitResult == -1 ? null : HitResult.Type.values()[hitResult], pos);
                }
            });
        });
    }
}
