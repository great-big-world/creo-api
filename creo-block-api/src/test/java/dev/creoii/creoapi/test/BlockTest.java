package dev.creoii.creoapi.test;

import com.mojang.serialization.MapCodec;
import dev.creoii.creoapi.api.block.CreoBlock;
import dev.creoii.creoapi.api.block.Spreadable;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockTest implements ModInitializer, ClientModInitializer {
    private static final Block TEST_OVERLAY = new TestOverlayBlock();

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier("test", "test"), new TestBlock());
        Registry.register(Registries.BLOCK, new Identifier("test", "spreadable"), new TestSpreadableBlock());
        Registry.register(Registries.BLOCK, new Identifier("test", "adjacent_collider"), new TestColliderBlock());
        Registry.register(Registries.BLOCK, new Identifier("test", "overlay"), TEST_OVERLAY);
    }

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TEST_OVERLAY, RenderLayer.getCutout());
    }

    public static class TestBlock extends Block implements CreoBlock {
        public TestBlock() {
            super(AbstractBlock.Settings.copy(Blocks.STONE));
        }

        @Override
        public void onLookedAt(World world, BlockState state, BlockHitResult hitResult, Entity looker, double squaredDistance) {
            System.out.println("Looker: " + looker.getType().getTranslationKey());
        }

        @Override
        public void onPlacedByStructure(ServerWorldAccess world, BlockPos pos, BlockState state, FluidState fluidState, Random random, BlockPos pivot, StructureTemplate structureTemplate, StructurePlacementData placementData, StructureTemplate.StructureBlockInfo structureBlockInfo) {
            System.out.println("block " + world.getBlockState(pos).getBlock().getTranslationKey() + " placed by structure at " + pos.toShortString());
        }
    }

    public static class TestSpreadableBlock extends Block implements Spreadable {
        public TestSpreadableBlock() {
            super(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK));
        }

        @Override
        public Block getDead() {
            return Blocks.DIAMOND_BLOCK;
        }

        @Override
        public List<Spread> getSpreads() {
            return List.of(Spread.of(Blocks.DIRT));
        }
    }

    public static class TestColliderBlock extends PillarBlock implements CreoBlock {
        public TestColliderBlock() {
            super(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK));
        }

        @Override
        public boolean canEntityCollideAdjacent(Entity entity, BlockState state, BlockPos pos) {
            if (entity.isInPose(EntityPose.SWIMMING) || !entity.isSprinting() || entity.hasPassengers())
                return false;
            System.out.println("can collide");
            BlockPos difference = pos.subtract(entity.getBlockPos());
            if (difference.getY() > .5d || difference.getY() < -.5d || difference.equals(BlockPos.ORIGIN))
                return false;
            Vec3i facingVec = entity.getHorizontalFacing().getVector();
            return switch (state.get(AXIS)) {
                case X -> difference.getX() != 0d && difference.getX() == facingVec.getX() && difference.getZ() == 0d;
                case Z -> difference.getZ() != 0d && difference.getZ() == facingVec.getZ() && difference.getX() == 0d;
                case Y -> false;
            };
        }

        @Override
        public void onAdjacentEntityCollision(Entity entity, BlockState state, BlockPos pos) {
            System.out.println("colliding");
            entity.setSwimming(true);
            entity.setPose(EntityPose.SWIMMING);
        }
    }

    public static class TestOverlayBlock extends PlantBlock implements CreoBlock {
        protected TestOverlayBlock() {
            super(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS));
        }

        @Override
        public BlockState getOverlayState(BlockState state, BlockPos pos, Random random) {
            return Blocks.SNOW.getDefaultState();
        }

        @Override
        protected MapCodec<? extends PlantBlock> getCodec() {
            return null;
        }
    }
}
