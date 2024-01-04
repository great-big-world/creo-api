package dev.creoii.creoapi.api.tag;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoBlockTags {
    /**
     * Blocks that can be broken by a Ravager.
     */
    public static final TagKey<Block> RAVAGER_BREAKABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "ravager_breakable"));
    public static final TagKey<Block> BLOCKS_NO_CLIPPING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "blocks_no_clipping"));
    /**
     * Blocks that are ignored by a Beacon's beam.
     */
    public static final TagKey<Block> BEACON_BEAM_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "beacon_beam_ignores"));
    /**
     * Blocks that cause Campfires to emit signal fire smoke when placed underneath.
     */
    public static final TagKey<Block> SIGNAL_FIRE_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "signal_fire_base_blocks"));
    /**
     * Blocks that keep Coral blocks alive when touching.
     */
    public static final TagKey<Block> KEEPS_CORAL_ALIVE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "keeps_coral_alive"));
    /**
     * Blocks that keep Farmland moist when touching.
     */
    public static final TagKey<Block> KEEPS_FARMLAND_MOIST = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "keeps_farmland_moist"));
    public static final TagKey<Block> OBSERVER_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "observer_ignores"));
    /**
     * Blocks that won't damage Anvils when landed upon.
     */
    public static final TagKey<Block> ANVIL_SOFTENERS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "anvil_softeners"));
    public static final TagKey<Block> BRUSHABLE_SOFTENERS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "brushable_softeners"));
    /**
     * Blocks that Cocoa Beans can be placed and grown on.
     */
    public static final TagKey<Block> COCOA_BEANS_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cocoa_beans_plantable_on"));
    public static final TagKey<Block> SUGAR_CANE_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "sugar_cane_plantable_on"));
    /**
     * Blocks that Cacti can be placed and grown on.
     */
    public static final TagKey<Block> CACTUS_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cactus_plantable_on"));
    public static final TagKey<Block> WITHER_ROSE_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "wither_rose_plantable_on"));
    /**
     * Blocks that Nether Wart can be placed and grown on.
     */
    public static final TagKey<Block> NETHER_WART_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "nether_wart_plantable_on"));
    public static final TagKey<Block> CHORUS_FRUIT_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "chorus_fruit_plantable_on"));
    /**
     * Blocks which activate Conduits when placed correctly.
     */
    public static final TagKey<Block> CONDUIT_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "conduit_frame_base_blocks"));
    public static final TagKey<Block> NETHER_PORTAL_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "nether_portal_frame_base_blocks"));
    public static final TagKey<Block> CAN_DRIP_THROUGH = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "can_drip_through"));
    /**
     * Blocks that are eaten by Sheep like {@link net.minecraft.block.Blocks#SHORT_GRASS}.
     */
    public static final TagKey<Block> EATEN_BY_SHEEP = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "eaten_by_sheep"));
    /**
     * Blocks which projectiles will ignore collision with.
     */
    public static final TagKey<Block> PROJECTILES_IGNORE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "projectiles_ignore"));
    /**
     * Blocks that Shulkers cannot teleport to.
     */
    public static final TagKey<Block> INVALID_FOR_SHULKER_TELEPORT = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "invalid_for_shulker_teleport"));
    /**
     * Blocks which are mined very quickly by Shears.
     */
    public static final TagKey<Block> SHEARS_VERY_EFFICIENT = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "shears_very_efficient"));
    /**
     * Blocks which are mined quickly by Shears.
     */
    public static final TagKey<Block> SHEARS_LESS_EFFICIENT = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "shears_less_efficient"));
    /**
     * Blocks which will not stop weather rendering below them.
     */
    public static final TagKey<Block> WEATHER_RENDER_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "weather_render_ignores"));
}
