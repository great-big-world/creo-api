package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

@Mixin(FoxEntity.class)
public class FoxEntityMixin {
    @Mutable @Shadow @Final static Predicate<ItemEntity> PICKABLE_DROP_FILTER;

    static {
        PICKABLE_DROP_FILTER = ItemTagImpl::applyFoxIgnores;
    }
}
