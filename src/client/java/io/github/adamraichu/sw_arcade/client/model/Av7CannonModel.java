package io.github.adamraichu.sw_arcade.client.model;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.building.cannon.Av7Cannon;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class Av7CannonModel extends DefaultedEntityGeoModel<Av7Cannon> {
  public Av7CannonModel() {
    super(new Identifier(StarWarsArcadeMode.MOD_ID, "av7_cannon"), false);
  }
}
