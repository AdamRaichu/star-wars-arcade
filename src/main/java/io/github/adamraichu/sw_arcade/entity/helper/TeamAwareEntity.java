package io.github.adamraichu.sw_arcade.entity.helper;

import io.github.adamraichu.sw_arcade.game.Team;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class TeamAwareEntity<T extends Team> extends PathAwareEntity implements GeoEntity {
  public final T team;
  public final Team enemyTeam;

  protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

  public TeamAwareEntity(T team, Team enemyTeam, EntityType<? extends PathAwareEntity> type, World world) {
    super(type, world);
    this.team = team;
    this.enemyTeam = enemyTeam;
  }

  public T getTeam() {
    return this.team;
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.cache;
  }
}
