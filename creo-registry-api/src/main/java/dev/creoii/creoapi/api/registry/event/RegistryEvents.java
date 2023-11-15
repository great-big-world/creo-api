package dev.creoii.creoapi.api.registry.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

/**
 * Events related to {@link Registry}.
 */
public final class RegistryEvents {
    /**
     * An event called when an object is registered to a {@link Registry}.
     *
     * <p>This event must be registered in the {@link dev.creoii.creoapi.api.registry.init.PreInitModInitializer} entrypoint, otherwise it will not take effect.</p>
     */
    public static final Event<Register> REGISTER = EventFactory.createArrayBacked(Register.class, listeners -> (registry, key, entry) -> {
        for (Register listener : listeners) {
            return listener.onRegister(registry, key, entry);
        }
        return entry;
    });

    @FunctionalInterface
    public interface Register {
        /**
         * Called when an object is registered to a {@link Registry}.
         *
         * @param registry the registry
         * @param key the registry key of the object
         * @param entry the object
         * @return the modified object.
         */
        Object onRegister(Registry<?> registry, RegistryKey<?> key, Object entry);
    }
}
