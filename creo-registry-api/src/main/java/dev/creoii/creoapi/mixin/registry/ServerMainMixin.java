package dev.creoii.creoapi.mixin.registry;

import dev.creoii.creoapi.impl.registry.PreInitImpl;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class ServerMainMixin {
    @Inject(method = "main", at = @At(value = "INVOKE", target = "Lnet/minecraft/Bootstrap;initialize()V"))
    private static void test_invokePreInitEntrypoint(String[] args, CallbackInfo ci) {
        PreInitImpl.applyPreInit(false);
    }
}
