package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.shader.ShaderInteractions;
import dev.creoii.creoapi.api.shader.Shaders;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShaderInteractionTest implements ModInitializer {
    @Override
    public void onInitialize() {
        final Block shaderBlock = Registry.register(Registries.BLOCK, new Identifier("test", "shader_block"), new ShaderBlock());
        Registry.register(Registries.ITEM, new Identifier("test", "shader_block"), new BlockItem(shaderBlock, new FabricItemSettings()));
    }

    public static class ShaderBlock extends Block {
        public ShaderBlock() {
            super(Settings.copy(Blocks.STONE));
        }

        @Override
        @SuppressWarnings("deprecation")
        public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            if (world.isClient) {
                if (player.isSneaking()) {
                    Identifier id = ShaderInteractions.getCurrentPostProcessor();
                    if (id != null)
                        System.out.println(id);
                } else {
                    int i = world.getRandom().nextInt(4);
                    switch (i) {
                        case 0 -> ShaderInteractions.setCurrentPostProcessor(Shaders.POST_CREEPER);
                        case 1 -> ShaderInteractions.addPostProcessPass(Shaders.POST_ART);
                        case 2 -> ShaderInteractions.removePostProcessPass(Shaders.POST_ART);
                        case 3 -> ShaderInteractions.clearPostProcessors();
                    }
                }
                return ActionResult.SUCCESS;
            }
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }
}
