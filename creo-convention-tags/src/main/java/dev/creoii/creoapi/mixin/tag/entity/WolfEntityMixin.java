package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {
    @Mutable @Shadow @Final public static Predicate<LivingEntity> FOLLOW_TAMED_PREDICATE;

    static {
        FOLLOW_TAMED_PREDICATE = EntityTypeTagImpl::applyWolfPrey;
    }
}
