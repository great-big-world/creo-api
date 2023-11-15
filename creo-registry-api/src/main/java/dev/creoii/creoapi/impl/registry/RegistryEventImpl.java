package dev.creoii.creoapi.impl.registry;

import com.mojang.serialization.Lifecycle;
import dev.creoii.creoapi.api.registry.event.RegistryEvents;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class RegistryEventImpl {
    @SuppressWarnings("unchecked")
    public static <V, T extends V> void applyRegisterEvent(Registry<V> registry, RegistryKey<V> key, T entry, CallbackInfoReturnable<T> cir) {
        T obj = (T) RegistryEvents.REGISTER.invoker().onRegister(registry, key, entry);
        if (obj != null && registry instanceof MutableRegistry<V> mutableRegistry) {
            mutableRegistry.add(key, obj, Lifecycle.stable());
            cir.setReturnValue(obj);
        }
    }
}
