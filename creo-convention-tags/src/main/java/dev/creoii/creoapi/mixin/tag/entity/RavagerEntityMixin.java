package dev.creoii.creoapi.mixin.tag.entity;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RavagerEntity.class)
public abstract class RavagerEntityMixin extends RaiderEntity {
    protected RavagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
    private void creo_ravagerBreakables(CallbackInfo ci, @Local boolean bl, @Local BlockPos blockPos, @Local BlockState blockState) {
        BlockTagImpl.applyRavagerBreakable(getWorld(), blockState, blockPos, (RavagerEntity) (Object) this, bl);
    }
}
