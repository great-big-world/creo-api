package dev.creoii.creoapi.api.tag;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoEnchantmentTags {
    public static final TagKey<Enchantment> GRINDSTONE_IGNORES = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(CreoConventionTags.COMMON_NAMESPACE, "grindstone_ignores"));

    public static final TagKey<Enchantment> PROTECTION = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(CreoConventionTags.COMMON_NAMESPACE, "protection"));
    public static final TagKey<Enchantment> SHARPNESS = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(CreoConventionTags.COMMON_NAMESPACE, "sharpness"));
}
