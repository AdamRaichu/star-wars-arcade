package io.github.adamraichu.sw_arcade.client.model;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.projectile.BlueCannonBoltEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BlueCannonBoltModel extends DefaultedEntityGeoModel<BlueCannonBoltEntity> {
  public BlueCannonBoltModel() {
    super(new Identifier(StarWarsArcadeMode.MOD_ID, "blue_cannon_bolt"), false);
  }
}
