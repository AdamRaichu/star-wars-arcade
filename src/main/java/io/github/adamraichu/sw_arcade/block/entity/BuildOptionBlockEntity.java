package io.github.adamraichu.sw_arcade.block.entity;

import io.github.adamraichu.sw_arcade.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildOptionBlockEntity extends BlockEntity {

  public BuildOptionBlockEntity(BlockPos pos, BlockState state) {
    super(BlockRegistry.BlockEntityRegistry.BUILD_OPTION_BLOCK_ENTITY, pos, state);
  }

  @Override
  protected void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
  }

  public static void tick(World world, BlockPos pos, BlockState state, BuildOptionBlockEntity be) {

  }
}
