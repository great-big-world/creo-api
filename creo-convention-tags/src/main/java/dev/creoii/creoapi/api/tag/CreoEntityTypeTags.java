package dev.creoii.creoapi.api.tag;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoEntityTypeTags {
    public static final TagKey<EntityType<?>> NO_CLIPPING_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "no_clipping_entities"));
    public static final TagKey<EntityType<?>> CACTUS_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/cactus"));
    public static final TagKey<EntityType<?>> SWEET_BERRY_BUSH_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/sweet_berry_bush"));
    public static final TagKey<EntityType<?>> DRIPSTONE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/dripstone"));
    public static final TagKey<EntityType<?>> LIGHTNING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/lightning"));
    public static final TagKey<EntityType<?>> DROWNING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/drowning"));
    public static final TagKey<EntityType<?>> MAGIC_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/magic"));
    public static final TagKey<EntityType<?>> FIRE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/fire"));
    public static final TagKey<EntityType<?>> WITHER_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/wither"));
    public static final TagKey<EntityType<?>> DRYOUT_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/dryout"));
    public static final TagKey<EntityType<?>> SUFFOCATION_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/suffocation"));
    public static final TagKey<EntityType<?>> STARVATION_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/starvation"));
    public static final TagKey<EntityType<?>> CRAMMING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/cramming"));
    public static final TagKey<EntityType<?>> FLY_INTO_WALL_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/fly_into_wall"));
    public static final TagKey<EntityType<?>> FALL_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/fall"));
    public static final TagKey<EntityType<?>> OUT_OF_WORLD_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/out_of_world"));
    public static final TagKey<EntityType<?>> GENERIC_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "damage_immune/generic"));
    /**
     * Entities that are ignored by Zoglins.
     */
    public static final TagKey<EntityType<?>> ZOGLIN_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "zoglin_ignores"));
    /**
     * Entities that do not activate traps such as Pressure Plates.
     */
    public static final TagKey<EntityType<?>> AVOIDS_TRAPS = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "avoids_traps"));
    /**
     * Entities which projectiles ignore collision with.
     */
    public static final TagKey<EntityType<?>> PROJECTILES_IGNORE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "projectiles_ignore"));
    public static final TagKey<EntityType<?>> WALKS_ON_WATER = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "walks_on_water"));
    public static final TagKey<EntityType<?>> WALKS_ON_LAVA = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "walks_on_lava"));
    /**
     * Entities which can be stood on like Boats.
     */
    public static final TagKey<EntityType<?>> COLLIDABLE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "collidable"));
    /**
     * Entities which are ignored by the Warden.
     */
    public static final TagKey<EntityType<?>> WARDEN_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "warden_ignores"));
    /**
     * Entities which Creepers run away from.
     */
    public static final TagKey<EntityType<?>> SCARES_CREEPERS = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "scares_creepers"));
    public static final TagKey<EntityType<?>> CAN_BREATHE_IN_WATER = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "can_breathe_in_water"));
    public static final TagKey<EntityType<?>> GLINTED = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "glinted"));
    public static final TagKey<EntityType<?>> UNDEAD = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "undead"));
    public static final TagKey<EntityType<?>> ARTHROPOD = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "arthropod"));
    public static final TagKey<EntityType<?>> AQUATIC = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "aquatic"));
    public static final TagKey<EntityType<?>> ILLAGER = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "illager"));
    /**
     * Entities which do not activate Tripwires.
     */
    public static final TagKey<EntityType<?>> TRIPWIRE_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "tripwire_ignores"));
    public static final TagKey<EntityType<?>> MILKABLE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "milkable"));
    /**
     * Entities which cannot be hooked by a Fishing Rod.
     */
    public static final TagKey<EntityType<?>> FISHING_ROD_CANNOT_HOOK = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "fishing_rod_cannot_hook"));

    public static final TagKey<EntityType<?>> BOSSES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "bosses"));
}
