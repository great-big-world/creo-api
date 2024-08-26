package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItem;
import dev.creoii.creoapi.api.item.CreoItemApi;
import dev.creoii.creoapi.api.item.ItemEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class CreoItemImpl {
    public static void applyAttackThroughBlockClient(MinecraftClient client) {
        if (client.player != null && client.getCameraEntity() != null) {
            double d = client.player.getEntityInteractionRange();
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
                    ClientPlayNetworking.send(new AttackThroughBlock(xrayResult.getEntity().getId()));
                }
            }
        }
    }

    public static void applyItemAttack(MinecraftClient client) {
        if (client.player == null)
            return;
        ItemStack stack = client.player.getStackInHand(client.player.getActiveHand());
        if (stack.getItem() instanceof CreoItem creoItem && client.crosshairTarget != null) {
            creoItem.onAttack(client.player, stack, client.crosshairTarget.getType(), client.crosshairTarget.getPos());
            ClientPlayNetworking.send(new ItemAttack(client.crosshairTarget.getType().ordinal(), client.crosshairTarget.getPos()));
        }
    }

    public record AttackThroughBlock(int entityId) implements CustomPayload {
        public static final CustomPayload.Id<AttackThroughBlock> PACKET_ID = new CustomPayload.Id<>(Identifier.of(CreoItemApi.NAMESPACE, "attack_through_block"));
        public static final PacketCodec<RegistryByteBuf, AttackThroughBlock> PACKET_CODEC = PacketCodec.of(AttackThroughBlock::write, AttackThroughBlock::new);

        public AttackThroughBlock(RegistryByteBuf buf) {
            this(buf.readVarInt());
        }

        public void write(RegistryByteBuf buf) {
            buf.writeVarInt(entityId);
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }

    public record ItemAttack(int hitResultType, Vec3d pos) implements CustomPayload {
        public static final CustomPayload.Id<ItemAttack> PACKET_ID = new CustomPayload.Id<>(Identifier.of(CreoItemApi.NAMESPACE, "item_attack"));
        public static final PacketCodec<RegistryByteBuf, ItemAttack> PACKET_CODEC = PacketCodec.of(ItemAttack::write, ItemAttack::new);

        public ItemAttack(RegistryByteBuf buf) {
            this(buf.readVarInt(), buf.readVec3d());
        }

        public void write(RegistryByteBuf buf) {
            buf.writeVarInt(hitResultType);
            buf.writeVec3d(pos);
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }
}
