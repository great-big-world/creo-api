package dev.creoii.creoapi.mixin.attribute.client;

import dev.creoii.creoapi.impl.attribute.BlockCooldownAttributeImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(method = "attackBlock", constant = @Constant(intValue = 5))
    private int creo_applyAttackBlockSpeed(int constant) {
        return BlockCooldownAttributeImpl.applyBlockBreakSpeed(client);
    }

    @ModifyConstant(method = "updateBlockBreakingProgress", constant = @Constant(intValue = 5))
    private int creo_applyBlockBreakSpeed(int constant) {
        return BlockCooldownAttributeImpl.applyBlockBreakSpeed(client);
    }
}
