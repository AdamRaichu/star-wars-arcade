package io.github.adamraichu.sw_arcade.client.render;

import io.github.adamraichu.sw_arcade.client.model.BlasterDroidSquadLeaderModel;
import io.github.adamraichu.sw_arcade.entity.BlasterDroidSquadLeader;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
// import net.minecraft.client.render.VertexConsumer;
// import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
// import net.minecraft.client.util.math.MatrixStack;
// import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BlasterDroidSquadLeaderRenderer extends GeoEntityRenderer<BlasterDroidSquadLeader> {
  public BlasterDroidSquadLeaderRenderer(EntityRendererFactory.Context context) {
    super(context, new BlasterDroidSquadLeaderModel());
  }

  @Override
  public void preRender(MatrixStack stack, BlasterDroidSquadLeader animatable, BakedGeoModel model,
      VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
      int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.preRender(stack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight,
        packedOverlay,
        red, green, blue, alpha);

    float scale = 1.3f;
    stack.scale(scale, scale, scale);
  }
}
