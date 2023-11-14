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

  public static <I extends Item> I registerItem(String name, I item) {
    return Registry.register(Registries.ITEM, new Identifier(StarWarsArcadeMode.MOD_ID, name), item);
  }
}
