package io.github.adamraichu.sw_arcade.item;

import java.util.function.Consumer;
import java.util.function.Supplier;

import io.github.adamraichu.sw_arcade.client.render.item.Av7CannonItemRenderer;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;

public class Av7CannonItem extends AbstractGeoItem {
  private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

  public Av7CannonItem(Settings settings) {
    super(settings);
  }

  @Override
  public void createRenderer(Consumer<Object> consumer) {
    consumer.accept(new RenderProvider() {
      private Av7CannonItemRenderer renderer;

      @Override
      public BuiltinModelItemRenderer getCustomRenderer() {
        if (this.renderer == null)
          this.renderer = new Av7CannonItemRenderer();

        return this.renderer;
      }
    });
  }

  @Override
  public Supplier<Object> getRenderProvider() {
    return this.renderProvider;
  }

}
