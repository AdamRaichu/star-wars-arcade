package io.github.adamraichu.sw_arcade.client.render;

import java.util.Objects;

import io.github.adamraichu.sw_arcade.client.model.BlueBlasterBoltModel;
import io.github.adamraichu.sw_arcade.entity.BlueBlasterBoltEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import software.bernie.geckolib.util.RenderUtils;

public class BlueBlasterBoltRenderer extends GeoEntityRenderer<BlueBlasterBoltEntity> {

  public BlueBlasterBoltRenderer(Context renderManager) {
    super(renderManager, new BlueBlasterBoltModel());
    addRenderLayer(new AutoGlowingGeoLayer<>(this));
  }

  @Override
  public void preRender(MatrixStack stack, BlueBlasterBoltEntity entity, BakedGeoModel model,
      VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
      int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    Entity animatable = entity.getOwner();

    if (!Objects.isNull(animatable)) {
      // Projectile was not created by an entity; e.g. it was created via /summon.
      RenderUtils.faceRotation(stack, entity.getOwner(), partialTick);
      stack.multiply(RotationAxis.NEGATIVE_Y
          .rotationDegrees(MathHelper.lerp(partialTick, animatable.prevYaw, animatable.getHeadYaw()) - 90));
      stack.multiply(RotationAxis.POSITIVE_Z
          .rotationDegrees(MathHelper.lerp(partialTick, animatable.prevPitch, animatable.getPitch())));
    }

    super.preRender(stack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight,
        packedOverlay,
        red, green, blue, alpha);
  }
}
