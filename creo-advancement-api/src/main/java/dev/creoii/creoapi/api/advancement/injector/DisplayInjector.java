package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DisplayInjector extends AdvancementInjectionRegistry.Injector {
    @Nullable
    private final AdvancementDisplay display;

    @Nullable
    private Text title;
    @Nullable
    private Text description;
    @Nullable
    private ItemStack icon;
    @Nullable
    private Identifier background;
    @Nullable
    private AdvancementFrame frame;
    private boolean showToast;
    private boolean announceToChat;
    private boolean hidden;

    public DisplayInjector(@NotNull AdvancementDisplay display) {
        this.display = display;
    }

    public DisplayInjector(ItemConvertible icon, Text title, Text description, @Nullable Identifier background, AdvancementFrame frame, boolean showToast, boolean announceToChat, boolean hidden) {
        this(new AdvancementDisplay(new ItemStack(icon.asItem()), title, description, background, frame, showToast, announceToChat, hidden));
    }

    public DisplayInjector(ItemStack icon, Text title, Text description, @Nullable Identifier background, AdvancementFrame frame, boolean showToast, boolean announceToChat, boolean hidden) {
        this(new AdvancementDisplay(icon, title, description, background, frame, showToast, announceToChat, hidden));
    }

    public DisplayInjector() {
        display = null;
    }

    public DisplayInjector title(Text title) {
        this.title = title;
        return this;
    }

    public DisplayInjector description(Text description) {
        this.description = description;
        return this;
    }

    public DisplayInjector icon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public DisplayInjector background(Identifier background) {
        this.background = background;
        return this;
    }

    public DisplayInjector frame(AdvancementFrame frame) {
        this.frame = frame;
        return this;
    }

    public DisplayInjector showToast(boolean showToast) {
        this.showToast = showToast;
        return this;
    }

    public DisplayInjector announceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
        return this;
    }

    public DisplayInjector hidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    @Override
    public AdvancementInjectionRegistry.Type getType() {
        return AdvancementInjectionRegistry.Type.DISPLAY;
    }

    public @Nullable AdvancementDisplay getDisplay() {
        return display;
    }

    public @Nullable Text getTitle() {
        return title;
    }

    public @Nullable Text getDescription() {
        return description;
    }

    public @Nullable ItemStack getIcon() {
        return icon;
    }

    public @Nullable Identifier getBackground() {
        return background;
    }

    public @Nullable AdvancementFrame getFrame() {
        return frame;
    }

    public boolean isShowToast() {
        return showToast;
    }

    public boolean isAnnounceToChat() {
        return announceToChat;
    }

    public boolean isHidden() {
        return hidden;
    }
}
