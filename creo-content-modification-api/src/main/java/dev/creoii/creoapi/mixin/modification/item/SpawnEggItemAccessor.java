package dev.creoii.creoapi.mixin.modification.item;

import net.minecraft.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpawnEggItem.class)
public interface SpawnEggItemAccessor {
    @Accessor("primaryColor")
    void setPrimaryColor(int primaryColor);

    @Accessor("secondaryColor")
    void setSecondaryColor(int secondaryColor);
}
