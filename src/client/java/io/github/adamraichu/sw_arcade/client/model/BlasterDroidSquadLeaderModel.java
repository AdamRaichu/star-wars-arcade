package io.github.adamraichu.sw_arcade.client.model;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.BlasterDroidSquadLeader;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BlasterDroidSquadLeaderModel extends DefaultedEntityGeoModel<BlasterDroidSquadLeader> {
  public BlasterDroidSquadLeaderModel() {
    super(new Identifier(StarWarsArcadeMode.MOD_ID, "b1_default"), false);
  }
}
