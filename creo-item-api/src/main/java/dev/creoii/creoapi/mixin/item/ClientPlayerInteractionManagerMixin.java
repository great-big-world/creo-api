package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.CreoItemImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "attackBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameMode;isCreative()Z"))
    private void creo$tryAttackThroughBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        CreoItemImpl.applyAttackThroughBlockClient(client);
    }

    @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
    private void creo$doNotAttackItemEntities(PlayerEntity player, Entity target, CallbackInfo ci) {
        if (target instanceof ItemEntity)
            ci.cancel();
    }
}
