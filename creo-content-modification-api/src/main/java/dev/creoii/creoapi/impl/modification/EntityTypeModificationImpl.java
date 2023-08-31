package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.EntityTypeModification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class EntityTypeModificationImpl implements EntityTypeModification {
    @Override
    public void setSpawnGroup(EntityType<?> entityType, SpawnGroup spawnGroup) {

    }

    @Override
    public SpawnGroup getSpawnGroup(EntityType<?> entityType) {
        return null;
    }
}