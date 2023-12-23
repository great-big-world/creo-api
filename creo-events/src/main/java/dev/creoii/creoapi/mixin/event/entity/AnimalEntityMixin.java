package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
    protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "breed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AnimalEntity;createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/PassiveEntity;)Lnet/minecraft/entity/passive/PassiveEntity;", shift = At.Shift.BY, by = 2), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void creo_animalPreBreedEvent(ServerWorld world, AnimalEntity other, CallbackInfo ci, PassiveEntity passiveEntity) {
        EntityEventImpl.applyAnimalPreBreedEvent(world, (AnimalEntity) (Object) this, other, passiveEntity, ci);
    }

    @Inject(method = "breed", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void creo_animalPostBreedEvent(ServerWorld world, AnimalEntity other, CallbackInfo ci, PassiveEntity passiveEntity) {
        EntityEventImpl.applyAnimalPostBreedEvent(world, (AnimalEntity) (Object) this, other, passiveEntity, ci);
    }
}
