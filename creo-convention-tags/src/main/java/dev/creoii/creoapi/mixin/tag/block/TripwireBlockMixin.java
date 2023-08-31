package dev.creoii.creoapi.mixin.tag.block;

import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.TripwireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TripwireBlock.class)
public class TripwireBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void creo_applyTripwireIgnores(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        ItemTagImpl.applyTripwireIgnores(entity, ci);
        EntityTypeTagImpl.applyTripwireIgnores(entity, ci);
    }
}
