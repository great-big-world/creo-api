package dev.creoii.creoapi.api.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CreoItemApi implements ModInitializer {
    public static final Identifier ATTACK_THROUGH_BLOCK_PACKET_ID = new Identifier("creo", "attack_through_block");

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
                        creoItem.onAttackThroughBlock(player, stack, entity);
                    }
                }
            });
        });
    }
}
