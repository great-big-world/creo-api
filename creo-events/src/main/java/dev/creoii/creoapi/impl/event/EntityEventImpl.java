package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class EntityEventImpl {
    public static void applyEntitySpawnEvent(ServerWorld serverWorld, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean result = EntityEvents.SPAWN.invoker().onSpawn(serverWorld, entity);

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

    public static void applyWriteNbtEvent(Entity entity, NbtCompound nbt) {
        EntityEvents.WRITE_NBT.invoker().onWriteNbt(entity, nbt);
    }

    public static void applyDataTrackEvent(Entity entity, DataTracker dataTracker) {
        EntityEvents.DATA_TRACK.invoker().onDataTrack(entity, dataTracker);
    }

    public static void applyAnimalPreBreedEvent(ServerWorld world, AnimalEntity animal, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        boolean result = AnimalEntityEvents.PRE_BREED.invoker().onBreed(world, animal, other, baby);

        if (!result)
            ci.cancel();
    }

    public static void applyAnimalPostBreedEvent(ServerWorld world, AnimalEntity animal, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        AnimalEntityEvents.POST_BREED.invoker().onBreed(world, animal, other, baby);
    }

    public static void applyLivingEquipStackEvent(LivingEntity livingEntity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        boolean result = LivingEntityEvents.EQUIP_STACK.invoker().onEquipStack(livingEntity, slot, oldStack, newStack);

        if (!result)
            ci.cancel();
    }

    public static void applyLivingDropLootEvent(LivingEntity livingEntity, Identifier identifier, LootTable lootTable, DamageSource damageSource, LootContextParameterSet lootContextParameterSet, boolean causedByPlayer, CallbackInfo ci) {
        boolean result = LivingEntityEvents.DROP_LOOT.invoker().onDropLoot(livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer);

        if (!result)
            ci.cancel();
    }

    public static void applyLivingEatFoodEvent(World world, LivingEntity livingEntity, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(LivingEntityEvents.EAT_FOOD.invoker().onEatFood(world, livingEntity, stack));
    }

    public static void applyPlayerLevelUpEvent(PlayerEntity playerEntity, int levels, CallbackInfo ci) {
        if (levels <= 0)
            return;

        boolean result = PlayerEntityEvents.LEVEL_UP.invoker().onLevelUp(playerEntity, levels);

        if (!result)
            ci.cancel();
    }

    public static void applyProjectileFireEvent(ProjectileEntity projectile) {
        ProjectileEntityEvents.FIRE.invoker().onFire(projectile);
    }

    public static void applyProjectileImpactEvent(ProjectileEntity projectile, HitResult hitResult, CallbackInfo ci) {
        boolean result = ProjectileEntityEvents.IMPACT.invoker().onImpact(projectile, hitResult);

        if (!result)
            ci.cancel();
    }

    public static void applyMobPostInitGoalsEvent(World world, MobEntity mob, GoalSelector goalSelector, GoalSelector targetSelector) {
        MobEntityEvents.INIT_GOALS.invoker().onInitGoals(world, mob, goalSelector, targetSelector);
    }

    public static void applyEntityStruckByLightningEvent(ServerWorld serverWorld, Entity entity, LightningEntity lightning) {
        EntityEvents.STRUCK_BY_LIGHTNING.invoker().onStruckByLightning(serverWorld, entity, lightning);
    }

    public static void applyEntityChangeDimensionEvent(World world, World destination, Entity entity, Entity copy, TeleportTarget teleportTarget, CallbackInfoReturnable<Entity> cir) {
        boolean result = EntityEvents.CHANGE_DIMENSION.invoker().onChangeDimension(world, destination, entity, copy, teleportTarget);

        if (!result)
            cir.setReturnValue(null);
    }

    public static void applyPlayerRespawnEvent(ServerPlayerEntity player, boolean alive) {
        PlayerEntityEvents.RESPAWN.invoker().onRespawn(player, alive);
    }

    public static void applyAnimalEatEvent(PlayerEntity player, Hand hand, ItemStack food, AnimalEntity animal, int age, boolean overGrow, CallbackInfoReturnable<ActionResult> cir) {
        boolean result = AnimalEntityEvents.EAT.invoker().onEat(player, hand, food, animal, age, overGrow);

        if (!result)
            cir.setReturnValue(ActionResult.PASS);
    }

    public static void applyAnimalGrowUpEvent(PassiveEntity passive, int age, CallbackInfo ci) {
        boolean result = AnimalEntityEvents.GROW_UP.invoker().onGrowUp(passive, age);

        if (!result) {
            passive.getDataTracker().set(PassiveEntity.CHILD, true);
            ci.cancel();
        }
    }

    public static void applyAnimalLoveEvent(PlayerEntity player, PassiveEntity animal, int age, boolean overGrow, CallbackInfoReturnable<ActionResult> cir) {
        boolean result = AnimalEntityEvents.LOVE.invoker().onLove(player, animal, age, overGrow);

        if (!result)
            cir.setReturnValue(ActionResult.PASS);
    }
}
