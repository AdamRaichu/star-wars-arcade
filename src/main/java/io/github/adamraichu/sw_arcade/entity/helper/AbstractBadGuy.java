package io.github.adamraichu.sw_arcade.entity.helper;

import io.github.adamraichu.sw_arcade.game.Team.Republic;
import io.github.adamraichu.sw_arcade.game.Team.Separatists;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.world.World;

public abstract class AbstractBadGuy extends TeamAwareEntity<Separatists> {
  public AbstractBadGuy(EntityType<? extends AbstractBadGuy> type, World world) {
    super(Separatists.get(), Republic.get(), type, world);
  }

  @Override
  protected void initGoals() {
    this.targetSelector.add(1, new ActiveTargetGoal<AbstractGoodGuy>(this, AbstractGoodGuy.class, 10, false, true,
        entity -> entity instanceof AbstractGoodGuy));
  }
}
