package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

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

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AnimalEntity;getBreedingAge()I", shift = At.Shift.BY, by = 2), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyAnimalEatEvent(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, ItemStack itemStack, int i) {
        EntityEventImpl.applyAnimalEatEvent(player, hand, itemStack, (AnimalEntity) (Object) this, i, true, cir);
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AnimalEntity;lovePlayer(Lnet/minecraft/entity/player/PlayerEntity;)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyAnimalLoveEvent(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, ItemStack itemStack, int i) {
        EntityEventImpl.applyAnimalLoveEvent(player, (AnimalEntity) (Object) this, i, true, cir);
    }
}
