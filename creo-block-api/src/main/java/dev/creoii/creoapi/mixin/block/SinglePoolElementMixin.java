package dev.creoii.creoapi.mixin.block;

import dev.creoii.creoapi.api.block.CreoBlock;
import net.minecraft.block.Block;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SinglePoolElement.class)
public class SinglePoolElementMixin {
    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/pool/SinglePoolElement;method_16756(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/structure/StructureTemplate$StructureBlockInfo;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/BlockRotation;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/util/math/BlockBox;)V"))
    private void creo_placedByStructureBlock(StructureTemplateManager structureTemplateManager, StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, BlockPos pos, BlockPos pivot, BlockRotation rotation, BlockBox box, Random random, boolean keepJigsaws, CallbackInfoReturnable<Boolean> cir) {
        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof CreoBlock creoBlock) {
            creoBlock.onPlacedByStructure(structureTemplateManager, world, structureAccessor, chunkGenerator, pos, pivot, rotation, box, random, keepJigsaws);
        }
    }
}
