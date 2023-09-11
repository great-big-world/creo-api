package dev.creoii.creoapi.mixin.advancement;

import dev.creoii.creoapi.impl.advancement.AdvancementImpl;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;

@Mixin(AdvancementManager.class)
public class AdvancementManagerMixin {
    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/Advancement$Builder;build(Lnet/minecraft/util/Identifier;)Lnet/minecraft/advancement/Advancement;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyAdvancementInjections(Map<Identifier, Advancement.Builder> advancements, CallbackInfo ci, Map<Identifier, Advancement.Builder> map, boolean bl, Iterator<Map.Entry<Identifier, Advancement.Builder>> iterator, Map.Entry<Identifier, Advancement.Builder> entry, Identifier identifier, Advancement.Builder builder) {
        AdvancementImpl.applyAdvancementInjections(identifier, builder);
    }
}
