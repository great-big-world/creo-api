package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItem;
import dev.creoii.creoapi.api.item.CreoItemApi;
import dev.creoii.creoapi.api.item.ItemEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class CreoItemImpl {
    public static void applyAttackThroughBlockClient(MinecraftClient client) {
        if (client.interactionManager != null && client.getCameraEntity() != null) {
            double d = client.interactionManager.getReachDistance() * 2.5d;
            Vec3d vec3d = client.getCameraEntity().getCameraPosVec(1f);
            Vec3d vec3d2 = client.getCameraEntity().getRotationVec(1f);
            EntityHitResult xrayResult = ProjectileUtil.raycast(client.getCameraEntity(), vec3d, vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d), client.getCameraEntity().getBoundingBox().stretch(vec3d2.multiply(d)).expand(1d, 1d, 1d), (entityx) -> {
                return !entityx.isSpectator() && entityx.canHit();
            }, d);
            if (client.player != null && xrayResult != null) {
                ItemStack stack = client.player.getStackInHand(client.player.getActiveHand());
                if (stack.getItem() instanceof CreoItem creoItem && creoItem.canAttackThroughBlock(client.player, stack, xrayResult.getEntity())) {
                    ItemEvents.ATTACK_THROUGH_BLOCK.invoker().onAttackThroughBlock(client.player, stack, xrayResult.getEntity());
                    creoItem.onAttackThroughBlock(client.player, stack, xrayResult.getEntity());
                    ClientPlayNetworking.send(CreoItemApi.ATTACK_THROUGH_BLOCK_PACKET_ID, getAttackThroughBlockData(xrayResult));
                }
            }
        }
    }

    public static void applyItemAttack(MinecraftClient client) {
        if (client.player == null)
            return;
        ItemStack stack = client.player.getStackInHand(client.player.getActiveHand());
        if (stack.getItem() instanceof CreoItem creoItem && client.crosshairTarget != null) {
            creoItem.onAttack(client.player, stack, client.crosshairTarget.getType());
            ClientPlayNetworking.send(CreoItemApi.ITEM_ATTACK_PACKET_ID, getItemAttackData(client));
        }
    }

    private static PacketByteBuf getAttackThroughBlockData(EntityHitResult xrayResult) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(xrayResult.getEntity().getId());
        return buf;
    }

    private static PacketByteBuf getItemAttackData(MinecraftClient client) {
        PacketByteBuf buf = PacketByteBufs.create();
        HitResult hitResult = client.crosshairTarget;
        if (hitResult != null)
            buf.writeInt(hitResult.getType().ordinal());
        else buf.writeInt(-1);
        return buf;
    }
}
