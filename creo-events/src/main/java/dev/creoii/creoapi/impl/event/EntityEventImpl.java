package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
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
    public static void applyEntitySpawnCallback(ServerWorld serverWorld, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean result = EntitySpawnCallback.EVENT.invoker().spawn(serverWorld, entity);

        if (!result)
            cir.cancel();
    }

    public static void applyMobInitializeCallback(ServerWorldAccess world, MobEntity mob, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        EntityData result = MobInitializeCallback.EVENT.invoker().initialize(world, mob, difficulty, spawnReason, entityData, entityNbt);

        if (result != null) {
            cir.setReturnValue(result);
        }
    }

    public static void applyWithinStructureCallback(World world, Entity entity, BlockPos pos, ChunkPos chunkPos) {
        if (entity.age % 10 != 0 || world.isClient) return;
        ServerWorld serverWorld = (ServerWorld) world;
        for (StructureStart structureStart : serverWorld.getStructureAccessor().getStructureStarts(chunkPos, structure -> true)) {
            if (structureStart.hasChildren() && structureStart.getBoundingBox().contains(pos))
                WithinStructureCallback.EVENT.invoker().enter(serverWorld, entity, structureStart);
        }
    }

    public static void applyAnimalBreedCallbackPre(ServerWorld world, AnimalEntity animal, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        boolean result = AnimalBreedCallback.Pre.EVENT.invoker().breed(world, animal, other, baby);

        if (!result)
            ci.cancel();
    }

    public static void applyAnimalBreedCallbackPost(ServerWorld world, AnimalEntity animal, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        AnimalBreedCallback.Post.EVENT.invoker().breed(world, animal, other, baby);
    }

    public static void applyLivingEquipStackCallback(LivingEntity livingEntity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        boolean result = LivingEquipStackCallback.EVENT.invoker().equipStack(livingEntity, slot, oldStack, newStack);

        if (!result)
            ci.cancel();
    }

    public static void applyLivingDropLootCallback(LivingEntity livingEntity, Identifier identifier, LootTable lootTable, DamageSource damageSource, LootContextParameterSet lootContextParameterSet, boolean causedByPlayer, CallbackInfo ci) {
        boolean result = LivingDropLootCallback.EVENT.invoker().dropLoot(livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer);

        if (!result)
            ci.cancel();
    }
}
