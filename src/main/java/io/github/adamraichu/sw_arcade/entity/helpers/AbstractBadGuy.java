package io.github.adamraichu.sw_arcade.entity.helpers;

import io.github.adamraichu.sw_arcade.game.Team.Republic;
import io.github.adamraichu.sw_arcade.game.Team.Separatists;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class AbstractBadGuy extends TeamAwareEntity<Separatists> {
  public AbstractBadGuy(EntityType<? extends AbstractBadGuy> type, World world) {
    super(Separatists.get(), Republic.get(), type, world);
  }
}
