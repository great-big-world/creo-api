package dev.creoii.creoapi.mixin.advancement;

import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AdvancementDisplay.class)
public interface AdvancementDisplayAccessor {
    @Accessor
    void setTitle(Text title);

    @Accessor
    void setDescription(Text description);

    @Accessor
    void setIcon(ItemStack icon);

    @Accessor
    void setBackground(Identifier backgroundId);

    @Accessor
    void setFrame(AdvancementFrame frame);

    @Accessor
    void setShowToast(boolean setShowToast);

    @Accessor
    void setAnnounceToChat(boolean announceToChat);

    @Accessor
    void setHidden(boolean hidden);
}
