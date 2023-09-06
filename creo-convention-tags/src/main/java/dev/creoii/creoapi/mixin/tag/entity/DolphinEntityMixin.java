package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {
    @Mutable @Shadow @Final public static Predicate<ItemEntity> CAN_TAKE;

    static {
        CAN_TAKE = ItemTagImpl.applyDolphinIgnores();
    }
}
