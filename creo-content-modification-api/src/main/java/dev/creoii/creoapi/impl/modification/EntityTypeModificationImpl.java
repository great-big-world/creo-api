package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.EntityTypeModification;
import dev.creoii.creoapi.mixin.modification.EntityTypeAccessor;
import dev.creoii.creoapi.mixin.modification.item.SpawnEggItemAccessor;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class EntityTypeModificationImpl implements EntityTypeModification {
    @Override
    public void setSpawnGroup(EntityType<?> entityType, SpawnGroup spawnGroup) {
        ((EntityTypeAccessor) entityType).setSpawnGroup(spawnGroup);
    }

    @Override
    public void setPrimarySpawnEggColor(EntityType<?> entityType, int color) {
        SpawnEggItem spawnEggItem = SpawnEggItem.forEntity(entityType);
        if (spawnEggItem == null)
            return;
        ((SpawnEggItemAccessor) spawnEggItem).setPrimaryColor(color);
    }

    @Override
    public int getPrimarySpawnEggColor(EntityType<?> entityType) {
        SpawnEggItem spawnEggItem = SpawnEggItem.forEntity(entityType);
        if (spawnEggItem == null)
            return -1;
        return spawnEggItem.getColor(0);
    }

    @Override
    public void setSecondarySpawnEggColor(EntityType<?> entityType, int color) {
        SpawnEggItem spawnEggItem = SpawnEggItem.forEntity(entityType);
        if (spawnEggItem == null)
            return;
        ((SpawnEggItemAccessor) spawnEggItem).setSecondaryColor(color);
    }

    @Override
    public int getSecondarySpawnEggColor(EntityType<?> entityType) {
        SpawnEggItem spawnEggItem = SpawnEggItem.forEntity(entityType);
        if (spawnEggItem == null)
            return -1;
        return spawnEggItem.getColor(1);
    }

    @Override
    public void setSummonable(EntityType<?> entityType, boolean summonable) {
        ((EntityTypeAccessor) entityType).setSummonable(summonable);
    }

    @Override
    public void setFireImmune(EntityType<?> entityType, boolean fireImmune) {
        ((EntityTypeAccessor) entityType).setFireImmune(fireImmune);
    }

    @Override
    public void setSpawnableFarFromPlayer(EntityType<?> entityType, boolean spawnableFarFromPlayer) {
        ((EntityTypeAccessor) entityType).setSpawnableFarFromPlayer(spawnableFarFromPlayer);
    }

    @Override
    public void setLootTableId(EntityType<?> entityType, Identifier lootTableId) {
        ((EntityTypeAccessor) entityType).setLootTableId(lootTableId);
    }

    @Override
    public void setDimensions(EntityType<?> entityType, EntityDimensions dimensions) {
        ((EntityTypeAccessor) entityType).setDimensions(dimensions);
    }
}