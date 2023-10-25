package io.github.adamraichu.sw_arcade.client.model;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.BlueBlasterBoltEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BlueBlasterBoltModel extends DefaultedEntityGeoModel<BlueBlasterBoltEntity> {
  public BlueBlasterBoltModel() {
    super(new Identifier(StarWarsArcadeMode.MOD_ID, "blue_blaster_bolt"), true);
  }
}
