package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.modification.BlockModification;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentModificationApiTest implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ContentModificationApiTest.class);

    @Override
    public void onInitialize() {
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getHardness(Blocks.STONE)));
        BlockModification.INSTANCE.setHardness(Blocks.STONE, 10f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getHardness(Blocks.STONE)));

        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getResistance(Blocks.OBSIDIAN)));
        BlockModification.INSTANCE.setResistance(Blocks.OBSIDIAN, 0f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getResistance(Blocks.OBSIDIAN)));
    }
}