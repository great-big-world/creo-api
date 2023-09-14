package dev.creoii.creoapi.api.advancement;

import dev.creoii.creoapi.api.advancement.injector.Injector;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class AdvancementInjectionRegistry {
    private static final Map<Identifier, Injector[]> ADVANCEMENT_INJECTORS = new HashMap<>();

    public static void register(Identifier advancementId, Injector injector) {
        if (ADVANCEMENT_INJECTORS.containsKey(advancementId)) {
            Injector[] prevInjectors = ADVANCEMENT_INJECTORS.get(advancementId);
            Injector[] newInjectors = Arrays.copyOf(prevInjectors, prevInjectors.length + 1);
            newInjectors[newInjectors.length - 1] = injector;
            ADVANCEMENT_INJECTORS.replace(advancementId, newInjectors);
        } else
            ADVANCEMENT_INJECTORS.put(advancementId, new Injector[]{injector});
    }

    public static void register(Identifier advancementId, Injector... injectors) {
        for (Injector injector : injectors) {
            register(advancementId, injector);
        }
    }

    public static Map<Identifier, Injector[]> getAdvancementInjectors() {
        return ADVANCEMENT_INJECTORS;
    }
}
