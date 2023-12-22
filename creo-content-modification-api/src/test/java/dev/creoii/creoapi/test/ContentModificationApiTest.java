package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.modification.BlockModification;
import dev.creoii.creoapi.api.modification.EnchantmentModification;
import dev.creoii.creoapi.api.modification.ItemModification;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
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
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getHardness(Blocks.STONE)));
        BlockModification.INSTANCE.setHardness(Blocks.STONE, 50f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getHardness(Blocks.STONE)));
        LOGGER.info("pass");

        LOGGER.info("RESISTANCE");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getResistance(Blocks.OBSIDIAN)));
        BlockModification.INSTANCE.setResistance(Blocks.OBSIDIAN, 0f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getResistance(Blocks.OBSIDIAN)));
        LOGGER.info("pass");

        LOGGER.info("SOUND GROUP");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getSoundGroup(Blocks.DIAMOND_BLOCK).getPlaceSound().getId()));
        BlockModification.INSTANCE.setSoundGroup(Blocks.DIAMOND_BLOCK, BlockSoundGroup.AMETHYST_BLOCK);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getSoundGroup(Blocks.DIAMOND_BLOCK).getPlaceSound().getId()));
        LOGGER.info("pass");

        LOGGER.info("SLIPPERINESS");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getSlipperiness(Blocks.ICE)));
        BlockModification.INSTANCE.setSlipperiness(Blocks.ICE, .6f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getSlipperiness(Blocks.ICE)));
        LOGGER.info("pass");

        LOGGER.info("LUMINANCE");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getLuminance(Blocks.GLOWSTONE)));
        BlockModification.INSTANCE.setLuminance(Blocks.GLOWSTONE, 0);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getLuminance(Blocks.GLOWSTONE)));
        LOGGER.info("pass");

        LOGGER.info("MAP COLOR");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getMapColor(Blocks.RED_WOOL).color));
        BlockModification.INSTANCE.setMapColor(Blocks.RED_WOOL, MapColor.BLUE);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getMapColor(Blocks.RED_WOOL).color));
        LOGGER.info("pass");

        LOGGER.info("VELOCITY MULT");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getVelocityMultiplier(Blocks.DIRT_PATH)));
        BlockModification.INSTANCE.setVelocityMultiplier(Blocks.DIRT_PATH, 1.2f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getVelocityMultiplier(Blocks.DIRT_PATH)));
        LOGGER.info("pass");

        LOGGER.info("JUMP VELOCITY MULT");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getJumpVelocityMultiplier(Blocks.SLIME_BLOCK)));
        BlockModification.INSTANCE.setJumpVelocityMultiplier(Blocks.SLIME_BLOCK, 2f);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getJumpVelocityMultiplier(Blocks.SLIME_BLOCK)));
        LOGGER.info("pass");

        LOGGER.info("RANDOM TICKS");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.hasRandomTicks(Blocks.REDSTONE_ORE)));
        BlockModification.INSTANCE.setRandomTicks(Blocks.REDSTONE_ORE, false);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.hasRandomTicks(Blocks.REDSTONE_ORE)));
        LOGGER.info("pass");

        LOGGER.info("TOOL REQUIRED");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isToolRequired(Blocks.NETHERRACK)));
        BlockModification.INSTANCE.setToolRequired(Blocks.NETHERRACK, false);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isToolRequired(Blocks.NETHERRACK)));
        LOGGER.info("fail");

        LOGGER.info("LOOT TABLE ID");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getLootTableId(Blocks.OAK_PLANKS)));
        BlockModification.INSTANCE.setLootTableId(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS.getLootTableId());
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getLootTableId(Blocks.OAK_PLANKS)));
        LOGGER.info("fail");

        LOGGER.info("OPAQUE");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isOpaque(Blocks.IRON_BARS)));
        BlockModification.INSTANCE.setOpaque(Blocks.IRON_BARS, true);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isOpaque(Blocks.IRON_BARS)));
        LOGGER.info("fail");

        LOGGER.info("BURNABLE");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isBurnable(Blocks.OAK_LOG)));
        BlockModification.INSTANCE.setBurnable(Blocks.OAK_LOG, false);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isBurnable(Blocks.OAK_LOG)));
        LOGGER.info("fail");

        LOGGER.info("PISTON BEHAVIOR");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getPistonBehavior(Blocks.DIRT)));
        BlockModification.INSTANCE.setPistonBehavior(Blocks.DIRT, PistonBehavior.DESTROY);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getPistonBehavior(Blocks.DIRT)));
        LOGGER.info("pass");

        LOGGER.info("INSTRUMENT");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getInstrument(Blocks.GOLD_BLOCK)));
        BlockModification.INSTANCE.setInstrument(Blocks.GOLD_BLOCK, Instrument.DRAGON);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.getInstrument(Blocks.GOLD_BLOCK)));
        LOGGER.info("fail");

        LOGGER.info("BLOCK BREAK PARTICLES");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.hasBlockBreakParticles(Blocks.DIRT)));
        BlockModification.INSTANCE.setBlockBreakParticles(Blocks.DIRT, false);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.hasBlockBreakParticles(Blocks.DIRT)));
        LOGGER.info("pass");

        LOGGER.info("REPLACEABLE");
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isReplaceable(Blocks.SHORT_GRASS)));
        BlockModification.INSTANCE.setReplaceable(Blocks.SHORT_GRASS, false);
        LOGGER.info(String.valueOf(BlockModification.INSTANCE.isReplaceable(Blocks.SHORT_GRASS)));
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
        LOGGER.info("fail");

        LOGGER.info("---------- ENCHANTMENT ----------");

        LOGGER.info("RARITY");
        LOGGER.info(Enchantments.MENDING.getRarity().name());
        EnchantmentModification.INSTANCE.setRarity(Enchantments.MENDING, Enchantment.Rarity.COMMON);
        LOGGER.info(Enchantments.MENDING.getRarity().name());
        LOGGER.info("???");

        LOGGER.info("ACCEPTABLE ITEM");
        LOGGER.info(String.valueOf(Enchantments.MENDING.isAcceptableItem(Items.DIAMOND.getDefaultStack())));
        EnchantmentModification.INSTANCE.setAcceptableItems(Enchantments.MENDING, stack -> stack.isOf(Items.DIAMOND));
        LOGGER.info(String.valueOf(Enchantments.MENDING.isAcceptableItem(Items.DIAMOND.getDefaultStack())));
        LOGGER.info("???");

        LOGGER.info("MIN LEVEL");
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMinLevel()));
        EnchantmentModification.INSTANCE.setMinLevel(Enchantments.SILK_TOUCH, 2);
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMinLevel()));
        LOGGER.info("???");

        LOGGER.info("MAX LEVEL");
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMaxLevel()));
        EnchantmentModification.INSTANCE.setMaxLevel(Enchantments.SILK_TOUCH, 3);
        LOGGER.info(String.valueOf(Enchantments.SILK_TOUCH.getMaxLevel()));
        LOGGER.info("???");
    }
}