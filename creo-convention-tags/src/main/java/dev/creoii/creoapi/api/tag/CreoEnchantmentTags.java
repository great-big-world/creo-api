package dev.creoii.creoapi.api.tag;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoEnchantmentTags {
    public static final TagKey<Enchantment> CURSED = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cursed"));
    public static final TagKey<Enchantment> TREASURE = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "treasure"));
    public static final TagKey<Enchantment> NOT_OFFERED_BY_LIBRARIANS = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "not_offered_by_librarians"));
    public static final TagKey<Enchantment> NOT_RANDOMLY_SELECTABLE = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "not_randomly_selectable"));
    public static final TagKey<Enchantment> GRINDSTONE_IGNORES = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "grindstone_ignores"));

    public static final TagKey<Enchantment> PROTECTION = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "protection"));
    public static final TagKey<Enchantment> SHARPNESS = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "sharpness"));
}
