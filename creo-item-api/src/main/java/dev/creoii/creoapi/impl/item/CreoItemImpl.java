package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class CreoItemImpl {
    public static void updateAttackThroughBlock(MinecraftClient client, MutableObject<HitResult> xrayResult) {
        if (client.interactionManager != null && client.getCameraEntity() != null) {
            double d = client.interactionManager.getReachDistance() * 2.5d;
            Vec3d vec3d = client.getCameraEntity().getCameraPosVec(1f);
            Vec3d vec3d2 = client.getCameraEntity().getRotationVec(1f);
            xrayResult.setValue(ProjectileUtil.raycast(client.getCameraEntity(), vec3d, vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d), client.getCameraEntity().getBoundingBox().stretch(vec3d2.multiply(d)).expand(1d, 1d, 1d), (entityx) -> {
                return !entityx.isSpectator() && entityx.canHit();
            }, d));
        }
    }

    public static void applyAttackThroughBlock(MinecraftClient client, MutableObject<HitResult> xrayResult) {
        if (client.player != null && client.interactionManager != null) {
            ItemStack stack = client.player.getStackInHand(client.player.getActiveHand());
            if (stack.getItem() instanceof CreoItem creoItem && xrayResult.getValue() != null && xrayResult.getValue().getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) xrayResult.getValue();
                if (creoItem.canAttackThroughBlock(client, stack, entityHitResult.getEntity())) {
                    client.interactionManager.attackEntity(client.player, entityHitResult.getEntity());
                    creoItem.onAttackThroughBlock(client, stack, entityHitResult.getEntity());
                }
            }
        }
    }
}
