package dev.creoii.creoapi.api.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoItemTags {
    public static final TagKey<Item> GLINTED = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glinted"));
    public static final TagKey<Item> EXPLOSION_IMMUNE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "explosion_immune"));
    public static final TagKey<Item> CACTUS_IMMUNE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cactus_immune"));
    public static final TagKey<Item> FIREPROOF = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "fireproof"));
    /**
     * Items which cannot be placed in an Item Frame.
     */
    public static final TagKey<Item> UNFRAMEABLE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "unframeable"));
    public static final TagKey<Item> GLOWS_SIGN = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glows_sign"));
    /**
     * Items which do not activate Tripwires when on the ground.
     */
    public static final TagKey<Item> TRIPWIRE_IGNORES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "tripwire_ignores"));
    /**
     * Items which cannot be moved by Hoppers.
     */
    public static final TagKey<Item> HOPPER_IGNORES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "hopper_ignores"));
    /**
     * Items which cannot be played with by Dolphins.
     */
    public static final TagKey<Item> DOLPHIN_IGNORES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dolphin_ignores"));
    /**
     * Items which cannot be picked up by Foxes.
     */
    public static final TagKey<Item> FOX_IGNORES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "fox_ignores"));
    /**
     * Items which duplicate Allays.
     */
    public static final TagKey<Item> DUPLICATES_ALLAYS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "duplicates_allays"));
    /**
     * Items which disable Shields on critical hits.
     */
    public static final TagKey<Item> DISABLES_SHIELD = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "disables_shield"));
    /**
     * Items which can be used to fuel enchanting.
     */
    public static final TagKey<Item> ENCHANTING_FUEL = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "enchanting_fuel"));
    /**
     * Items which can be used to fuel brewing.
     */
    public static final TagKey<Item> BREWING_FUEL = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "brewing_fuel"));
    /**
     * Items which repair damaged Iron Golems.
     */
    public static final TagKey<Item> REPAIRS_IRON_GOLEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs_iron_golems"));
    /**
     * Items which can be used to charge a Respawn Anchor.
     */
    public static final TagKey<Item> CHARGES_RESPAWN_ANCHOR = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "charges_respawn_anchor"));
    /**
     * Items which can be used to fuel a Furnace Minecart.
     */
    public static final TagKey<Item> FUELS_FURNACE_MINECARTS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "fuels_furnace_minecarts"));

    public static final TagKey<Item> REPAIRS_LEATHER = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/leather"));
    public static final TagKey<Item> REPAIRS_WOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/wood"));
    public static final TagKey<Item> REPAIRS_CHAINMAIL = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/chainmail"));
    public static final TagKey<Item> REPAIRS_STONE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/stone"));
    public static final TagKey<Item> REPAIRS_IRON = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/iron"));
    public static final TagKey<Item> REPAIRS_GOLD = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/gold"));
    public static final TagKey<Item> REPAIRS_DIAMOND = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/diamond"));
    public static final TagKey<Item> REPAIRS_NETHERITE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/netherite"));
    public static final TagKey<Item> REPAIRS_TURTLE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/turtle"));

    public static final TagKey<Item> BREEDS_SHEEP = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/sheep"));
    public static final TagKey<Item> BREEDS_COW = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/cow"));
    public static final TagKey<Item> BREEDS_PIG = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/pig"));
    public static final TagKey<Item> BREEDS_CHICKEN = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/chicken"));
    public static final TagKey<Item> BREEDS_GOAT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/goat"));
    public static final TagKey<Item> BREEDS_RABBIT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/rabbit"));
    public static final TagKey<Item> BREEDS_PANDA = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/panda"));
    public static final TagKey<Item> BREEDS_CAMEL = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/camel"));
    public static final TagKey<Item> BREEDS_FROG = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/frog"));
    public static final TagKey<Item> BREEDS_CAT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/cat"));
    public static final TagKey<Item> BREEDS_HORSE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/horse"));
    public static final TagKey<Item> BREEDS_DONKEY = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/donkey"));
    public static final TagKey<Item> BREEDS_MULE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/mule"));
    public static final TagKey<Item> BREEDS_BEE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/bee"));
    public static final TagKey<Item> BREEDS_LLAMA = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/llama"));
    public static final TagKey<Item> BREEDS_POLAR_BEAR = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/polar_bear"));
    public static final TagKey<Item> BREEDS_STRIDER = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/strider"));
    public static final TagKey<Item> BREEDS_TURTLE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/turtle"));
    public static final TagKey<Item> BREEDS_WOLF = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/wolf"));
    public static final TagKey<Item> BREEDS_OCELOT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/ocelot"));
    public static final TagKey<Item> BREEDS_HOGLIN = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "breeds/hoglin"));

    public static final TagKey<Item> GEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "gems"));
    public static final TagKey<Item> RAW_METALS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "raw_metals"));
    public static final TagKey<Item> INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "ingots"));
    public static final TagKey<Item> NUGGETS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "nuggets"));
    public static final TagKey<Item> MINERALS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "minerals"));
    public static final TagKey<Item> FRUIT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "fruit"));
    public static final TagKey<Item> RAW_MEAT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "raw_meat"));
    public static final TagKey<Item> COOKED_MEAT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cooked_meat"));
    public static final TagKey<Item> MEAT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "meat"));
    public static final TagKey<Item> SEEDS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "seeds"));
    public static final TagKey<Item> HELMETS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "helmets"));
    public static final TagKey<Item> CHESTPLATES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "chestplates"));
    public static final TagKey<Item> LEGGINGS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "leggings"));
    public static final TagKey<Item> BOOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "boots"));
    public static final TagKey<Item> ARMOR = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "armor"));
    public static final TagKey<Item> WEAPONS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "weapons"));
    public static final TagKey<Item> RANGED_WEAPONS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "ranged_weapons"));
    public static final TagKey<Item> MELEE_WEAPONS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "melee_weapons"));
    public static final TagKey<Item> EQUIPMENT = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "equipment"));

    public static final TagKey<Item> CONCRETE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "concrete"));
    public static final TagKey<Item> CONCRETE_POWDER = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "concrete_powder"));
    public static final TagKey<Item> GLAZED_TERRACOTTA = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glazed_terracotta"));
    public static final TagKey<Item> STAINED_GLASS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "stained_glass"));
    public static final TagKey<Item> STAINED_GLASS_PANES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "stained_glass_panes"));
    public static final TagKey<Item> GLASS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glass"));
    public static final TagKey<Item> GLASS_PANES = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glass_panes"));
    public static final TagKey<Item> SANDSTONE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "sandstone"));
    public static final TagKey<Item> RED_SANDSTONE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "red_sandstone"));
    public static final TagKey<Item> MINERAL_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "mineral_blocks"));
    public static final TagKey<Item> DEAD_CORAL_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_coral_blocks"));
    public static final TagKey<Item> DEAD_CORAL_PLANTS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_coral_plants"));
    public static final TagKey<Item> DEAD_CORALS = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "dead_corals"));
}
