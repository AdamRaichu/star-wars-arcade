package io.github.adamraichu.sw_arcade.client.render.item;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.item.Av7CannonItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class Av7CannonItemRenderer extends GeoItemRenderer<Av7CannonItem> {
  public Av7CannonItemRenderer() {
    super(new DefaultedItemGeoModel<>(new Identifier(StarWarsArcadeMode.MOD_ID, "av7_cannon")));
  }
}
