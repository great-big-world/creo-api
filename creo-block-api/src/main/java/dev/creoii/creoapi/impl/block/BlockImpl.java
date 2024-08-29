package dev.creoii.creoapi.impl.block;

import dev.creoii.creoapi.api.block.CreoBlock;
import dev.creoii.creoapi.api.block.CreoBlockApi;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;

@ApiStatus.Internal
public final class BlockImpl {
    public static void applyLookAtBlock(Entity entity) {
        if (!entity.getWorld().isClient) {
            HitResult hitResult = entity.raycast(64d, 0f, false);

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                BlockState state = entity.getWorld().getBlockState(blockHitResult.getBlockPos());

                if (state.getBlock() instanceof CreoBlock creoBlock) {
                    double distance = blockHitResult.squaredDistanceTo(entity);
                    ((ServerWorld) entity.getWorld()).getPlayers().forEach(serverPlayer -> {
                        ServerPlayNetworking.send(serverPlayer, new LookAtBlock(entity.getId(), blockHitResult, distance));
                    });
                    creoBlock.onLookedAt(entity.getWorld(), state, blockHitResult, entity, distance);
                }
            }
        }
    }

    public static void applyOnPlacedByStructure(ServerWorldAccess world, BlockPos pivot, StructurePlacementData placementData, Random random, StructureTemplate.StructureBlockInfo structureBlockInfo, BlockPos pos, FluidState fluidState, BlockState state, StructureTemplate structureTemplate) {
        if (state.getBlock() instanceof CreoBlock creoBlock) {
            creoBlock.onPlacedByStructure(world, pos, state, fluidState, random, pivot, structureTemplate, placementData, structureBlockInfo);
        }
    }

    public static void applyCollideAdjacent(Entity entity) {
        if (!entity.getWorld().isClient) {
            Optional<BlockPos> optionalPos = BlockPos.findClosest(entity.getBlockPos(), (int) (entity.getWidth() + .5f), (int) (entity.getHeight() + .5f), pos -> {
                return entity.getWorld().getBlockState(pos).getBlock() instanceof CreoBlock;
            });
            if (optionalPos.isPresent()) {
                BlockPos pos = optionalPos.get();
                BlockState state = entity.getWorld().getBlockState(pos);
                if (state.getBlock() instanceof CreoBlock creoBlock) {
                    if (creoBlock.canEntityCollideAdjacent(entity, state, pos)) {
                        creoBlock.onAdjacentEntityCollision(entity, state, pos);
                    }
                }
            }
        }
    }

    public record LookAtBlock(int entityId, BlockHitResult hitResult, double distance) implements CustomPayload {
        public static final CustomPayload.Id<LookAtBlock> PACKET_ID = new CustomPayload.Id<>(Identifier.of(CreoBlockApi.NAMESPACE, "look_at_block"));
        public static final PacketCodec<RegistryByteBuf, LookAtBlock> PACKET_CODEC = PacketCodec.of(LookAtBlock::write, LookAtBlock::new);

        public LookAtBlock(RegistryByteBuf buf) {
            this(buf.readVarInt(), buf.readBlockHitResult(), buf.readDouble());
        }

        public void write(RegistryByteBuf buf) {
            buf.writeVarInt(entityId);
            buf.writeBlockHitResult(hitResult);
            buf.writeDouble(distance);
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }
}