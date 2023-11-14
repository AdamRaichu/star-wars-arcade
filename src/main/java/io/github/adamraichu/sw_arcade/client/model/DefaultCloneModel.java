package io.github.adamraichu.sw_arcade.client.model;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.BlasterCloneSquadLeader;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DefaultCloneModel extends DefaultedEntityGeoModel<BlasterCloneSquadLeader> {
  public DefaultCloneModel() {
    super(new Identifier(StarWarsArcadeMode.MOD_ID, "clone_default"), true);
  }
}
