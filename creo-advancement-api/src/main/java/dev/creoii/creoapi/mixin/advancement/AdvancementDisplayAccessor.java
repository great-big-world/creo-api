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
    @Accessor("title")
    void setTitle(Text title);

    @Accessor("description")
    void setDescription(Text description);

    @Accessor("icon")
    void setIcon(ItemStack icon);

    @Accessor("background")
    void setBackground(Identifier background);

    @Accessor("frame")
    void setFrame(AdvancementFrame frame);

    @Accessor("showToast")
    void setShowToast(boolean setShowToast);

    @Accessor("announceToChat")
    void setAnnounceToChat(boolean announceToChat);

    @Accessor("hidden")
    void setHidden(boolean hidden);
}
