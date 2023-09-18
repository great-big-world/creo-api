package dev.creoii.creoapi.impl.collision.util;

import com.google.common.collect.AbstractIterator;
import dev.creoii.creoapi.api.collision.EntityBlockCollision;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.CuboidBlockIterator;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@ApiStatus.NonExtendable
public class EntityBlockCollisionSpliterator extends AbstractIterator<VoxelShape> {
    private final Box box;
    private final Entity entity;
    private final ShapeContext context;
    private final CuboidBlockIterator blockIterator;
    private final BlockPos.Mutable pos;
    private final VoxelShape boxShape;
    private final CollisionView world;
    @Nullable
    private BlockView chunk;
    private long chunkPos;
    private final Predicate<EntityBlockCollision.EntityBlockCollisionContext> contextPredicate;

    public EntityBlockCollisionSpliterator(CollisionView world, Entity entity, Box box, Predicate<EntityBlockCollision.EntityBlockCollisionContext> contextPredicate) {
        this.entity = entity;
        context = ShapeContext.of(entity);
        pos = new BlockPos.Mutable();
        boxShape = VoxelShapes.cuboid(box);
        this.world = world;
        this.box = box;
        int i = MathHelper.floor(box.minX - 1e-7) - 1;
        int j = MathHelper.floor(box.maxX + 1e-7) + 1;
        int k = MathHelper.floor(box.minY - 1e-7) - 1;
        int l = MathHelper.floor(box.maxY + 1e-7) + 1;
        int m = MathHelper.floor(box.minZ - 1e-7) - 1;
        int n = MathHelper.floor(box.maxZ + 1e-7) + 1;
        blockIterator = new CuboidBlockIterator(i, k, m, j, l, n);
        this.contextPredicate = contextPredicate;
    }

    @Nullable
    private BlockView getChunk(int x, int z) {
        int i = ChunkSectionPos.getSectionCoord(x);
        int j = ChunkSectionPos.getSectionCoord(z);
        long l = ChunkPos.toLong(i, j);
        if (chunk != null && chunkPos == l) return chunk;
        else {
            BlockView blockView = world.getChunkAsView(i, j);
            chunk = blockView;
            chunkPos = l;
            return blockView;
        }
    }

    protected VoxelShape computeNext() {
        while (true) {
            if (blockIterator.step()) {
                int i = blockIterator.getX();
                int j = blockIterator.getY();
                int k = blockIterator.getZ();
                int l = blockIterator.getEdgeCoordinatesCount();
                if (l == 3)
                    continue;

                BlockView chunk = getChunk(i, k);
                if (chunk == null)
                    continue;

                pos.set(i, j, k);
                BlockState state = chunk.getBlockState(pos);

                EntityBlockCollision.EntityBlockCollisionContext collisionContext = new EntityBlockCollision.EntityBlockCollisionContext(world, entity, pos, state, chunk);
                if (!contextPredicate.test(collisionContext))
                    continue;

                if (!state.shouldSuffocate(chunk, pos) || l == 1 && !state.exceedsCube() || l == 2 && !state.isOf(Blocks.MOVING_PISTON)) {
                    continue;
                }

                VoxelShape voxelShape = state.getCollisionShape(world, pos, context);
                if (voxelShape == VoxelShapes.fullCube()) {
                    if (!box.intersects(i, j, k, (double)i + 1d, (double)j + 1d, (double)k + 1d))
                        continue;
                    return voxelShape.offset(i, j, k);
                }

                VoxelShape voxelShape2 = voxelShape.offset(i, j, k);
                if (!VoxelShapes.matchesAnywhere(voxelShape2, boxShape, BooleanBiFunction.AND))
                    continue;
                return voxelShape2;
            }
            return endOfData();
        }
    }

    public List<VoxelShape> collectAll() {
        ArrayList<VoxelShape> collisions = new ArrayList<>();

        while (hasNext()) {
            collisions.add(next());
        }

        return collisions;
    }
}