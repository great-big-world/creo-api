package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow private World world;
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow public abstract ChunkPos getChunkPos();

    @Inject(method = "tick", at = @At("TAIL"))
    private void creo_enterStructureCallback(CallbackInfo ci) {
        EntityEventImpl.applyWithinStructureCallback(world, (Entity) (Object) this, getBlockPos(), getChunkPos());
    }
}
