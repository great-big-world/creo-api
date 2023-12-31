package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.block.CreoBlock;
import dev.creoii.creoapi.api.block.Spreadable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.util.List;

public class BlockTest implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier("test", "test"), new TestBlock());
        Registry.register(Registries.BLOCK, new Identifier("test", "spreadable"), new TestSpreadableBlock());
    }

    public static class TestBlock extends Block implements CreoBlock {
        public TestBlock() {
            super(FabricBlockSettings.copy(Blocks.STONE));
        }

        @Override
        public void onLookAt(World world, BlockState state, BlockHitResult hitResult, Entity looker, double squaredDistance) {
            System.out.println("Looker: " + looker.getType().getTranslationKey());
        }
    }

    public static class TestSpreadableBlock extends Block implements Spreadable {
        public TestSpreadableBlock() {
            super(FabricBlockSettings.copy(Blocks.GRASS_BLOCK));
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
}
