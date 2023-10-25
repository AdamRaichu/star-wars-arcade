package io.github.adamraichu.sw_arcade.client.model;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.EvilCloneTest;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class EvilCloneModel extends DefaultedEntityGeoModel<EvilCloneTest> {
  public EvilCloneModel() {
    super(new Identifier(StarWarsArcadeMode.MOD_ID, "clone_default"), true);
  }
}
