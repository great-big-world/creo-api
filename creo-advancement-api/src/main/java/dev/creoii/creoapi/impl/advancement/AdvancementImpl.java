package dev.creoii.creoapi.impl.advancement;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;
import dev.creoii.creoapi.api.advancement.injector.*;
import dev.creoii.creoapi.mixin.advancement.AdvancementBuilderAccessor;
import dev.creoii.creoapi.mixin.advancement.AdvancementDisplayAccessor;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class AdvancementImpl {
    public static void applyAdvancementInjections(Identifier identifier, Advancement.Builder builder) {
        AdvancementInjectionRegistry.getAdvancementInjectors().forEach((id, injectors) -> {
            if (identifier.equals(id)) {
                for (Injector injector : injectors) {
                    switch (injector.getType()) {
                        case CRITERIA -> {
                            CriteriaInjector criteriaInjector = (CriteriaInjector) injector;
                            builder.criterion(criteriaInjector.name(), criteriaInjector.criterion());
                        }
                        case REQUIREMENTS -> {
                            AdvancementBuilderAccessor accessor = ((AdvancementBuilderAccessor) builder);
                            String[][] oldReqs = accessor.getRequirements();
                            String[][] newReqs = Arrays.copyOf(oldReqs, oldReqs.length + 1);
                            newReqs[newReqs.length - 1] = ((RequirementsInjector) injector).requirements();
                            accessor.setRequirements(newReqs);
                        }
                        case REWARDS -> builder.rewards(((RewardsInjector) injector).rewards());
                        case DISPLAY -> {
                            AdvancementDisplayAccessor accessor = (AdvancementDisplayAccessor) ((AdvancementBuilderAccessor) builder).getDisplay();
                            DisplayInjector displayInjector = ((DisplayInjector) injector);
                            AdvancementDisplay newDisplay = displayInjector.getDisplay();
                            if (newDisplay != null) {
                                builder.display(newDisplay);
                                break;
                            }
                            if (displayInjector.getTitle() != null)
                                accessor.setTitle(displayInjector.getTitle());
                            if (displayInjector.getDescription() != null)
                                accessor.setDescription(displayInjector.getDescription());
                            if (displayInjector.getIcon() != null)
                                accessor.setIcon(displayInjector.getIcon());
                            if (displayInjector.getBackground() != null)
                                accessor.setBackground(displayInjector.getBackground());
                            if (displayInjector.getFrame() != null)
                                accessor.setFrame(displayInjector.getFrame());
                            if (displayInjector.isShowToast())
                                accessor.setShowToast(displayInjector.isShowToast());
                            if (displayInjector.isAnnounceToChat())
                                accessor.setAnnounceToChat(displayInjector.isAnnounceToChat());
                            if (displayInjector.isHidden())
                                accessor.setHidden(displayInjector.isHidden());
                        }
                        case PARENT -> {
                            ParentInjector parentInjector = (ParentInjector) injector;
                            if (parentInjector.getParentId() == null)
                                builder.parent(parentInjector.getParent());
                            else builder.parent(parentInjector.getParentId());
                        }
                    }
                }
            }
        });
    }
}
