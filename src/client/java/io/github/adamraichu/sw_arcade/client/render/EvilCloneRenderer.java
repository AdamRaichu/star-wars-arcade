package io.github.adamraichu.sw_arcade.client.render;

import io.github.adamraichu.sw_arcade.client.model.EvilCloneModel;
import io.github.adamraichu.sw_arcade.entity.EvilCloneTest;
// import net.minecraft.client.render.VertexConsumer;
// import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
// import net.minecraft.client.util.math.MatrixStack;
// import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EvilCloneRenderer extends GeoEntityRenderer<EvilCloneTest> {
  public EvilCloneRenderer(EntityRendererFactory.Context context) {
    super(context, new EvilCloneModel());
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
