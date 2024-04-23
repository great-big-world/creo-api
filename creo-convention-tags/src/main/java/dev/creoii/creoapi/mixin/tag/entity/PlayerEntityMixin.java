package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "clipAtLedge", at = @At("HEAD"), cancellable = true)
    private void creo$applyDoesntClipAtLedge(CallbackInfoReturnable<Boolean> cir) {
        BlockTagImpl.applyDoesNotClipAtLedge((PlayerEntity) (Object) this, cir);
    }
}
