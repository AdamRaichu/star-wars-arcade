package io.github.adamraichu.sw_arcade.plugin;

import java.util.List;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.item.ItemStack;

public class PluginManager {
  public static void initPlugins() {
    getPluginEntrypoints().forEach(pluginMod -> {
      pluginMod.getEntrypoint().registerComponents();
    });
  }

  public static List<EntrypointContainer<SWAMP>> getPluginEntrypoints() {
    return FabricLoader.getInstance().getEntrypointContainers("sw_arcade", SWAMP.class);
  }

  public static List<ItemStack> getContributedStarterItems() {
    return null;
  }
}
