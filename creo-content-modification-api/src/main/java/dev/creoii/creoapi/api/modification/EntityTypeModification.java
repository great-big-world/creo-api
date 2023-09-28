package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.EntityTypeModificationImpl;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface EntityTypeModification {
    EntityTypeModification INSTANCE = new EntityTypeModificationImpl();

    /**
     * Set the entity type's spawn group.
     * @param entityType the entity type
     * @param spawnGroup the new {@link SpawnGroup}
     */
    void setSpawnGroup(EntityType<?> entityType, SpawnGroup spawnGroup);

    /**
     * Set the entity type's primary spawn egg color.
     * @param entityType the entity type
     * @param color the new color
     */
    void setPrimarySpawnEggColor(EntityType<?> entityType, int color);

    /**
     * Get the primary color of the entity type's spawn egg.
     * @param entityType the entity type
     * @return the primary color of the entity type's spawn egg
     */
    int getPrimarySpawnEggColor(EntityType<?> entityType);

    /**
     * Set the entity type's secondary spawn egg color.
     * @param entityType the entity type
     * @param color the new color
     */
    void setSecondarySpawnEggColor(EntityType<?> entityType, int color);

    /**
     * Get the secondary color of the entity type's spawn egg.
     * @param entityType the entity type
     * @return the secondary color of the entity type's spawn egg
     */
    int getSecondarySpawnEggColor(EntityType<?> entityType);

    /**
     * Set whether the entity type is summonable.
     * @param entityType the entity type
     * @param summonable whether the entity type is summonable
     */
    void setSummonable(EntityType<?> entityType, boolean summonable);

    /**
     * Set whether the entity type is immune to fire damage.
     * @param entityType the entity type
     * @param fireImmune whether the entity type is immunt to fire damage
     */
    void setFireImmune(EntityType<?> entityType, boolean fireImmune);

    /**
     * Set whether the entity type can spawn far from the player.
     * @param entityType the entity type
     * @param spawnableFarFromPlayer whether the entity type can spawn far from the player
     */
    void setSpawnableFarFromPlayer(EntityType<?> entityType, boolean spawnableFarFromPlayer);

    /**
     * Set the entity type's loot table id.
     * @param entityType the entity type
     * @param lootTableId the new loot table id
     */
    void setLootTableId(EntityType<?> entityType, Identifier lootTableId);

    /**
     * Set the entity type's dimensions.
     * @param entityType the entity type
     * @param dimensions the new {@link EntityDimensions}
     */
    void setDimensions(EntityType<?> entityType, EntityDimensions dimensions);
}