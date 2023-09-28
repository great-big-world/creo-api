package dev.creoii.creoapi.mixin.modification;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityType.class)
public interface EntityTypeAccessor {
    @Accessor("spawnGroup")
    void setSpawnGroup(SpawnGroup spawnGroup);

    @Accessor("summonable")
    void setSummonable(boolean summonable);

    @Accessor("fireImmune")
    void setFireImmune(boolean fireImmune);

    @Accessor("spawnableFarFromPlayer")
    void setSpawnableFarFromPlayer(boolean spawnableFarFromPlayer);

    @Accessor("lootTableId")
    void setLootTableId(Identifier lootTableId);

    @Accessor("dimensions")
    void setDimensions(EntityDimensions dimensions);
}
