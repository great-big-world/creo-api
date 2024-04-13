package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItemApi;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.EntityHitResult;
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
            if (client.player != null) {
                ClientPlayNetworking.send(CreoItemApi.ATTACK_THROUGH_BLOCK_PACKET_ID, getAttackThroughBlockData(client, xrayResult));
            }
        }
    }

    private static PacketByteBuf getAttackThroughBlockData(MinecraftClient client, EntityHitResult xrayResult) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(xrayResult.getEntity().getId());
        return buf;
    }
}
