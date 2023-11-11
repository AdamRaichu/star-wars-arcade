package io.github.adamraichu.sw_arcade.plugin;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * SWAMP stands for Star Wars: Arcade Mode (Plugin)
 * 
 * Declare this in your {@code fabric.mod.json} under the {@code entrypoints}
 * key.
 * 
 * <pre>
 * {
 *   [...]
 *   "entrypoints": {
 *     [...]
 *     "sw_arcade": [
 *       "full.name.of.your.Plugin"
 *     ]
 *   }
 * }
 * </pre>
 */
public interface SWAMP {
  /**
   * @return The version of the plugin api that is implemented. Current version is
   *         {@link io.github.adamraichu.sw_arcade.plugin.PluginVersion V1}.
   */
  public PluginVersion API_VERSION();

  /**
   * @return A unique string used to identify the plugin to the main mod.
   *         You can create an issue on GitHub to get added to a list of available
   *         plugins.
   */
  public String getId();

  /**
   * This method will be called in
   * {@link io.github.adamraichu.sw_arcade.StarWarsArcadeMode#onInitialize()
   * StarWarsArcadeMode#onInitialize()}. Use to register entities, items,
   * commands, etc.
   * 
   * @return
   */
  public void registerComponents();

  /**
   * 
   * @return A list of ItemStacks to give to the player on the
   *         {@link io.github.adamraichu.sw_arcade.game.Team.Republic Republic}
   *         team.
   */
  public List<ItemStack> playerStarterItems$republic();

  /**
   * 
   * @return A list of ItemStacks to give to the player on the
   *         {@link io.github.adamraichu.sw_arcade.game.Team.Separatists
   *         Separatists}
   *         team.
   */
  public List<ItemStack> playerStarterItems$separatists();
}
