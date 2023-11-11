package io.github.adamraichu.sw_arcade.block;

import java.util.Objects;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.ButtonAction;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import io.github.adamraichu.sw_arcade.block.entity.BuildOptionBlockEntity;
import io.github.adamraichu.sw_arcade.entity.building.cannon.Av7Cannon;
import io.github.adamraichu.sw_arcade.entity.helper.TeamAwareEntity;
import io.github.adamraichu.sw_arcade.registry.BlockRegistry;
import io.github.adamraichu.sw_arcade.registry.EntityRegistry;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildOptionBlock extends BlockWithEntity {
  public BuildOptionBlock(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    if (!world.isClient()) {

      GooeyPage page = getBuildOptionsPage(player, pos);
      Objects.requireNonNull(player);
      Objects.requireNonNull(page);
      UIManager.openUIForcefully(((ServerPlayerEntity) player), page);

    }
    return ActionResult.SUCCESS;
  }

  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new BuildOptionBlockEntity(pos, state);
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
      BlockEntityType<T> type) {
    return validateTicker(type, BlockRegistry.BlockEntityRegistry.BUILD_OPTION_BLOCK_ENTITY,
        (w, pos, s, be) -> BuildOptionBlockEntity.tick(w, pos, s, be));
  }

  private GooeyPage getBuildOptionsPage(PlayerEntity player, BlockPos pos) {
    GooeyButton.Builder bb = GooeyButton.builder();

    ChestTemplate template = ChestTemplate.builder(6)
        .set(0, 0, bb
            .display(new ItemStack(Items.IRON_BARS))
            .onClick((action) -> {
              build(new Av7Cannon(EntityRegistry.AV7_CANNON, player.getWorld()), player, pos, action);
            })
            .build())
        .build();
    Objects.requireNonNull(template);
    GooeyPage page = GooeyPage.builder()
        .template(template)
        .title("Build")
        .build();

    Objects.requireNonNull(page);

    return page;
  }

  private void build(TeamAwareEntity<?> building, PlayerEntity player, BlockPos pos, ButtonAction action) {
    building.setPos(pos.getX(), pos.getY() + 1.25, pos.getZ());
    player.getWorld().spawnEntity(building);
    UIManager.closeUI((ServerPlayerEntity) player);
  }
}
