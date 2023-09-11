package dev.creoii.creoapi.api.tag;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoBlockTags {
    public static final TagKey<Block> CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "chests"));
    public static final TagKey<Block> TRAPPED_CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "trapped_chests"));
    public static final TagKey<Block> RAVAGER_BREAKABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "ravager_breakable"));
    public static final TagKey<Block> BLOCKS_NO_CLIPPING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "blocks_no_clipping"));
    public static final TagKey<Block> BEACON_BEAM_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "beacon_beam_ignores"));
    public static final TagKey<Block> ATTRACTS_LIGHTNING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "attracts_lightning"));
    public static final TagKey<Block> SIGNAL_FIRE_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "signal_fire_base_blocks"));
    public static final TagKey<Block> KEEPS_CORAL_ALIVE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "keeps_coral_alive"));
    public static final TagKey<Block> KEEPS_FARMLAND_MOIST = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "keeps_farmland_moist"));
    public static final TagKey<Block> OBSERVER_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "observer_ignores"));
    public static final TagKey<Block> ANVIL_SOFTENERS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "anvil_softeners"));
    public static final TagKey<Block> BRUSHABLE_SOFTENERS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "brushable_softeners"));
    public static final TagKey<Block> COCOA_BEANS_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cocoa_beans_plantable_on"));
    public static final TagKey<Block> SUGAR_CANE_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "sugar_cane_plantable_on"));
    public static final TagKey<Block> CACTUS_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cactus_plantable_on"));
    public static final TagKey<Block> WITHER_ROSE_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "wither_rose_plantable_on"));
    public static final TagKey<Block> NETHER_WART_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "nether_wart_plantable_on"));
    public static final TagKey<Block> CHORUS_FRUIT_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "chorus_fruit_plantable_on"));
    public static final TagKey<Block> CONDUIT_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "conduit_frame_base_blocks"));
    public static final TagKey<Block> NETHER_PORTAL_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "nether_portal_frame_base_blocks"));
    public static final TagKey<Block> CAN_DRIP_THROUGH = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "can_drip_through"));
    public static final TagKey<Block> EATEN_BY_SHEEP = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "eaten_by_sheep"));
    public static final TagKey<Block> PROJECTILES_IGNORE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "projectiles_ignore"));
    public static final TagKey<Block> INVALID_FOR_SHULKER_TELEPORT = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "invalid_for_shulker_teleport"));
    public static final TagKey<Block> SHEARS_VERY_EFFICIENT = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "shears_very_efficient"));
    public static final TagKey<Block> SHEARS_LESS_EFFICIENT = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "shears_less_efficient"));

    public static final TagKey<Block> FURNACES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "furnaces"));
    public static final TagKey<Block> CONCRETE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "concrete"));
    public static final TagKey<Block> CONCRETE_POWDER = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "concrete_powder"));
    public static final TagKey<Block> GLAZED_TERRACOTTA = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glazed_terracotta"));
    public static final TagKey<Block> STAINED_GLASS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "stained_glass"));
    public static final TagKey<Block> STAINED_GLASS_PANES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "stained_glass_panes"));
    public static final TagKey<Block> GLASS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glass"));
    public static final TagKey<Block> GLASS_PANES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glass_panes"));
    public static final TagKey<Block> SANDSTONE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "sandstone"));
    public static final TagKey<Block> RED_SANDSTONE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "red_sandstone"));
    public static final TagKey<Block> MINERAL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "mineral_blocks"));
    public static final TagKey<Block> DEAD_CORAL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_coral_blocks"));
    public static final TagKey<Block> DEAD_WALL_CORALS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_wall_corals"));
    public static final TagKey<Block> DEAD_CORAL_PLANTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_coral_plants"));
    public static final TagKey<Block> DEAD_CORALS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_corals"));
}
