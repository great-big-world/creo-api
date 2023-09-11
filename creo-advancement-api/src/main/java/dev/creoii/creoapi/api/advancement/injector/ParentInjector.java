package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;
import net.minecraft.advancement.Advancement;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParentInjector extends AdvancementInjectionRegistry.Injector {
    @Nullable
    private final Identifier parentId;
    @Nullable
    private final Advancement parent;

    public ParentInjector(@NotNull Identifier parentId) {
        this.parentId = parentId;
        parent = null;
    }

    public ParentInjector(@NotNull Advancement parent) {
        this.parent = parent;
        parentId = null;
    }

    @Override
    public AdvancementInjectionRegistry.Type getType() {
        return AdvancementInjectionRegistry.Type.PARENT;
    }

    @Nullable
    public Identifier getParentId() {
        return parentId;
    }

    @Nullable
    public Advancement getParent() {
        return parent;
    }
}
