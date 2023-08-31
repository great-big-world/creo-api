package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.EntityTypeModificationImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public interface EntityTypeModification {
    EntityTypeModification INSTANCE = new EntityTypeModificationImpl();

    void setSpawnGroup(EntityType<?> entityType, SpawnGroup spawnGroup);

    SpawnGroup getSpawnGroup(EntityType<?> entityType);
}