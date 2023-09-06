package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.Identifier;

/**
 * Callback for when a {@link LivingEntity} drops loot.
 * <p>
 * Return false to stop the loot from being dropped.
 */
public interface LivingDropLootCallback {
    Event<LivingDropLootCallback> EVENT = EventFactory.createArrayBacked(LivingDropLootCallback.class,
            (listeners) -> (livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer) -> {
                for (LivingDropLootCallback event : listeners) {
                    return event.dropLoot(livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer);
                }

                return true;
            }
    );

    boolean dropLoot(LivingEntity livingEntity, Identifier identifier, LootTable lootTable, DamageSource damageSource, LootContextParameterSet lootContextParameterSet, boolean causedByPlayer);
}
