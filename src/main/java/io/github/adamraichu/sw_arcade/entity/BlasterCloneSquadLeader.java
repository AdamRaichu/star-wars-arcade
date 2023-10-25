package io.github.adamraichu.sw_arcade.entity;

import io.github.adamraichu.sw_arcade.entity.helpers.AbstractGoodGuy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class BlasterCloneSquadLeader extends AbstractGoodGuy implements RangedAttackMob {
  private boolean shouldShoot = false;

  public BlasterCloneSquadLeader(EntityType<? extends BlasterCloneSquadLeader> type, World world) {
    super(type, world);
  }

  @Override
  public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
    if (hand.equals(Hand.MAIN_HAND)) {
      this.shouldShoot = true;
    }
    return super.interactAt(player, hitPos, hand);
  }

  @Override
  protected void initGoals() {
    super.initGoals();
    this.goalSelector.add(4, new ProjectileAttackGoal(this, 1.0, 20, 15.0f));
    this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
    this.goalSelector.add(6, new LookAroundGoal(this));
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(new AnimationController<>(this, 10, state -> state.setAndContinue(getShootAnimation())));
  }

  private RawAnimation getShootAnimation() {
    if (this.shouldShoot) {
      this.shouldShoot = false;
      return RawAnimation
          .begin()
          .thenPlay("clone_default.point_and_shoot");
    }
    return null;
  }

  @Override
  public void shootAt(LivingEntity target, float pullProgress) {
    BlueBlasterBoltEntity projectile = new BlueBlasterBoltEntity(this.getWorld(), this);
    double d = target.getEyeY() - (double) 2.1f;
    double e = target.getX() - this.getX();
    double f = d - projectile.getY();
    double g = target.getZ() - this.getZ();
    double h = Math.sqrt(e * e + g * g) * (double) 0.2f;
    projectile.setVelocity(e, f + h, g, 1.6f, 0.5f);
    this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 0.4f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
    this.getWorld().spawnEntity(projectile);
  }
}
