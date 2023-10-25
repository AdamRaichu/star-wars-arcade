package io.github.adamraichu.sw_arcade.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BlueBlasterBoltItem extends Item {
  public BlueBlasterBoltItem(Item.Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    if (!world.isClient) {
      user.getStackInHand(hand).decrement(1);
    }
    return super.use(world, user, hand);
  }
}
