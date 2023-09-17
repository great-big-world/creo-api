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

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
    protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;Lnet/minecraft/entity/passive/PassiveEntity;)V", at = @At("HEAD"), cancellable = true)
    private void creo_animalPreBreedEvent(ServerWorld world, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        EntityEventImpl.applyAnimalPreBreedEvent(world, (AnimalEntity) (Object) this, other, baby, ci);
    }

    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;Lnet/minecraft/entity/passive/PassiveEntity;)V", at = @At("TAIL"), cancellable = true)
    private void creo_animalPostBreedEvent(ServerWorld world, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        EntityEventImpl.applyAnimalPostBreedEvent(world, (AnimalEntity) (Object) this, other, baby, ci);
    }
}
