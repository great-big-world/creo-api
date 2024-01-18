package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.event.block.BlockEvents;
import dev.creoii.creoapi.api.event.block.CropEvents;
import dev.creoii.creoapi.api.event.entity.*;
import dev.creoii.creoapi.api.event.item.ItemEvents;
import dev.creoii.creoapi.api.event.misc.FishingEvents;
import dev.creoii.creoapi.api.event.misc.LanguageEvents;
import dev.creoii.creoapi.api.event.misc.SleepEvents;
import dev.creoii.creoapi.api.event.world.WorldEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

public class EventsTest implements ModInitializer {
    private static final boolean testMobInitializeEvent = false;
    private static final boolean testMobInitGoalsEvent = false;
    private static final boolean testEntitySpawnEvent = false;
    private static final boolean testWithinStructureEvent = false;
    private static final boolean testEntityWriteNbtEvent = false;
    private static final boolean testEntityDataTrackEvent = false;
    private static final boolean testEntityStruckByLightningEvent = false;
    private static final boolean testEntityChangeDimensionEvent = false;
    private static final boolean testAnimalBreedEventPre = false;
    private static final boolean testAnimalBreedEventPost = false;
    private static final boolean testAnimalEatEvent = false;
    private static final boolean testAnimalGrowUpEvent = false;
    private static final boolean testAnimalLoveEvent = false;
    private static final boolean testLivingEquipStackEvent = false;
    private static final boolean testLivingEatFoodEvent = false;
    private static final boolean testLivingDropLootEvent = false;
    private static final boolean testPlayerLevelUpEvent = false;
    private static final boolean testPlayerRespawnEvent = false;
    private static final boolean testProjectileFireEvent = false;
    private static final boolean testProjectileImpactEvent = false;
    private static final boolean testItemCraftEvent = false;
    private static final boolean testItemEnchantEvent = false;
    private static final boolean testBlockPlaceEvent = false;
    private static final boolean testBlockBreakEvent = false;
    private static final boolean testBlockChangeEvent = false;
    private static final boolean testCropGrowEvent = false;
    private static final boolean testWorldExplodeEvent = false;
    private static final boolean testSleepExplodeEvent = false;
    private static final boolean testSleepSleepEvent = false;
    private static final boolean testSleepWakeUpEvent = false;
    private static final boolean testFishingCastEvent = false;
    private static final boolean testFishingCatchEvent = false;
    private static final boolean testLanguageTranslationLoadEvent = true;

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
            AnimalEntityEvents.PRE_BREED.register((world, animal, other, baby) -> {
                System.out.println("Animal Breed (Pre):");
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    other=" + other.getType().getTranslationKey());
                System.out.println("    baby=" + baby.getType().getTranslationKey());
                return true;
            });
        }

        if (testAnimalBreedEventPost) {
            AnimalEntityEvents.POST_BREED.register((world, animal, other, baby) -> {
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
            BlockEvents.PLACE.register((state, context) -> {
                System.out.println("Block Place:");
                System.out.println("    block=" + state.getBlock().getTranslationKey());

                return state.getBlock() != Blocks.STONE;
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

        if (testCropGrowEvent) {
            CropEvents.GROW.register((world, pos, state, growState, age, moisture) -> {
                System.out.println("Crop Grow:");
                System.out.println("    world=" + world.getDimensionKey().toString());
                System.out.println("    pos=" + pos.toShortString());
                System.out.println("    state=" + state.getBlock().getTranslationKey());
                System.out.println("    growState=" + growState.getBlock().getTranslationKey());
                System.out.println("    age=" + age);
                System.out.println("    moisture=" + moisture);

                // Only grow crops in the Overworld
                return world.getDimensionKey() == DimensionTypes.OVERWORLD;
            });
        }

        if (testAnimalEatEvent) {
            AnimalEntityEvents.EAT.register((player, hand, food, animal, age, overGrow) -> {
                System.out.println("Animal Eat:");
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    hand=" + hand.name());
                System.out.println("    food=" + food.getTranslationKey());
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    age=" + age);
                System.out.println("    overGrow=" + overGrow);

                return true;
            });
        }

        if (testAnimalGrowUpEvent) {
            AnimalEntityEvents.GROW_UP.register((passive, age) -> {
                System.out.println("Animal Grow Up:");
                System.out.println("    passive=" + passive.getType().getTranslationKey());
                System.out.println("    age=" + age);

                // Baby animals never grow up
                return true;
            });
        }

        if (testAnimalLoveEvent) {
            AnimalEntityEvents.LOVE.register((player, animal, age, overGrow) -> {
                System.out.println("Animal Love:");
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    age=" + age);
                System.out.println("    overGrow=" + overGrow);

                return false;
            });
        }

        if (testEntityStruckByLightningEvent) {
            EntityEvents.STRUCK_BY_LIGHTNING.register((serverWorld, entity, lightning) -> {
                System.out.println("Entity Struck By Lightning:");
                System.out.println("    world=" + serverWorld.getDimensionKey().toString());
                System.out.println("    entity=" + entity.getType().getTranslationKey());
            });
        }

        if (testEntityChangeDimensionEvent) {
            EntityEvents.CHANGE_DIMENSION.register((world, destination, entity, copy, teleportTarget) -> {
                System.out.println("Entity Change Dimension:");
                System.out.println("    world=" + world.getDimensionKey().toString());
                System.out.println("    destination=" + destination.getDimensionKey().toString());
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    copy=" + copy.getType().getTranslationKey());
                System.out.println("    teleportTarget=" + teleportTarget.position.toString());

                // limit player to only the Overworld & Nether
                return destination.getDimensionKey() == DimensionTypes.THE_NETHER || destination.getDimensionKey() == DimensionTypes.OVERWORLD;
            });
        }

        if (testMobInitGoalsEvent) {
            MobEntityEvents.INIT_GOALS.register((world, mob, goalSelector, targetSelector) -> {
                System.out.println("Mob Post Init Goals:");
                System.out.println("    world=" + world.getDimensionKey().toString());
                System.out.println("    mob=" + mob.getType().getTranslationKey());

                // all goal & target selectors should have at least 1 goal
                System.out.println("    pre-goalSelector=" + goalSelector.getGoals().size());
                System.out.println("    pre-targetSelector=" + targetSelector.getGoals().size());

                // add 2 goals to modify all goal & target selectors
                goalSelector.add(100, new Goal() {
                    @Override
                    public boolean canStart() {
                        return false;
                    }
                });
                targetSelector.add(100, new Goal() {
                    @Override
                    public boolean canStart() {
                        return false;
                    }
                });

                if (goalSelector.getGoals().isEmpty() || targetSelector.getGoals().isEmpty())
                    System.out.println("ERROR, goals have been unsuccessfully added to selectors.");

                // all goal & target selectors should have at least 1 goal
                System.out.println("    post-goalSelector=" + goalSelector.getGoals().size());
                System.out.println("    post-targetSelector=" + targetSelector.getGoals().size());

            });
        }

        if (testPlayerRespawnEvent) {
            PlayerEntityEvents.RESPAWN.register((player, alive) -> {
                System.out.println("Player Respawn:");
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    alive=" + alive);
            });
        }

        if (testFishingCastEvent) {
            FishingEvents.CAST.register((world, user, hand, fishingRod, lure, luck) -> {
                System.out.println("Fishing Cast:");
                System.out.println("    world=" + world.getDimensionKey().toString());
                System.out.println("    user=" + user.getDisplayName().getString());
                System.out.println("    hand=" + hand.name());
                System.out.println("    fishingRod=" + fishingRod.getTranslationKey());
                System.out.println("    lure=" + lure);
                System.out.println("    luck=" + luck);

                return true;
            });
        }

        if (testFishingCatchEvent) {
            FishingEvents.REELED_IN.register((world, user, hand, fishingRod) -> {
                System.out.println("Fishing Catch:");
                System.out.println("    world=" + world.getDimensionKey().toString());
                System.out.println("    user=" + user.getDisplayName().getString());
                System.out.println("    hand=" + hand.name());
                System.out.println("    fishingRod=" + fishingRod.getTranslationKey());

                return false;
            });
        }

        if (testSleepExplodeEvent) {
            SleepEvents.EXPLODE.register((state, world, pos, player, hand, hit) -> {
                System.out.println("Sleep Explode:");
                System.out.println("    state=" + state.getBlock().getTranslationKey());
                System.out.println("    world=" + world.getDimensionKey().toString());
                System.out.println("    pos=" + pos.toShortString());
                System.out.println("    player=" + player.getDisplayName().getString());
                System.out.println("    hand=" + hand.name());
                System.out.println("    hit=" + hit.getType().name());

                if (!world.isClient) {
                    world.removeBlock(pos, false);
                    BlockPos offset = pos.offset((state.get(BedBlock.FACING)).getOpposite());
                    if (world.getBlockState(offset).isIn(BlockTags.BEDS))
                        world.removeBlock(offset, false);

                    world.createExplosion(player, world.getDamageSources().badRespawnPoint(pos.toCenterPos()), null, pos.toCenterPos(), 10f, true, World.ExplosionSourceType.BLOCK);
                }

                return false;
            });
        }

        if (testSleepSleepEvent) {
            SleepEvents.SLEEP.register((entity, pos, either) -> {
                System.out.println("Sleep Sleep:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    pos=" + pos.toShortString());
                either.right().ifPresent(unit -> {
                    System.out.println("    either right=" + either.right().get().name());
                });
                either.left().ifPresent(unit -> {
                    System.out.println("    either left=" + either.left().get().name());
                });

                return false;
            });
        }

        if (testSleepWakeUpEvent) {
            SleepEvents.WAKE_UP.register((entity, skipSleepTimer, updateSleepingPlayers) -> {
                System.out.println("Sleep Wake Up:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    skipSleepTimer=" + skipSleepTimer);
                System.out.println("    updateSleepingPlayers=" + updateSleepingPlayers);

                return false;
            });
        }

        if (testLanguageTranslationLoadEvent) {
            LanguageEvents.LOAD_TRANSLATION.register((langCode, consumer, translationKey, translated) -> {
                if (translationKey.startsWith("block.")) {
                    if (translationKey.contains("chiseled_")) {
                        System.out.println(translationKey);
                        consumer.accept(translationKey, translated.replace("Chiseled ", ""));
                        return false;
                    }
                }
                return true;
            });
        }
    }
}
