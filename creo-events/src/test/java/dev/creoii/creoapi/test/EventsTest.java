package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.event.EntitySpawnCallback;
import dev.creoii.creoapi.api.event.MobInitializeCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class EventsTest implements ModInitializer {
    @Override
    public void onInitialize() {
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

        EntitySpawnCallback.EVENT.register((serverWorld, entity) -> {
            System.out.println("Entity Spawn:");
            System.out.println("    type=" + entity.getType().getTranslationKey());
            return true;
        });
    }
}
