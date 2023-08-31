package dev.creoii.creoapi.api.advancement;

public class RequirementsInjector extends AdvancementInjector.Injector {
    private final String[] requirements;

    public RequirementsInjector(String... requirements) {
        this.requirements = requirements;
    }

    @Override
    public AdvancementInjector.Type getType() {
        return AdvancementInjector.Type.REQUIREMENTS;
    }

    public String[] getRequirements() {
        return requirements;
    }
}
