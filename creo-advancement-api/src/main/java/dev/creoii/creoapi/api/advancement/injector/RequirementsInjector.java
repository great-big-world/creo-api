package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;

public class RequirementsInjector extends AdvancementInjectionRegistry.Injector {
    private final String[] requirements;

    public RequirementsInjector(String... requirements) {
        this.requirements = requirements;
    }

    @Override
    public AdvancementInjectionRegistry.Type getType() {
        return AdvancementInjectionRegistry.Type.REQUIREMENTS;
    }

    public String[] getRequirements() {
        return requirements;
    }
}
