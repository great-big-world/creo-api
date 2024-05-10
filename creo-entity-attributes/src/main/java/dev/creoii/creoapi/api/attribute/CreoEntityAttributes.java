package dev.creoii.creoapi.api.attribute;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class CreoEntityAttributes implements ModInitializer {
    private static final String NAMESPACE = "creo";

    /**
     * Controls movement speed while swimming.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_SWIMMING_SPEED = register(new Identifier(NAMESPACE, "generic.swimming_speed"), new ClampedEntityAttribute("attribute.name.generic.swimming_speed", .02d, 0d, 256d).setTracked(true));
    /**
     * Controls movement speed while climbing.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_CLIMBING_SPEED = register(new Identifier(NAMESPACE, "generic.climbing_speed"), new ClampedEntityAttribute("attribute.name.generic.climbing_speed", .2d, 0d, 256d).setTracked(true));
    /**
     * Controls maximum air while drowning.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_MAX_AIR = register(new Identifier(NAMESPACE, "generic.max_air"), new ClampedEntityAttribute("attribute.name.generic.max_air", 30d, 0d, 1024d).setTracked(true));
    /**
     * Controls the block placing cooldown.
     */
    public static final RegistryEntry<EntityAttribute> PLACE_COOLDOWN = register(new Identifier(NAMESPACE, "player.place_cooldown"), new ClampedEntityAttribute("attribute.name.player.place_cooldown", 4d, 0d, 1024d).setTracked(true));
    /**
     * Controls the block breaking cooldown.
     */
    public static final RegistryEntry<EntityAttribute> BREAK_COOLDOWN = register(new Identifier(NAMESPACE, "player.break_cooldown"), new ClampedEntityAttribute("attribute.name.player.break_cooldown", 5d, 0d, 1024d).setTracked(true));

    @Override
    public void onInitialize() {
    }

    private static RegistryEntry<EntityAttribute> register(Identifier id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, id, attribute);
    }
}
