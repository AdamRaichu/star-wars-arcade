package io.github.adamraichu.sw_arcade.entity.helpers;

import io.github.adamraichu.sw_arcade.entity.EvilCloneTest;
import io.github.adamraichu.sw_arcade.game.Team.Republic;
import io.github.adamraichu.sw_arcade.game.Team.Separatists;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.world.World;

public abstract class AbstractGoodGuy extends TeamAwareEntity<Republic> {
  public AbstractGoodGuy(EntityType<? extends AbstractGoodGuy> type, World world) {
    super(Republic.get(), Separatists.get(), type, world);
  }

  @Override
  protected void initGoals() {
    this.targetSelector.add(1, new ActiveTargetGoal<EvilCloneTest>(this, EvilCloneTest.class, false));
  }
}
