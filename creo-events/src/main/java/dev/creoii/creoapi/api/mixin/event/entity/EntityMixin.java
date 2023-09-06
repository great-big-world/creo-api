package dev.creoii.creoapi.api.mixin.event.entity;

import dev.creoii.creoapi.api.event.entity.WithinStructureCallback;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
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
    @Shadow public int age;
    @Shadow private World world;
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow public abstract ChunkPos getChunkPos();

    @Inject(method = "tick", at = @At("TAIL"))
    private void creo_enterStructureCallback(CallbackInfo ci) {
        if (age % 10 != 0 || world.isClient) return;
        ServerWorld serverWorld = (ServerWorld) world;
        for (StructureStart structureStart : serverWorld.getStructureAccessor().getStructureStarts(getChunkPos(), structure -> true)) {
            if (structureStart.hasChildren() && structureStart.getBoundingBox().contains(getBlockPos()))
                WithinStructureCallback.EVENT.invoker().enter(serverWorld, (Entity) (Object) this, structureStart);
        }
    }
}
