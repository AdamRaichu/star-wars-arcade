package io.github.adamraichu.sw_arcade.client.render;

import io.github.adamraichu.sw_arcade.client.model.BlueCannonBoltModel;
import io.github.adamraichu.sw_arcade.entity.projectile.BlueCannonBoltEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class BlueCannonBoltRenderer extends GeoEntityRenderer<BlueCannonBoltEntity> {
  public BlueCannonBoltRenderer(Context renderManager) {
    super(renderManager, new BlueCannonBoltModel());
    addRenderLayer(new AutoGlowingGeoLayer<>(this));
  }
}
