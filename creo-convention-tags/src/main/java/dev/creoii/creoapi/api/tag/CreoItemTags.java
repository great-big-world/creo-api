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

    /**
     * Items which can be used to repair Leather equipment.
     */
    public static final TagKey<Item> REPAIRS_LEATHER = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/leather"));
    /**
     * Items which can be used to repair Wooden equipment.
     */
    public static final TagKey<Item> REPAIRS_WOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/wood"));
    /**
     * Items which can be used to repair Chainmail equipment.
     */
    public static final TagKey<Item> REPAIRS_CHAINMAIL = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/chainmail"));
    /**
     * Items which can be used to repair Stone equipment.
     */
    public static final TagKey<Item> REPAIRS_STONE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/stone"));
    /**
     * Items which can be used to repair Iron equipment.
     */
    public static final TagKey<Item> REPAIRS_IRON = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/iron"));
    /**
     * Items which can be used to repair Golden equipment.
     */
    public static final TagKey<Item> REPAIRS_GOLD = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/gold"));
    /**
     * Items which can be used to repair Diamond equipment.
     */
    public static final TagKey<Item> REPAIRS_DIAMOND = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/diamond"));
    /**
     * Items which can be used to repair Netherite equipment.
     */
    public static final TagKey<Item> REPAIRS_NETHERITE = TagKey.of(RegistryKeys.ITEM, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "repairs/netherite"));
    /**
     * Items which can be used to repair Turtle Helmets.
     */
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
}
