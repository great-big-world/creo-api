package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.event.entity.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.Items;

public class EventsTest implements ModInitializer {
    private static final boolean testMobInitializeCallback = false;
    private static final boolean testEntitySpawnCallback = false;
    private static final boolean testWithinStructureCallback = false;
    private static final boolean testAnimalBreedCallbackPre = true;
    private static final boolean testAnimalBreedCallbackPost = true;
    private static final boolean testLivingEquipStackCallback = true;
    private static final boolean testLivingEatFoodCallback = true;
    private static final boolean testLivingDropLootCallback = true;

    @Override
    public void onInitialize() {
        if (testMobInitializeCallback) {
            MobEvents.INITIALIZE.register((world, mob, difficulty, spawnReason, entityData, entityNbt) -> {
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

        if (testEntitySpawnCallback) {
            EntityEvents.SPAWN.register((serverWorld, entity) -> {
                System.out.println("Entity Spawn:");
                System.out.println("    type=" + entity.getType().getTranslationKey());
                return true;
            });
        }

        if (testWithinStructureCallback) {
            EntityEvents.WITHIN_STRUCTURE.register((serverWorld, entity, structureStart) -> {
                System.out.println("Within Structure:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    structure=" + structureStart.getStructure().getType().toString());
            });
        }

        if (testAnimalBreedCallbackPre) {
            AnimalBreedEvents.PRE.register((world, animal, other, baby) -> {
                System.out.println("Animal Breed (Pre):");
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    other=" + other.getType().getTranslationKey());
                System.out.println("    baby=" + baby.getType().getTranslationKey());
                return true;
            });
        }

        if (testAnimalBreedCallbackPost) {
            AnimalBreedEvents.POST.register((world, animal, other, baby) -> {
                System.out.println("Animal Breed (Post):");
                System.out.println("    animal=" + animal.getType().getTranslationKey());
                System.out.println("    other=" + other.getType().getTranslationKey());
                System.out.println("    baby=" + baby.getType().getTranslationKey());

                baby.setBreedingAge(-40);
            });
        }

        if (testLivingEquipStackCallback) {
            LivingEntityEvents.EQUIP_STACK.register((livingEntity, slot, oldStack, newStack) -> {
                System.out.println("Living Equip Stack:");
                System.out.println("    living=" + livingEntity.getType().getTranslationKey());
                System.out.println("    slot=" + slot.getType());
                System.out.println("    oldStack=" + oldStack.getTranslationKey());
                System.out.println("    newStack=" + newStack.getTranslationKey());

                return true;
            });
        }

        if (testLivingEatFoodCallback) {
            LivingEntityEvents.EAT_FOOD.register((world, livingEntity, stack) -> {
                System.out.println("Living Eat Food:");
                System.out.println("    living=" + livingEntity.getType().getTranslationKey());
                System.out.println("    food=" + stack.getTranslationKey());

                return stack;
            });
        }

        if (testLivingDropLootCallback) {
            LivingEntityEvents.DROP_LOOT.register((livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer) -> {
                System.out.println("Living Drop Loot:");
                System.out.println("    living=" + livingEntity.getType().getTranslationKey());
                System.out.println("    id=" + identifier.toString());
                System.out.println("    damage=" + damageSource.getName());

                return true;
            });
        }
    }
}
