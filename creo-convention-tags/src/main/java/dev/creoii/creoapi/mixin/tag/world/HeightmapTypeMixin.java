package dev.creoii.creoapi.mixin.tag.world;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.world.Heightmap;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Heightmap.Type.class)
public class HeightmapTypeMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Heightmap.Type create(String internalName, int internalId, String name, Heightmap.Purpose purpose, Predicate<BlockState> blockPredicate) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable private static Heightmap.Type[] field_13199;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/world/Heightmap$Type;field_13199:[Lnet/minecraft/world/Heightmap$Type;", shift = At.Shift.AFTER))
    private static void creo_addWeatherHeightmap(CallbackInfo ci) {
        field_13199 = BlockTagImpl.addWeatherHeightmap(field_13199, HeightmapTypeMixin::create);
    }
}