package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.event.block.BlockEvents;
import dev.creoii.creoapi.api.event.entity.*;
import dev.creoii.creoapi.api.event.item.ItemEvents;
import dev.creoii.creoapi.api.event.world.WorldEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

public class EventsTest implements ModInitializer {
    private static final boolean testMobInitializeEvent = false;
    private static final boolean testEntitySpawnEvent = false;
    private static final boolean testWithinStructureEvent = false;
    private static final boolean testEntityWriteNbtEvent = true;
    private static final boolean testEntityDataTrackEvent = true;
    private static final boolean testAnimalBreedEventPre = false;
    private static final boolean testAnimalBreedEventPost = false;
    private static final boolean testLivingEquipStackEvent = false;
    private static final boolean testLivingEatFoodEvent = false;
    private static final boolean testLivingDropLootEvent = false;
    private static final boolean testPlayerLevelUpEvent = false;
    private static final boolean testProjectileFireEvent = false;
    private static final boolean testProjectileImpactEvent = false;
    private static final boolean testItemCraftEvent = false;
    private static final boolean testItemEnchantEvent = false;
    private static final boolean testBlockPlaceEvent = false;
    private static final boolean testBlockBreakEvent = false;
    private static final boolean testBlockChangeEvent = false;
    private static final boolean testWorldExplodeEvent = false;

    @Override
    public void onInitialize() {
        if (testMobInitializeEvent) {
            MobEntityEvents.INITIALIZE.register((world, mob, difficulty, spawnReason, entityData, entityNbt) -> {
                System.out.println("Mob Initialize:");
                System.out.println("    type=" + mob.getType().getTranslationKey());
                System.out.println("    reason=" + spawnReason);
                System.out.println("    data=" + (entityData == null));

                if (mob instanceof AbstractSkeletonEntity && world.getRandom().nextBoolean()) {
                    mob.equipStack(EquipmentSlot.MAINHAND, Items.STONE_SWORD.getDefaultStack());
                }

                return null;
            });
        }

        if (testEntitySpawnEvent) {
            EntityEvents.SPAWN.register((serverWorld, entity) -> {
                System.out.println("Entity Spawn:");
                System.out.println("    type=" + entity.getType().getTranslationKey());
                return true;
            });
        }

        if (testWithinStructureEvent) {
            EntityEvents.WITHIN_STRUCTURE.register((serverWorld, entity, structureStart) -> {
                System.out.println("Within Structure:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    structure=" + structureStart.getStructure().getType().toString());
            });
        }

        TrackedData<Byte> COLOR = DataTracker.registerData(CowEntity.class, TrackedDataHandlerRegistry.BYTE);
        if (testEntityWriteNbtEvent) {
            EntityEvents.WRITE_NBT.register((entity, nbt) -> {
                if (entity.getType() == EntityType.COW) {
                    nbt.putByte("Color", (byte) DyeColor.byId(entity.getDataTracker().get(COLOR) & 0xf).getId());
                }

                System.out.println("Write Nbt:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    nbt=" + nbt.toString());
            });
        }

        if (testEntityDataTrackEvent) {
            EntityEvents.DATA_TRACK.register((entity, dataTracker) -> {
                if (entity.getType() == EntityType.COW) {
                    dataTracker.startTracking(COLOR, (byte) 0);
                }

                System.out.println("Data Track:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
            });
        }

        if (testAnimalBreedEventPre) {
            AnimalEntityEvents.BREED_PRE.register((world, animal, other, baby) -> {
                System.out.println("Animal Breed (Pre):");
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    other=" + other.getType().getTranslationKey());
                System.out.println("    baby=" + baby.getType().getTranslationKey());
                return true;
            });
        }

        if (testAnimalBreedEventPost) {
            AnimalEntityEvents.BREED_POST.register((world, animal, other, baby) -> {
                System.out.println("Animal Breed (Post):");
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    other=" + other.getType().getTranslationKey());
                System.out.println("    baby=" + baby.getType().getTranslationKey());

                baby.setBreedingAge(-40);
            });
        }

        if (testLivingEquipStackEvent) {
            LivingEntityEvents.EQUIP_STACK.register((livingEntity, slot, oldStack, newStack) -> {
                System.out.println("Living Equip Stack:");
                System.out.println("    living=" + livingEntity.getType().getTranslationKey());
                System.out.println("    slot=" + slot.getType());
                System.out.println("    oldStack=" + oldStack.getTranslationKey());
                System.out.println("    newStack=" + newStack.getTranslationKey());

                return true;
            });
        }

        if (testLivingEatFoodEvent) {
            LivingEntityEvents.EAT_FOOD.register((world, livingEntity, stack) -> {
                System.out.println("Living Eat Food:");
                System.out.println("    living=" + livingEntity.getType().getTranslationKey());
                System.out.println("    food=" + stack.getTranslationKey());

                return stack;
            });
        }

        if (testLivingDropLootEvent) {
            LivingEntityEvents.DROP_LOOT.register((livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer) -> {
                System.out.println("Living Drop Loot:");
                System.out.println("    living=" + livingEntity.getType().getTranslationKey());
                System.out.println("    id=" + identifier.toString());
                System.out.println("    damage=" + damageSource.getName());

                return true;
            });
        }

        if (testPlayerLevelUpEvent) {
            PlayerEntityEvents.LEVEL_UP.register((player, levels) -> {
                System.out.println("Player Level Up:");
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    levels=" + levels);

                return player.experienceLevel < 30;
            });
        }

        if (testProjectileFireEvent) {
            ProjectileEntityEvents.FIRE.register(projectile -> {
                System.out.println("Projectile Fire:");
                System.out.println("    projectile=" + projectile.getType().getTranslationKey());
            });
        }

        if (testProjectileImpactEvent) {
            ProjectileEntityEvents.IMPACT.register((projectile, hitResult) -> {
                System.out.println("Projectile Impact:");
                System.out.println("    projectile=" + projectile.getType().getTranslationKey());
                System.out.println("    type=" + hitResult.getType().name());
                System.out.println("    pos=" + hitResult.getPos());

                return false;
            });
        }

        if (testItemCraftEvent) {
            ItemEvents.CRAFT.register((world, stack, player, amount) -> {
                System.out.println("Item Craft:");
                System.out.println("    stack=" + stack.getItem().getTranslationKey());
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    amount=" + amount);
            });
        }

        if (testItemEnchantEvent) {
            ItemEvents.ENCHANT.register((stack, enchantment, level) -> {
                System.out.println("Item Enchant:");
                System.out.println("    stack=" + stack.getItem().getTranslationKey());
                System.out.println("    enchantment=" + enchantment.getTranslationKey());
                System.out.println("    level=" + level);

                return enchantment != Enchantments.MENDING;
            });
        }

        if (testBlockBreakEvent) {
            BlockEvents.BREAK.register((world, player, state, pos) -> {
                System.out.println("Block Break:");
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    block=" + state.getBlock().getTranslationKey());
                System.out.println("    pos=" + pos.toShortString());

                return !state.isOf(Blocks.STONE);
            });
        }

        if (testBlockPlaceEvent) {
            BlockEvents.PLACE.register((block, context) -> {
                System.out.println("Block Place:");
                System.out.println("    block=" + block.getTranslationKey());

                return block != Blocks.STONE;
            });
        }

        if (testBlockChangeEvent) {
            BlockEvents.CHANGE.register((world, pos, newState, oldState, moved) -> {
                if (world.isClient)
                    return true;

                System.out.println("Block Change:");
                System.out.println("    pos=" + pos.toShortString());
                System.out.println("    new state=" + newState.getBlock().getTranslationKey());
                System.out.println("    old state=" + oldState.getBlock().getTranslationKey());
                System.out.println("    moved=" + moved);

                return !(newState.isOf(Blocks.FARMLAND) && oldState.isOf(Blocks.GRASS_BLOCK));
            });
        }

        if (testWorldExplodeEvent) {
            WorldEvents.EXPLODE.register((world, explosion, x, y, z, behavior, destructionType, explosionSourceType, power, createFire, particles) -> {
                System.out.println("World Explode:");
                System.out.println("    destruction type=" + destructionType.name());
                System.out.println("    source type=" + explosionSourceType.name());
                System.out.println("    power=" + power);
                System.out.println("    fire=" + createFire);
                System.out.println("    particles=" + particles);

                return power > 5;
            });
        }
    }
}
