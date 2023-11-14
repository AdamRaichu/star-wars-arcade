package io.github.adamraichu.sw_arcade.client.render;

import io.github.adamraichu.sw_arcade.client.model.DefaultCloneModel;
import io.github.adamraichu.sw_arcade.entity.BlasterCloneSquadLeader;
// import net.minecraft.client.render.VertexConsumer;
// import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
// import net.minecraft.client.util.math.MatrixStack;
// import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DefaultCloneRenderer extends GeoEntityRenderer<BlasterCloneSquadLeader> {
  public DefaultCloneRenderer(EntityRendererFactory.Context context) {
    super(context, new DefaultCloneModel());
  }
  /*
   * @Override
   * public void renderFinal(MatrixStack poseStack, CloneSquadLeader animatable,
   * BakedGeoModel model,
   * VertexConsumerProvider bufferSource, VertexConsumer buffer, float
   * partialTick, int packedLight, int packedOverlay,
   * float red,
   * float green, float blue, float alpha) {
   * super.renderFinal(poseStack, animatable, model, bufferSource, buffer,
   * partialTick, packedLight, packedOverlay, red,
   * green, blue, alpha);
   * }
   */
}
