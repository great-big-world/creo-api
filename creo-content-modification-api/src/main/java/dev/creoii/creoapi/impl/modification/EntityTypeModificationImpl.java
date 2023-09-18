package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.EntityTypeModification;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class EntityTypeModificationImpl implements EntityTypeModification {
    @Override
    public void setSpawnGroup(EntityType<?> entityType, SpawnGroup spawnGroup) {

    }

    @Override
    public SpawnGroup getSpawnGroup(EntityType<?> entityType) {
        return null;
    }

    @Override
    public void setPrimarySpawnEggColor(EntityType<?> entityType, int color) {

    }

    @Override
    public int getPrimarySpawnEggColor(EntityType<?> entityType) {
        return 0;
    }

    @Override
    public void setSecondarySpawnEggColor(EntityType<?> entityType, int color) {

    }

    @Override
    public int getSecondarySpawnEggColor(EntityType<?> entityType) {
        return 0;
    }

    @Override
    public void setSummonable(EntityType<?> entityType, boolean spawnGroup) {

    }

    @Override
    public boolean isSummonable(EntityType<?> entityType) {
        return false;
    }

    @Override
    public void setFireImmune(EntityType<?> entityType, boolean spawnGroup) {

    }

    @Override
    public boolean isFireImmune(EntityType<?> entityType) {
        return false;
    }

    @Override
    public void setSpawnableFarFromPlayer(EntityType<?> entityType, boolean spawnGroup) {

    }

    @Override
    public boolean isSpawnableFarFromPlayer(EntityType<?> entityType) {
        return false;
    }

    @Override
    public void setLootTableId(EntityType<?> entityType, Identifier spawnGroup) {

    }

    @Override
    public Identifier getLootTableId(EntityType<?> entityType) {
        return null;
    }

    @Override
    public void setDimensions(EntityType<?> entityType, EntityDimensions spawnGroup) {

    }

    @Override
    public EntityDimensions getDimensions(EntityType<?> entityType) {
        return null;
    }
}