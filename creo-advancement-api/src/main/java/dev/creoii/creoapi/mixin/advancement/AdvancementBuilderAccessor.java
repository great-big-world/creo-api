package dev.creoii.creoapi.mixin.advancement;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Advancement.Builder.class)
public interface AdvancementBuilderAccessor {
    @Accessor("requirements")
    String[][] getRequirements();

    @Accessor("display")
    AdvancementDisplay getDisplay();

    @Accessor("requirements")
    void setRequirements(String[][] requirements);
}
