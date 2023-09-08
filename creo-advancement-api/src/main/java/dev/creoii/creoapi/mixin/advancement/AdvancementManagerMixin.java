package dev.creoii.creoapi.mixin.advancement;

import dev.creoii.creoapi.api.advancement.*;
import dev.creoii.creoapi.api.advancement.injector.*;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Mixin(AdvancementManager.class)
public class AdvancementManagerMixin {
    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/Advancement$Builder;build(Lnet/minecraft/util/Identifier;)Lnet/minecraft/advancement/Advancement;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyAdvancementInjections(Map<Identifier, Advancement.Builder> advancements, CallbackInfo ci, Map<Identifier, Advancement.Builder> map, boolean bl, Iterator<Map.Entry<Identifier, Advancement.Builder>> iterator, Map.Entry<Identifier, Advancement.Builder> entry, Identifier identifier, Advancement.Builder builder) {
        AdvancementInjectorRegistry.getAdvancementInjectors().forEach((id, injectors) -> {
            if (identifier.equals(id)) {
                for (AdvancementInjectorRegistry.Injector injector : injectors) {
                    switch (injector.getType()) {
                        case CRITERIA -> {
                            CriteriaInjector criteriaInjector = (CriteriaInjector) injector;
                            builder.criterion(criteriaInjector.getName(), criteriaInjector.getCriterion());
                        }
                        case REQUIREMENTS -> {
                            AdvancementBuilderAccessor accessor = ((AdvancementBuilderAccessor) builder);
                            String[][] oldReqs = accessor.getRequirements();
                            String[][] newReqs = Arrays.copyOf(oldReqs, oldReqs.length + 1);
                            newReqs[newReqs.length - 1] = ((RequirementsInjector) injector).getRequirements();
                            accessor.setRequirements(newReqs);
                        }
                        case REWARDS -> builder.rewards(((RewardsInjector) injector).getRewards());
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
