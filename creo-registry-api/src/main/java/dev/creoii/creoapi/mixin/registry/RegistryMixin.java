package dev.creoii.creoapi.mixin.registry;

import com.mojang.serialization.Lifecycle;
import dev.creoii.creoapi.api.registry.event.RegistryEvents;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.IdentityHashMap;
import java.util.Map;

@Mixin(Registry.class)
public interface RegistryMixin {
    @SuppressWarnings("unchecked")
    @Inject(method = "register(Lnet/minecraft/registry/Registry;Lnet/minecraft/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;", at = @At("HEAD"), cancellable = true)
    private static <V, T extends V> void test_applyRegistryRegisterEvent(Registry<V> registry, RegistryKey<V> key, T entry, CallbackInfoReturnable<T> cir) {
        T obj = (T) RegistryEvents.REGISTER.invoker().onRegister(registry, key, entry);

        if (obj == null) {
            if (registry instanceof SimpleRegistry<V> simpleRegistry && simpleRegistry.intrusiveValueToEntry != null) {
                System.out.println(registry.getId(entry));
                System.out.println("before: " + simpleRegistry.intrusiveValueToEntry.containsKey(entry));
                simpleRegistry.intrusiveValueToEntry.remove(entry);
                //simpleRegistry.intrusiveValueToEntry = getMapWithout(simpleRegistry.intrusiveValueToEntry, entry);
                System.out.println("after: " + simpleRegistry.intrusiveValueToEntry.containsKey(entry));
            }
            cir.setReturnValue(entry);
        } else {
            if (registry instanceof MutableRegistry<V> mutableRegistry) {
                mutableRegistry.add(key, obj, Lifecycle.stable());
                cir.setReturnValue(obj);
            }
        }
    }

    @Unique
    private static <T> Map<T, RegistryEntry.Reference<T>> getMapWithout(Map<T, RegistryEntry.Reference<T>> map, T entry) {
        Map<T, RegistryEntry.Reference<T>> copy = new IdentityHashMap<>();

        for (Map.Entry<T, RegistryEntry.Reference<T>> entry1 : map.entrySet()) {
            if (entry1.getKey().equals(entry))
                continue;
            copy.put(entry1.getKey(), entry1.getValue());
        }

        return copy;
    }
}
