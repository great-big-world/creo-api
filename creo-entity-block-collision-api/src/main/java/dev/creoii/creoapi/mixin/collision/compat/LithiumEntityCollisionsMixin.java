package dev.creoii.creoapi.mixin.collision.compat;

import dev.creoii.creoapi.impl.collision.EntityBlockCollisionImpl;
import me.jellysquid.mods.lithium.common.entity.LithiumEntityCollisions;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LithiumEntityCollisions.class)
public class LithiumEntityCollisionsMixin {
    @Inject(method = "getBlockCollisions(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private static void creo_lithiumCollisionCompat(World world, Entity entity, Box box, CallbackInfoReturnable<List<VoxelShape>> cir) {
        EntityBlockCollisionImpl.applyEntityBlockCollisionLithium(world, entity, box, cir);
    }
}
