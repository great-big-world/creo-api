package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.modification.BlockModification;
import dev.creoii.creoapi.api.modification.StatusEffectModification;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentModificationApiTest implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ContentModificationApiTest.class);

    @Override
    public void onInitialize() {
        LOGGER.info("---------- BLOCK ----------");
        LOGGER.info("HARDNESS");
        LOGGER.info(String.valueOf(Blocks.STONE.getHardness()));
        BlockModification.INSTANCE.setHardness(Blocks.STONE, 50f);
        LOGGER.info(String.valueOf(Blocks.STONE.getHardness()));
        LOGGER.info("pass");

        LOGGER.info("RESISTANCE");
        LOGGER.info(String.valueOf(Blocks.OBSIDIAN.getBlastResistance()));
        BlockModification.INSTANCE.setResistance(Blocks.OBSIDIAN, 0f);
        LOGGER.info(String.valueOf(Blocks.OBSIDIAN.getBlastResistance()));
        LOGGER.info("pass");

        LOGGER.info("SOUND GROUP");
        LOGGER.info(String.valueOf(Blocks.DIAMOND_BLOCK.getDefaultState().getSoundGroup().getPlaceSound().getId()));
        BlockModification.INSTANCE.setSoundGroup(Blocks.DIAMOND_BLOCK, BlockSoundGroup.AMETHYST_BLOCK);
        LOGGER.info(String.valueOf(Blocks.DIAMOND_BLOCK.getDefaultState().getSoundGroup().getPlaceSound().getId()));
        LOGGER.info("pass");

        LOGGER.info("SLIPPERINESS");
        LOGGER.info(String.valueOf(Blocks.ICE.getSlipperiness()));
        BlockModification.INSTANCE.setSlipperiness(Blocks.ICE, .6f);
        LOGGER.info(String.valueOf(Blocks.ICE.getSlipperiness()));
        LOGGER.info("pass");

        LOGGER.info("LUMINANCE");
        LOGGER.info(String.valueOf(Blocks.GLOWSTONE.getDefaultState().getLuminance()));
        BlockModification.INSTANCE.setLuminance(Blocks.GLOWSTONE, 0);
        LOGGER.info(String.valueOf(Blocks.GLOWSTONE.getDefaultState().getLuminance()));
        LOGGER.info("pass");

        LOGGER.info("MAP COLOR");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getMapColor(Blocks.RED_WOOL).color));
        BlockModification.INSTANCE.setMapColor(Blocks.RED_WOOL, MapColor.BLUE);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getMapColor(Blocks.RED_WOOL).color));
        LOGGER.info("pass");

        LOGGER.info("VELOCITY MULT");
        LOGGER.info(String.valueOf(Blocks.DIRT_PATH.getVelocityMultiplier()));
        BlockModification.INSTANCE.setVelocityMultiplier(Blocks.DIRT_PATH, 1.2f);
        LOGGER.info(String.valueOf(Blocks.DIRT_PATH.getVelocityMultiplier()));
        LOGGER.info("pass");

        LOGGER.info("JUMP VELOCITY MULT");
        LOGGER.info(String.valueOf(Blocks.SLIME_BLOCK.getJumpVelocityMultiplier()));
        BlockModification.INSTANCE.setJumpVelocityMultiplier(Blocks.SLIME_BLOCK, 2f);
        LOGGER.info(String.valueOf(Blocks.SLIME_BLOCK.getJumpVelocityMultiplier()));
        LOGGER.info("pass");

        LOGGER.info("DYNAMIC BOUNDS");
        LOGGER.info(String.valueOf(Blocks.SHORT_GRASS.hasDynamicBounds()));
        BlockModification.INSTANCE.setDynamicBounds(Blocks.SHORT_GRASS, true);
        LOGGER.info(String.valueOf(Blocks.SHORT_GRASS.hasDynamicBounds()));
        LOGGER.info("pass");

        LOGGER.info("RANDOM TICKS");
        LOGGER.info(String.valueOf(Blocks.OAK_SAPLING.getDefaultState().hasRandomTicks()));
        BlockModification.INSTANCE.setRandomTicks(Blocks.OAK_SAPLING, false);
        LOGGER.info(String.valueOf(Blocks.OAK_SAPLING.getDefaultState().hasRandomTicks()));
        LOGGER.info("pass");

        LOGGER.info("TOOL REQUIRED");
        LOGGER.info(String.valueOf(Blocks.NETHERRACK.getDefaultState().isToolRequired()));
        BlockModification.INSTANCE.setToolRequired(Blocks.NETHERRACK, false);
        LOGGER.info(String.valueOf(Blocks.NETHERRACK.getDefaultState().isToolRequired()));
        LOGGER.info("pass");

        LOGGER.info("LOOT TABLE ID");
        LOGGER.info(String.valueOf(Blocks.OAK_PLANKS.getLootTableKey()));
        BlockModification.INSTANCE.setLootTableKey(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS.getLootTableKey());
        LOGGER.info(String.valueOf(Blocks.OAK_PLANKS.getLootTableKey()));
        LOGGER.info("fail");

        LOGGER.info("OPAQUE");
        LOGGER.info(String.valueOf(Blocks.IRON_BARS.getDefaultState().isOpaque()));
        BlockModification.INSTANCE.setOpaque(Blocks.IRON_BARS, true);
        LOGGER.info(String.valueOf(Blocks.IRON_BARS.getDefaultState().isOpaque()));
        LOGGER.info("pass");

        LOGGER.info("BURNABLE");
        LOGGER.info(String.valueOf(Blocks.OAK_LOG.getDefaultState().isBurnable()));
        BlockModification.INSTANCE.setBurnable(Blocks.OAK_LOG, false);
        LOGGER.info(String.valueOf(Blocks.OAK_LOG.getDefaultState().isBurnable()));
        LOGGER.info("pass");

        LOGGER.info("PISTON BEHAVIOR");
        LOGGER.info(String.valueOf(Blocks.DIRT.getDefaultState().getPistonBehavior()));
        BlockModification.INSTANCE.setPistonBehavior(Blocks.DIRT, PistonBehavior.DESTROY);
        LOGGER.info(String.valueOf(Blocks.DIRT.getDefaultState().getPistonBehavior()));
        LOGGER.info("pass");

        LOGGER.info("INSTRUMENT");
        LOGGER.info(String.valueOf(Blocks.GOLD_BLOCK.getDefaultState().getInstrument()));
        BlockModification.INSTANCE.setInstrument(Blocks.GOLD_BLOCK, Instrument.DRAGON);
        LOGGER.info(String.valueOf(Blocks.GOLD_BLOCK.getDefaultState().getInstrument()));
        LOGGER.info("pass");

        LOGGER.info("BLOCK BREAK PARTICLES");
        LOGGER.info(String.valueOf(Blocks.DIRT.getDefaultState().hasBlockBreakParticles()));
        BlockModification.INSTANCE.setBlockBreakParticles(Blocks.DIRT, false);
        LOGGER.info(String.valueOf(Blocks.DIRT.getDefaultState().hasBlockBreakParticles()));
        LOGGER.info("pass");

        LOGGER.info("REPLACEABLE");
        LOGGER.info(String.valueOf(Blocks.SHORT_GRASS.getDefaultState().isReplaceable()));
        BlockModification.INSTANCE.setReplaceable(Blocks.SHORT_GRASS, false);
        LOGGER.info(String.valueOf(Blocks.SHORT_GRASS.getDefaultState().isReplaceable()));
        LOGGER.info("pass");

        LOGGER.info("---------- STATUS EFFECT ----------");

        LOGGER.info("CATEGORY");
        LOGGER.info(StatusEffects.ABSORPTION.value().getCategory().name());
        StatusEffectModification.INSTANCE.setCategory(StatusEffects.ABSORPTION.value(), StatusEffectCategory.HARMFUL);
        LOGGER.info(StatusEffects.ABSORPTION.value().getCategory().name());
        LOGGER.info("pass");

        LOGGER.info("COLOR");
        LOGGER.info(String.valueOf(StatusEffects.INSTANT_DAMAGE.value().getColor()));
        StatusEffectModification.INSTANCE.setColor(StatusEffects.INSTANT_DAMAGE.value(), 0);
        LOGGER.info(String.valueOf(StatusEffects.INSTANT_DAMAGE.value().getColor()));
        LOGGER.info("pass");
    }
}