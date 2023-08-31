package dev.creoii.creoapi.api.advancement;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public final class AdvancementInjector {
    private static final Map<Identifier, Injector[]> ADVANCEMENT_INJECTORS = new HashMap<>();

    public static void register(Identifier advancementId, Injector injector) {
        register(advancementId, new Injector[]{injector});
    }

    public static void register(Identifier advancementId, Injector... injectors) {
        ADVANCEMENT_INJECTORS.put(advancementId, injectors);
    }

    public static Map<Identifier, Injector[]> getAdvancementInjectors() {
        return ADVANCEMENT_INJECTORS;
    }

    public static abstract class Injector {
        public abstract Type getType();
    }

    public enum Type {
        CRITERIA,
        PARENT,
        REQUIREMENTS,
        REWARDS,
        DISPLAY
    }
}
