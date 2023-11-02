package io.github.adamraichu.sw_arcade.entity;

import io.github.adamraichu.sw_arcade.entity.helper.AbstractBadGuy;
import io.github.adamraichu.sw_arcade.entity.projectile.BlueBlasterBoltEntity;
import io.github.adamraichu.sw_arcade.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;

public class BlasterDroidSquadLeader extends AbstractBadGuy implements RangedAttackMob {

  public BlasterDroidSquadLeader(EntityType<? extends AbstractBadGuy> type, World worldIn) {
    super(type, worldIn);
  }

  @Override
  public void registerControllers(ControllerRegistrar controllers) {

  }

  @Override
  protected void initGoals() {
    super.initGoals();
    this.goalSelector.add(4, new ProjectileAttackGoal(this, 1.0, 20, 25.0f));
    this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
    this.goalSelector.add(6, new LookAroundGoal(this));
  }

  @Override
  public void shootAt(LivingEntity target, float pullProgress) {
    BlueBlasterBoltEntity projectile = new BlueBlasterBoltEntity(this.getWorld(), this);
    double d = target.getEyeY() - (double) 1.1f;
    double e = target.getX() - this.getX();
    double f = d - projectile.getY();
    double g = target.getZ() - this.getZ();
    double h = Math.sqrt(e * e + g * g) * (double) 0.2f;
    projectile.setVelocity(e, f + h, g, 1.6f, 0.5f);
    this.playSound(SoundRegistry.CLONE_BLASTER_FIRE, 1.0f, 0.4f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
    this.getWorld().spawnEntity(projectile);
  }
}
