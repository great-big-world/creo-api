package dev.creoii.creoapi.api.advancement.injector;

public record RequirementsInjector(String... requirements) implements Injector {
    @Override
    public Type getType() {
        return Type.REQUIREMENTS;
    }
}
