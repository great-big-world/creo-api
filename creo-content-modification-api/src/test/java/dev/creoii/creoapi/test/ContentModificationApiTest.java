package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.modification.BlockModification;
import dev.creoii.creoapi.api.modification.EnchantmentModification;
import dev.creoii.creoapi.api.modification.ItemModification;
import dev.creoii.creoapi.api.modification.StatusEffectModification;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
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
        LOGGER.info(String.valueOf(Blocks.OAK_PLANKS.getLootTableId()));
        BlockModification.INSTANCE.setLootTableId(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS.getLootTableId());
        LOGGER.info(String.valueOf(Blocks.OAK_PLANKS.getLootTableId()));
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

        LOGGER.info("---------- ITEM ----------");

        LOGGER.info("RARITY");
        LOGGER.info(String.valueOf(ItemModification.INSTANCE.getRarity(Items.ENCHANTED_GOLDEN_APPLE)));
        ItemModification.INSTANCE.setRarity(Items.ENCHANTED_GOLDEN_APPLE, Rarity.COMMON);
        LOGGER.info(String.valueOf(ItemModification.INSTANCE.getRarity(Items.ENCHANTED_GOLDEN_APPLE)));
        LOGGER.info("pass");

        LOGGER.info("MAX COUNT");
        LOGGER.info(String.valueOf(Items.ENDER_PEARL.getMaxCount()));
        ItemModification.INSTANCE.setMaxCount(Items.ENDER_PEARL, 64);
        LOGGER.info(String.valueOf(Items.ENDER_PEARL.getMaxCount()));
        LOGGER.info("pass");

        LOGGER.info("MAX DAMAGE");
        LOGGER.info(String.valueOf(Items.WOODEN_HOE.getMaxDamage()));
        ItemModification.INSTANCE.setMaxDamage(Items.WOODEN_HOE, 1);
        LOGGER.info(String.valueOf(Items.WOODEN_HOE.getMaxDamage()));
        LOGGER.info("pass");

        LOGGER.info("FIREPROOF");
        LOGGER.info(String.valueOf(Items.NETHERITE_BLOCK.isFireproof()));
        ItemModification.INSTANCE.setFireproof(Items.NETHERITE_BLOCK, false);
        LOGGER.info(String.valueOf(Items.NETHERITE_BLOCK.isFireproof()));
        LOGGER.info("pass");

        LOGGER.info("RECIPE REMAINDER");
        LOGGER.info(String.valueOf(Items.MILK_BUCKET.getRecipeRemainder()));
        ItemModification.INSTANCE.setRecipeRemainder(Items.MILK_BUCKET, Items.WATER_BUCKET);
        LOGGER.info(String.valueOf(Items.MILK_BUCKET.getRecipeRemainder()));
        LOGGER.info("pass");

        LOGGER.info("FOOD COMPONENT");
        LOGGER.info(String.valueOf(Items.CARROT_ON_A_STICK.isFood()));
        ItemModification.INSTANCE.setFoodComponent(Items.CARROT_ON_A_STICK, new FoodComponent.Builder().hunger(2).build());
        LOGGER.info(String.valueOf(Items.CARROT_ON_A_STICK.isFood()));
        LOGGER.info("pass");

        LOGGER.info("---------- ENCHANTMENT ----------");

        LOGGER.info("RARITY");
        LOGGER.info(Enchantments.MENDING.getRarity().name());
        EnchantmentModification.INSTANCE.setRarity(Enchantments.MENDING, Enchantment.Rarity.COMMON);
        LOGGER.info(Enchantments.MENDING.getRarity().name());
        LOGGER.info("pass");

        LOGGER.info("ACCEPTABLE ITEM");
        LOGGER.info(String.valueOf(Enchantments.MENDING.isAcceptableItem(Items.DIAMOND.getDefaultStack())));
        EnchantmentModification.INSTANCE.setAcceptableItems(Enchantments.MENDING, stack -> stack.isOf(Items.DIAMOND));
        LOGGER.info(String.valueOf(Enchantments.MENDING.isAcceptableItem(Items.DIAMOND.getDefaultStack())));
        LOGGER.info("pass");

        LOGGER.info("MIN LEVEL");
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMinLevel()));
        EnchantmentModification.INSTANCE.setMinLevel(Enchantments.SILK_TOUCH, 2);
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMinLevel()));
        LOGGER.info("pass");

        LOGGER.info("MAX LEVEL");
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMaxLevel()));
        EnchantmentModification.INSTANCE.setMaxLevel(Enchantments.SILK_TOUCH, 3);
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMaxLevel()));
        LOGGER.info("pass");

        LOGGER.info("---------- STATUS EFFECT ----------");

        LOGGER.info("CATEGORY");
        LOGGER.info(StatusEffects.ABSORPTION.getCategory().name());
        StatusEffectModification.INSTANCE.setCategory(StatusEffects.ABSORPTION, StatusEffectCategory.HARMFUL);
        LOGGER.info(StatusEffects.ABSORPTION.getCategory().name());
        LOGGER.info("pass");

        LOGGER.info("COLOR");
        LOGGER.info(String.valueOf(StatusEffects.INSTANT_DAMAGE.getColor()));
        StatusEffectModification.INSTANCE.setColor(StatusEffects.INSTANT_DAMAGE, 0);
        LOGGER.info(String.valueOf(StatusEffects.INSTANT_DAMAGE.getColor()));
        LOGGER.info("pass");
    }
}