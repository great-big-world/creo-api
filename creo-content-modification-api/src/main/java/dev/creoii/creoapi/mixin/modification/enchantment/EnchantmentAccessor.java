package dev.creoii.creoapi.mixin.modification.enchantment;

import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Enchantment.class)
public interface EnchantmentAccessor {
    @Accessor("rarity")
    void setRarity(Enchantment.Rarity rarity);
}
