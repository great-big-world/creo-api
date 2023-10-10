package dev.creoii.creoapi.mixin.worldgen;

import com.google.common.collect.ImmutableList;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseParameters;
import net.minecraft.registry.RegistryLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {
    @Shadow @Final @Mutable public static List<RegistryLoader.Entry<?>> DYNAMIC_REGISTRIES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void creo_addFastNoiseSettingsRegistry(CallbackInfo ci) {
        DYNAMIC_REGISTRIES = new ImmutableList.Builder<RegistryLoader.Entry<?>>()
                .addAll(DYNAMIC_REGISTRIES)
                .add(new RegistryLoader.Entry<>(FastNoiseParameters.REGISTRY_KEY, FastNoiseParameters.CODEC))
                .build();
    }
}