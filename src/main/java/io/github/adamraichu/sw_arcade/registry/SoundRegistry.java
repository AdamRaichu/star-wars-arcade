package io.github.adamraichu.sw_arcade.registry;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
  public static final SoundEvent CLONE_BLASTER_FIRE = register(
      new Identifier(StarWarsArcadeMode.MOD_ID, "clone_blaster"));

  private static SoundEvent register(Identifier id) {
    return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
  }
}
