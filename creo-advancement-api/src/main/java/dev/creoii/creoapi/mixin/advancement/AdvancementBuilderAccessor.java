package dev.creoii.creoapi.mixin.advancement;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Advancement.Builder.class)
public interface AdvancementBuilderAccessor {
    @Accessor
    String[][] getRequirements();

    @Accessor
    AdvancementDisplay getDisplay();

    @Accessor
    void setRequirements(String[][] requirements);
}
