package io.github.adamraichu.sw_arcade.entity.building.cannon;

import io.github.adamraichu.sw_arcade.entity.helper.AbstractGoodGuy;
import io.github.adamraichu.sw_arcade.entity.helper.Building;
import io.github.adamraichu.sw_arcade.game.Team.Republic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;

public class Av7Cannon extends AbstractGoodGuy implements Building, RangedAttackMob {

  public Av7Cannon(EntityType<? extends AbstractGoodGuy> type, World world) {
    super(type, world);
  }

  @Override
  public Republic getTeam() {
    return Republic.get();
  }

  @Override
  public void registerControllers(ControllerRegistrar controllers) {
  }

  @Override
  public void shootAt(LivingEntity var1, float var2) {
  }

}
