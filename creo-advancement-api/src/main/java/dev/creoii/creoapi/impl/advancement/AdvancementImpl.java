package dev.creoii.creoapi.impl.advancement;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;
import dev.creoii.creoapi.api.advancement.injector.*;
import dev.creoii.creoapi.mixin.advancement.AdvancementBuilderAccessor;
import dev.creoii.creoapi.mixin.advancement.AdvancementDisplayAccessor;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;

@ApiStatus.Internal
public class AdvancementImpl {
    public static void applyAdvancementInjections(Identifier identifier, Advancement.Builder builder) {
        AdvancementInjectionRegistry.getAdvancementInjectors().forEach((id, injectors) -> {
            if (!identifier.equals(id))
                return;

            for (Injector injector : injectors) {
                switch (injector.getType()) {
                    case CRITERIA -> applyCriteriaInjections((CriteriaInjector) injector, builder);
                    case REQUIREMENTS -> applyRequirementsInjections((RequirementsInjector) injector, builder);
                    case REWARDS -> applyRewardsInjections((RewardsInjector) injector, builder);
                    case DISPLAY -> applyDisplayInjections((DisplayInjector) injector, builder);
                    case PARENT -> applyParentInjections((ParentInjector) injector, builder);
                }
            }
        });
    }

    private static void applyCriteriaInjections(CriteriaInjector injector, Advancement.Builder builder) {
        builder.criterion(injector.name(), injector.criterion());
    }

    private static void applyRequirementsInjections(RequirementsInjector injector, Advancement.Builder builder) {
        AdvancementBuilderAccessor accessor = (AdvancementBuilderAccessor) builder;
        String[][] oldReqs = accessor.getRequirements();
        String[][] newReqs = Arrays.copyOf(oldReqs, oldReqs.length + 1);
        newReqs[newReqs.length - 1] = injector.requirements();
        accessor.setRequirements(newReqs);
    }

    private static void applyRewardsInjections(RewardsInjector injector, Advancement.Builder builder) {
        builder.rewards(injector.rewards());
    }

    private static void applyDisplayInjections(DisplayInjector injector, Advancement.Builder builder) {
        AdvancementDisplayAccessor accessor = (AdvancementDisplayAccessor) ((AdvancementBuilderAccessor) builder).getDisplay();
        AdvancementDisplay newDisplay = injector.getDisplay();
        if (newDisplay != null) {
            builder.display(newDisplay);
            return;
        }
        if (injector.getTitle() != null)
            accessor.setTitle(injector.getTitle());
        if (injector.getDescription() != null)
            accessor.setDescription(injector.getDescription());
        if (injector.getIcon() != null)
            accessor.setIcon(injector.getIcon());
        if (injector.getBackground() != null)
            accessor.setBackground(injector.getBackground());
        if (injector.getFrame() != null)
            accessor.setFrame(injector.getFrame());
        if (injector.isShowToast())
            accessor.setShowToast(injector.isShowToast());
        if (injector.isAnnounceToChat())
            accessor.setAnnounceToChat(injector.isAnnounceToChat());
        if (injector.isHidden())
            accessor.setHidden(injector.isHidden());
    }

    private static void applyParentInjections(ParentInjector injector, Advancement.Builder builder) {
        if (injector.getParentId() == null)
            builder.parent(injector.getParent());
        else
            builder.parent(injector.getParentId());
    }
}
