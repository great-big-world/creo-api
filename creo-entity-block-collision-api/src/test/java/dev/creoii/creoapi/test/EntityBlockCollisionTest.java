package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.collision.EntityBlockCollision;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;

public class EntityBlockCollisionTest implements ModInitializer {
    @Override
    public void onInitialize() {
        EntityBlockCollision.register(EntityType.PLAYER, EntityBlockCollision.create(context -> {
            if (context.state().isOf(Blocks.STONE))
                return false;
            return (context.entity().isDescending() && context.entity().verticalCollision) || context.entity().horizontalCollision;
        }));
    }
}
