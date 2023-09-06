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
    SpawnGroup getSpawnGroup();

    @Accessor("summonable")
    boolean isSummonable();

    @Accessor("fireImmune")
    boolean isFireImmune();

    @Accessor("spawnableFarFromPlayer")
    boolean isSpawnableFarFromPlayer();

    @Accessor("lootTableId")
    Identifier getLootTableId();

    @Accessor("dimensions")
    EntityDimensions getDimensions();
}
