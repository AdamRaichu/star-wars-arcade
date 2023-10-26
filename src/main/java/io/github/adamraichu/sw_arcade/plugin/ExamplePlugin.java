package io.github.adamraichu.sw_arcade.plugin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import net.minecraft.item.ItemStack;

public class ExamplePlugin implements SWAMP {
  public static final Logger LOGGER = LoggerFactory.getLogger("sw_arcade._example_plugin");

  @Override
  public String getId() {
    return "_example_plugin";
  }

  @Override
  public void registerComponents() {
    StarWarsArcadeMode.LOGGER.info("If this was a real plugin, I would init the entities, items, etc. here.");
  }

  @Override
  public List<ItemStack> playerStarterItems$republic() {
    return List.of();
  }

  @Override
  public List<ItemStack> playerStarterItems$separatists() {
    return List.of();
  }

}
