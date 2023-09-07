package dev.creoii.creoapi.mixin.worldgen;

import dev.creoii.creoapi.impl.worldgen.util.AwareNoiseConfig;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NoiseConfig.class)
public class NoiseConfigMixin implements AwareNoiseConfig {
    @Unique private ServerWorld creo_serverWorld;
    @Unique private ChunkGenerator creo_chunkGenerator;

    @Override
    public ServerWorld creo_getWorld() {
        return creo_serverWorld;
    }

    @Override
    public void creo_setWorld(ServerWorld serverWorld) {
        creo_serverWorld = serverWorld;
    }

    @Override
    public ChunkGenerator creo_getChunkGenerator() {
        return creo_chunkGenerator;
    }

    @Override
    public void creo_setChunkGenerator(ChunkGenerator chunkGenerator) {
        creo_chunkGenerator = chunkGenerator;
    }
}
