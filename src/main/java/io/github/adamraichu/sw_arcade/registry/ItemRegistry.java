package io.github.adamraichu.sw_arcade.registry;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.item.BlueBlasterBoltItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {
  public static final BlueBlasterBoltItem BLUE_BLASTER_BOLT_ITEM = registerItem("blue_blaster_bolt",
      new BlueBlasterBoltItem(new Item.Settings()));
  public static final Item GROUND_CANNON_ICON = registerItem("ground_cannon_icon", new Item(new Item.Settings()));
  public static final Item SMALL_AIR_SUPPORT_ICON = registerItem("small_air_support_icon",
      new Item(new Item.Settings()));
  public static final Item BARRACKS_ICON = registerItem("barracks_icon",
      new Item(new Item.Settings()));
  public static final Item LARGE_AIR_SUPPORT_ICON = registerItem("large_air_support_icon",
      new Item(new Item.Settings()));
  public static final Item SHIELD_GENERATOR_ICON = registerItem("shield_generator_icon",
      new Item(new Item.Settings()));
  public static final Item TORPEDO_GENERATOR_ICON = registerItem("torpedo_generator_icon",
      new Item(new Item.Settings()));
  public static final Item RAY_SHIELD_ICON = registerItem("ray_shield_icon",
      new Item(new Item.Settings()));
  public static final Item EXTRAS_ICON = registerItem("extras_icon",
      new Item(new Item.Settings()));

  public static <I extends Item> I registerItem(String name, I item) {
    return Registry.register(Registries.ITEM, new Identifier(StarWarsArcadeMode.MOD_ID, name), item);
  }
}
