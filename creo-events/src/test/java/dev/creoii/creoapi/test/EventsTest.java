package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.event.entity.EntitySpawnCallback;
import dev.creoii.creoapi.api.event.entity.MobInitializeCallback;
import dev.creoii.creoapi.api.event.entity.WithinStructureCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.Items;

public class EventsTest implements ModInitializer {
    private static final boolean testMobInitializeCallback = false;
    private static final boolean testEntitySpawnCallback = false;
    private static final boolean testWithinStructureCallback = false;

    @Override
    public void onInitialize() {
        if (testMobInitializeCallback) {
            MobInitializeCallback.EVENT.register((world, mob, difficulty, spawnReason, entityData, entityNbt) -> {
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
            EntitySpawnCallback.EVENT.register((serverWorld, entity) -> {
                System.out.println("Entity Spawn:");
                System.out.println("    type=" + entity.getType().getTranslationKey());
                return true;
            });
        }

        if (testWithinStructureCallback) {
            WithinStructureCallback.EVENT.register((serverWorld, entity, structureStart) -> {
                System.out.println("Within Structure:");
                System.out.println("    entity=" + entity.getType().getTranslationKey());
                System.out.println("    structure=" + structureStart.getStructure().getType().toString());
            });
        }
    }
}
