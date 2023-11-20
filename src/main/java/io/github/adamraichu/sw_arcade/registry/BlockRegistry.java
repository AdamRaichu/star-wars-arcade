package io.github.adamraichu.sw_arcade.registry;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.block.BuildOptionBlock;
import io.github.adamraichu.sw_arcade.block.entity.BuildOptionBlockEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockRegistry {
  public static final Block BUILD_OPTION_BLOCK = registerBlock("build_option_block",
      new BuildOptionBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
  public static final Block BASE_CENTER_BLOCK = registerBlock("base_center_block",
      new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK)));

  private static Block registerBlock(String name, Block block) {
    registerBlockItem(name, block);
    return Registry.register(Registries.BLOCK, new Identifier(StarWarsArcadeMode.MOD_ID, name), block);
  }

  private static Item registerBlockItem(String name, Block block) {
    return Registry.register(Registries.ITEM, new Identifier(StarWarsArcadeMode.MOD_ID, name),
        new BlockItem(block, new FabricItemSettings()));
  }

  public static class BlockEntityRegistry {
    public static final BlockEntityType<BuildOptionBlockEntity> BUILD_OPTION_BLOCK_ENTITY = BlockEntityRegistry
        .register("build_option_block_entity",
            FabricBlockEntityTypeBuilder.create(BuildOptionBlockEntity::new, BlockRegistry.BUILD_OPTION_BLOCK));

    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
        FabricBlockEntityTypeBuilder<T> builder) {
      return Registry.register(
          Registries.BLOCK_ENTITY_TYPE,
          new Identifier(StarWarsArcadeMode.MOD_ID, name),
          builder.build());
    }
  }
}
