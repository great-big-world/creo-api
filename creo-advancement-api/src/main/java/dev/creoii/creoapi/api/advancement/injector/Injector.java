package dev.creoii.creoapi.api.advancement.injector;

public interface Injector {
    Type getType();

    enum Type {
        CRITERIA,
        PARENT,
        REQUIREMENTS,
        REWARDS,
        DISPLAY
    }
}
