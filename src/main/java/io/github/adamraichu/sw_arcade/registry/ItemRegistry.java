package io.github.adamraichu.sw_arcade.registry;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.item.Av7CannonItem;
import io.github.adamraichu.sw_arcade.item.BlueBlasterBoltItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {
  public static final BlueBlasterBoltItem BLUE_BLASTER_BOLT_ITEM = registerItem("blue_blaster_bolt",
      new BlueBlasterBoltItem(new Item.Settings()));
  public static final Av7CannonItem AV7_CANNON_ITEM = registerItem("av7_cannon",
      new Av7CannonItem(new Item.Settings()));

  public static <I extends Item> I registerItem(String name, I item) {
    return Registry.register(Registries.ITEM, new Identifier(StarWarsArcadeMode.MOD_ID, name), item);
  }
}
