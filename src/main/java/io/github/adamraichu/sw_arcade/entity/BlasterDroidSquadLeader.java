package io.github.adamraichu.sw_arcade.entity;

import io.github.adamraichu.sw_arcade.entity.helper.AbstractBadGuy;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;

public class BlasterDroidSquadLeader extends AbstractBadGuy {
  public BlasterDroidSquadLeader(EntityType<? extends AbstractBadGuy> type, World worldIn) {
    super(type, worldIn);
  }

  @Override
  public void registerControllers(ControllerRegistrar controllers) {

  }
}