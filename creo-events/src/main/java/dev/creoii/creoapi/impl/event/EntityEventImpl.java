package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class EntityEventImpl {
    public static void applyEntitySpawnEvent(ServerWorld serverWorld, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean result = EntityEvents.SPAWN.invoker().shouldSpawn(serverWorld, entity);

        if (!result)
            cir.cancel();
    }

    public static void applyMobInitializeEvent(ServerWorldAccess world, MobEntity mob, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        EntityData result = MobEntityEvents.INITIALIZE.invoker().onInitialize(world, mob, difficulty, spawnReason, entityData, entityNbt);

        if (result != null)
            cir.setReturnValue(result);
    }

    public static void applyWithinStructureEvent(World world, Entity entity, BlockPos pos, ChunkPos chunkPos) {
        if (entity.age % 10 != 0 || world.isClient)
            return;
        ServerWorld serverWorld = (ServerWorld) world;
        for (StructureStart structureStart : serverWorld.getStructureAccessor().getStructureStarts(chunkPos, structure -> true)) {
            if (structureStart.hasChildren() && structureStart.getBoundingBox().contains(pos))
                EntityEvents.WITHIN_STRUCTURE.invoker().onWithinStructure(serverWorld, entity, structureStart);
        }
    }

    public static void applyAnimalPreBreedEvent(ServerWorld world, AnimalEntity animal, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        boolean result = AnimalEntityEvents.BREED_PRE.invoker().shouldBreed(world, animal, other, baby);

        if (!result)
            ci.cancel();
    }

    public static void applyAnimalPostBreedEvent(ServerWorld world, AnimalEntity animal, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        AnimalEntityEvents.BREED_POST.invoker().onBreed(world, animal, other, baby);
    }

    public static void applyLivingEquipStackEvent(LivingEntity livingEntity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        boolean result = LivingEntityEvents.EQUIP_STACK.invoker().shouldEquipStack(livingEntity, slot, oldStack, newStack);

        if (!result)
            ci.cancel();
    }

    public static void applyLivingDropLootEvent(LivingEntity livingEntity, Identifier identifier, LootTable lootTable, DamageSource damageSource, LootContextParameterSet lootContextParameterSet, boolean causedByPlayer, CallbackInfo ci) {
        boolean result = LivingEntityEvents.DROP_LOOT.invoker().shouldDropLoot(livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer);

        if (!result)
            ci.cancel();
    }

    public static void applyLivingEatFoodEvent(World world, LivingEntity livingEntity, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(LivingEntityEvents.EAT_FOOD.invoker().onEatFood(world, livingEntity, stack));
    }

    public static void applyPlayerLevelUpEvent(PlayerEntity playerEntity, int levels, CallbackInfo ci) {
        if (levels <= 0)
            return;

        boolean result = PlayerEntityEvents.LEVEL_UP.invoker().shouldLevelUp(playerEntity, levels);

        if (!result)
            ci.cancel();
    }
}
