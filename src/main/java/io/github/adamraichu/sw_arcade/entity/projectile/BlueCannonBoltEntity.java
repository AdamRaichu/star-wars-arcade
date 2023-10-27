package io.github.adamraichu.sw_arcade.entity.projectile;

import io.github.adamraichu.sw_arcade.config.JsonGameConfig;
import io.github.adamraichu.sw_arcade.game.Team;
import io.github.adamraichu.sw_arcade.registry.EntityRegistry;
import io.github.adamraichu.sw_arcade.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BlueCannonBoltEntity extends ThrownItemEntity implements GeoEntity {
  protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
  private JsonGameConfig.GameConfig config;

  public BlueCannonBoltEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
    super(entityType, world);
    this.config = JsonGameConfig.getConfig();
  }

  public BlueCannonBoltEntity(World world, LivingEntity owner) {
    super((EntityType<? extends ThrownItemEntity>) EntityRegistry.BLUE_BLASTER_BOLT, owner, world);
    this.config = JsonGameConfig.getConfig();
  }

  public BlueCannonBoltEntity(EntityType<? extends ThrownItemEntity> entityType, double d, double e, double f,
      World world) {
    super(entityType, d, e, f, world);
    this.config = JsonGameConfig.getConfig();
  }

  @Override
  public void registerControllers(ControllerRegistrar controllers) {
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.cache;
  }

  @Override
  protected Item getDefaultItem() {
    return ItemRegistry.BLUE_BLASTER_BOLT_ITEM;
  }

  @Override
  protected void onEntityHit(EntityHitResult entityHitResult) {
    super.onEntityHit(entityHitResult);
    Entity entity = entityHitResult.getEntity();
    boolean areSameTeam = Team.areSameTeam(entity, this.getOwner());
    if (!areSameTeam || !config.ignoreFriendlyFire) {
      entity.damage(this.getDamageSources().thrown(this, this.getOwner()), config.cannonDamage);
      this.discard();
    }
  }

  @Override
  protected void onBlockHit(BlockHitResult blockHitResult) {
    super.onBlockHit(blockHitResult);
    this.discard();
  }
}
