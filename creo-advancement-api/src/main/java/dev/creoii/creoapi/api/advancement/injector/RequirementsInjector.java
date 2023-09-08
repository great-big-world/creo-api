package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectorRegistry;

public class RequirementsInjector extends AdvancementInjectorRegistry.Injector {
    private final String[] requirements;

    public RequirementsInjector(String... requirements) {
        this.requirements = requirements;
    }

    @Override
    public AdvancementInjectorRegistry.Type getType() {
        return AdvancementInjectorRegistry.Type.REQUIREMENTS;
    }

    public String[] getRequirements() {
        return requirements;
    }
}
