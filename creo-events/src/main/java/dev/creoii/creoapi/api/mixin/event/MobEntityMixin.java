package dev.creoii.creoapi.api.mixin.event;

import dev.creoii.creoapi.api.event.MobInitializeCallback;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private void creo_mobInitializeCallback(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        EntityData result = MobInitializeCallback.EVENT.invoker().initialize(world, (MobEntity) (Object) this, difficulty, spawnReason, entityData, entityNbt);

        if (result != null) {
            cir.setReturnValue(result);
        }
    }
}
