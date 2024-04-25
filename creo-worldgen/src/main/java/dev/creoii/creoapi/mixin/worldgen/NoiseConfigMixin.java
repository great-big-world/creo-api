package dev.creoii.creoapi.mixin.worldgen;

import dev.creoii.creoapi.impl.worldgen.util.WorldAwareNoiseConfig;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NoiseConfig.class)
public class NoiseConfigMixin implements WorldAwareNoiseConfig {
    @Unique private ServerWorld creo_serverWorld;

    @Override
    public ServerWorld creo$getWorld() {
        return creo_serverWorld;
    }

    @Override
    public void creo$setWorld(ServerWorld serverWorld) {
        creo_serverWorld = serverWorld;
    }
}
