package dev.creoii.creoapi.api.tag;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CreoStatusEffectTags {
    public static final TagKey<StatusEffect> UNAFFECTED_BY_MILK = TagKey.of(RegistryKeys.STATUS_EFFECT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "unaffected_by_milk"));
    public static final TagKey<StatusEffect> CURES_ZOMBIE_VILLAGERS = TagKey.of(RegistryKeys.STATUS_EFFECT, new Identifier(CreoConventionTags.COMMON_NAMESPACE, "cures_zombie_villagers"));
}
