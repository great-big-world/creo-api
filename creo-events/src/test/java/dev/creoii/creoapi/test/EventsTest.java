package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.event.EntitySpawnCallback;
import dev.creoii.creoapi.api.event.MobInitializeCallback;
import net.fabricmc.api.ModInitializer;

public class EventsTest implements ModInitializer {
    @Override
    public void onInitialize() {
        MobInitializeCallback.EVENT.register((world, mob, difficulty, spawnReason, entityData, entityNbt) -> {
            //System.out.println(mob.getType().getTranslationKey());
            return null;
        });

        EntitySpawnCallback.EVENT.register((serverWorld, entity) -> {
            System.out.println(entity.getType().getTranslationKey());
            return true;
        });
    }
}
