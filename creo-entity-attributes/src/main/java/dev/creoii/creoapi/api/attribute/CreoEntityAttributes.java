package dev.creoii.creoapi.api.attribute;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CreoEntityAttributes implements ModInitializer {
    public static final String NAMESPACE = "creo";

    /**
     * Controls movement speed while swimming.
     */
    public static final EntityAttribute GENERIC_SWIMMING_SPEED = new ClampedEntityAttribute("attribute.name.generic.swimming_speed", .02d, 0d, 256d).setTracked(true);

    /**
     * Controls movement speed while climbing.
     */
    public static final EntityAttribute GENERIC_CLIMBING_SPEED = new ClampedEntityAttribute("attribute.name.generic.climbing_speed", .2d, 0d, 256d).setTracked(true);

    /**
     * Controls maximum air while drowning.
     */
    public static final EntityAttribute GENERIC_MAX_AIR = new ClampedEntityAttribute("attribute.name.generic.max_air", 30d, 0d, 1024d).setTracked(true);

    /**
     * Controls the block placing cooldown.
     */
    public static final EntityAttribute PLACE_COOLDOWN = new ClampedEntityAttribute("attribute.name.player.place_cooldown", 4d, 0d, 1024d).setTracked(true);

    /**
     * Controls the block breaking cooldown.
     */
    public static final EntityAttribute BREAK_COOLDOWN = new ClampedEntityAttribute("attribute.name.player.break_cooldown", 5d, 0d, 1024d).setTracked(true);

    @Override
    public void onInitialize() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoEntityAttributes.NAMESPACE, "generic.swimming_speed"), GENERIC_SWIMMING_SPEED);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoEntityAttributes.NAMESPACE, "generic.climbing_speed"), GENERIC_CLIMBING_SPEED);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoEntityAttributes.NAMESPACE, "generic.max_air"), GENERIC_MAX_AIR);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoEntityAttributes.NAMESPACE, "player.place_cooldown"), PLACE_COOLDOWN);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoEntityAttributes.NAMESPACE, "player.break_cooldown"), BREAK_COOLDOWN);
    }
}
