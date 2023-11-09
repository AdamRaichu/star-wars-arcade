package io.github.adamraichu.sw_arcade.client.render;

import io.github.adamraichu.sw_arcade.client.model.Av7CannonModel;
import io.github.adamraichu.sw_arcade.entity.building.cannon.Av7Cannon;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Av7CannonRenderer extends GeoEntityRenderer<Av7Cannon> {
  public Av7CannonRenderer(Context renderManager) {
    super(renderManager, new Av7CannonModel());
  }

  @Override
  public void preRender(MatrixStack stack, Av7Cannon animatable, BakedGeoModel model,
      VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
      int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.preRender(stack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight,
        packedOverlay,
        red, green, blue, alpha);

    float scale = 1.75f;
    stack.scale(scale, scale, scale);
  }
}
