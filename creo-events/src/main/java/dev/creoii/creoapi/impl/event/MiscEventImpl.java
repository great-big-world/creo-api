package dev.creoii.creoapi.impl.event;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Either;
import dev.creoii.creoapi.api.event.misc.FishingEvents;
import dev.creoii.creoapi.api.event.misc.LanguageEvents;
import dev.creoii.creoapi.api.event.misc.RecipeEvents;
import dev.creoii.creoapi.api.event.misc.SleepEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiConsumer;

@ApiStatus.Internal
public class MiscEventImpl {
    public static void applyFishingRodCastEvent(World world, PlayerEntity user, Hand hand, ItemStack itemStack, int lure, int luck, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean result = FishingEvents.CAST.invoker().onCast(world, user, hand, itemStack, lure, luck);

        if (!result)
            cir.setReturnValue(TypedActionResult.pass(itemStack));
    }

    public static void applyFishingRodCatchEvent(World world, PlayerEntity user, Hand hand, ItemStack itemStack, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean result = FishingEvents.REELED_IN.invoker().onReeledIn(world, user, hand, itemStack);

        if (!result)
            cir.setReturnValue(TypedActionResult.pass(itemStack));
    }

    public static void applySleepExplodeEvent(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        boolean result = SleepEvents.EXPLODE.invoker().onExplode(state, world, pos, player, hand, hit);

        if (!result)
            cir.setReturnValue(ActionResult.FAIL);
    }

    public static void applySleepSleepEvent(Entity entity, BlockPos pos, Either<PlayerEntity.SleepFailureReason, Unit> either, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> cir) {
        boolean result = SleepEvents.SLEEP.invoker().onSleep(entity, pos, either);

        if (!result)
            cir.setReturnValue(Either.left(either.left().orElse(PlayerEntity.SleepFailureReason.OTHER_PROBLEM)));
        else
            cir.setReturnValue(Either.right(Unit.INSTANCE));
    }

    public static void applySleepWakeUpEvent(Entity entity, boolean skipSleepTimer, boolean updateSleepingPlayers, CallbackInfo ci) {
        boolean result = SleepEvents.WAKE_UP.invoker().onWakeUp(entity, skipSleepTimer, updateSleepingPlayers);

        if (!result)
            ci.cancel();
    }

    public static boolean applyLanguageTranslationLoadEvent(@Nullable String langCode, BiConsumer<String, String> entryConsumer, String key, String value) {
        return LanguageEvents.LOAD_TRANSLATION.invoker().onLoadTranslation(langCode, entryConsumer, key, value);
    }

    public static ImmutableMap.Builder<Identifier, RecipeEntry<?>> applyRecipeLoadEvent(ImmutableMap.Builder<Identifier, RecipeEntry<?>> builder, Identifier identifier, RecipeEntry<?> recipeEntry) {
        boolean result = RecipeEvents.LOAD_RECIPE.invoker().onLoadRecipe(builder, recipeEntry);

        if (!result)
            return builder;

        return builder.put(identifier, recipeEntry);
    }
}
