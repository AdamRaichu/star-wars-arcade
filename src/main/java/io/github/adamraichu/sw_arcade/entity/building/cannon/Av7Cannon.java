package io.github.adamraichu.sw_arcade.entity.building.cannon;

import org.joml.Vector3f;

import io.github.adamraichu.sw_arcade.entity.helper.AbstractBadGuy;
import io.github.adamraichu.sw_arcade.entity.helper.AbstractGoodGuy;
import io.github.adamraichu.sw_arcade.entity.helper.Building;
import io.github.adamraichu.sw_arcade.entity.projectile.BlueCannonBoltEntity;
import io.github.adamraichu.sw_arcade.game.GameInstance;
// import io.github.adamraichu.sw_arcade.game.Team; //Debug FIXME:
import io.github.adamraichu.sw_arcade.game.Team.Republic;
import io.github.adamraichu.sw_arcade.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;

public class Av7Cannon extends AbstractGoodGuy implements Building, RangedAttackMob {
  private BlockPos baseCenter;

  public Av7Cannon(EntityType<? extends AbstractGoodGuy> type, World world) {
    // Required for /summon
    super(type, world);
  }

  public Av7Cannon(EntityType<? extends AbstractGoodGuy> type, World world, BlockPos baseCenter) {
    super(type, world);

    this.baseCenter = baseCenter;
  }

  @Override
  protected void initGoals() {
    this.targetSelector.add(1, new ActiveTargetGoal<AbstractBadGuy>(this, AbstractBadGuy.class, 10, false, true,
        entity -> entity instanceof AbstractBadGuy && !this.hasPassengers()));
    this.goalSelector.add(4, new ProjectileAttackGoal(this, 0.0, 100, 2500.0f));
  }

  @Override
  public Republic getTeam() {
    return Republic.get();
  }

  @Override
  public void registerControllers(ControllerRegistrar controllers) {
  }

  @Override
  public void shootAt(LivingEntity target, float var2) {
    BlueCannonBoltEntity projectile = new BlueCannonBoltEntity(this.getWorld(), this);
    double d = target.getEyeY() - (double) 2.1f;
    double e = target.getX() - this.getX();
    double f = d - projectile.getY();
    double g = target.getZ() - this.getZ();
    double h = Math.sqrt(e * e + g * g) * (double) 0.2f;
    projectile.setVelocity(e, f + h, g, 1.6f, 0.5f);
    this.playSound(SoundRegistry.CLONE_BLASTER_FIRE, 1.0f, 0.4f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
    this.getWorld().spawnEntity(projectile);
  }

  @Override
  protected ActionResult interactMob(PlayerEntity player, Hand hand) {
    if (this.hasPassengers()) {
      return ActionResult.PASS;
    }
    if (/* Team.areSameTeam(player, this) || */ true /* DEBUG ONLY FIXME: */) {
      mountPlayer(player);
    }
    return ActionResult.PASS;
  }

  private void mountPlayer(PlayerEntity player) {
    World world = this.getWorld();
    if (!world.isClient()) {
      player.setYaw(this.getYaw());
      player.setPitch(this.getPitch());
      player.startRiding(this);
    }
  }

  @Override
  public void onDeath(DamageSource damageSource) {
    GameInstance.getCurrent().buildingTrackerTable.remove(this.baseCenter, null);
    super.onDeath(damageSource);
  }

  @Override
  protected Vector3f getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
    return new Vector3f(-1f, dimensions.height - 1, -1.2f);
  }

  @Override
  public LivingEntity getControllingPassenger() {
    Entity rider = this.getFirstPassenger();
    if (rider instanceof PlayerEntity) {
      PlayerEntity player = ((PlayerEntity) rider);

      // FIXME: Use a different item.
      if (player.isHolding(Items.ACACIA_BOAT)) {
        return player;
      }
    }
    return super.getControllingPassenger();
  }

}
