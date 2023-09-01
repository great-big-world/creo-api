package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.EntityTypeModificationImpl;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;

public interface EntityTypeModification {
    EntityTypeModification INSTANCE = new EntityTypeModificationImpl();

    void setSpawnGroup(EntityType<?> entityType, SpawnGroup spawnGroup);

    SpawnGroup getSpawnGroup(EntityType<?> entityType);

    void setPrimarySpawnEggColor(EntityType<?> entityType, int color);

    int getPrimarySpawnEggColor(EntityType<?> entityType);

    void setSecondarySpawnEggColor(EntityType<?> entityType, int color);

    int getSecondarySpawnEggColor(EntityType<?> entityType);

    void setSummonable(EntityType<?> entityType, boolean spawnGroup);

    boolean isSummonable(EntityType<?> entityType);

    void setFireImmune(EntityType<?> entityType, boolean spawnGroup);

    boolean isFireImmune(EntityType<?> entityType);

    void setSpawnableFarFromPlayer(EntityType<?> entityType, boolean spawnGroup);

    boolean isSpawnableFarFromPlayer(EntityType<?> entityType);

    void setLootTableId(EntityType<?> entityType, Identifier spawnGroup);

    Identifier getLootTableId(EntityType<?> entityType);

    void setDimensions(EntityType<?> entityType, EntityDimensions spawnGroup);

    EntityDimensions getDimensions(EntityType<?> entityType);
}