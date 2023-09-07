package dev.creoii.creoapi.mixin.worldgen;

import com.mojang.datafixers.DataFixer;
import dev.creoii.creoapi.impl.worldgen.util.AwareNoiseConfig;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.thread.ThreadExecutor;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.ChunkStatusChangeListener;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorageMixin {
    @Shadow @Final private NoiseConfig noiseConfig;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/noise/NoiseConfig;create(Lnet/minecraft/world/gen/chunk/ChunkGeneratorSettings;Lnet/minecraft/registry/RegistryEntryLookup;J)Lnet/minecraft/world/gen/noise/NoiseConfig;", ordinal = 0, shift = At.Shift.BY, by = 2))
    private void creo_makeNoiseConfigAware(ServerWorld world, LevelStorage.Session session, DataFixer dataFixer, StructureTemplateManager structureTemplateManager, Executor executor, ThreadExecutor<Runnable> mainThreadExecutor, ChunkProvider chunkProvider, ChunkGenerator chunkGenerator, WorldGenerationProgressListener worldGenerationProgressListener, ChunkStatusChangeListener chunkStatusChangeListener, Supplier<PersistentStateManager> persistentStateManagerFactory, int viewDistance, boolean dsync, CallbackInfo ci) {
        ((AwareNoiseConfig) noiseConfig).creo_setWorld(world);
        ((AwareNoiseConfig) noiseConfig).creo_setChunkGenerator(chunkGenerator);
    }
}
