package io.github.adamraichu.sw_arcade.item;

import net.minecraft.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class AbstractGeoItem extends Item implements GeoItem {
  protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

  public AbstractGeoItem(Settings settings) {
    super(settings);
  }

  @Override
  public void registerControllers(ControllerRegistrar controllers) {
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.cache;
  }

}
