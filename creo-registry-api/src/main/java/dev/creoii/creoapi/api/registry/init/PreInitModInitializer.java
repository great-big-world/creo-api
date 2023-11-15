package dev.creoii.creoapi.api.registry.init;

/**
 * A mod initializer run on both {@link net.fabricmc.api.EnvType#CLIENT} and {@link net.fabricmc.api.EnvType#SERVER}.
 *
 * <p>This entrypoint is suitable for setting up logic, systems, or modifications that need to happen <i>before</i> anything in the game is registered.</p>
 *
 * <p>In {@code fabric.mod.json}, this entrypoint is defined with {@code pre-init} key.</p>
 *
 * @see net.fabricmc.api.ModInitializer
 */
@FunctionalInterface
public interface PreInitModInitializer {
    void onPreInitialize(boolean client);
}
