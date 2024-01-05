package dev.creoii.creoapi.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.block.BlockImpl;
import net.minecraft.fluid.FluidState;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {
    @Inject(method = "place", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0))
    private void creo_applyOnPlacedByStructureB(ServerWorldAccess world, BlockPos pos, BlockPos pivot, StructurePlacementData placementData, Random random, int flags, CallbackInfoReturnable<Boolean> cir, @Local BlockBox blockBox, @Local StructureTemplate.StructureBlockInfo structureBlockInfo, @Local FluidState fluidState) {
        BlockImpl.applyOnPlacedByStructure(world, pivot, placementData, random, structureBlockInfo, structureBlockInfo.pos(), fluidState, structureBlockInfo.state(), (StructureTemplate) (Object) this);
    }
}
