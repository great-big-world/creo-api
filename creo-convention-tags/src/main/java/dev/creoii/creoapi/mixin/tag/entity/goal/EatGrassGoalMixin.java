package dev.creoii.creoapi.mixin.tag.entity.goal;

import dev.creoii.creoapi.api.tag.CreoBlockTags;
import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(EatGrassGoal.class)
public class EatGrassGoalMixin {
    @Mutable @Shadow @Final private static Predicate<BlockState> GRASS_PREDICATE;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void creo_applyEatenBySheep(CallbackInfo ci) {
        BlockTagImpl.applyEatenBySheep(() -> GRASS_PREDICATE = state -> state.isIn(CreoBlockTags.EATEN_BY_SHEEP));
    }
}
