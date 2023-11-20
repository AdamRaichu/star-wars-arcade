package io.github.adamraichu.sw_arcade.game;

import static io.github.adamraichu.sw_arcade.block.BuildOptionBlock.build;

import java.util.List;
import java.util.Objects;

import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.building.cannon.Av7Cannon;
import io.github.adamraichu.sw_arcade.registry.EntityRegistry;
import io.github.adamraichu.sw_arcade.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public abstract class UIButtons {
  protected PlayerEntity player;
  protected BlockPos pos;

  public static UIButtons getButtons(PlayerEntity player, BlockPos pos) {
    if (Team.getPlayerTeam(player).equals(Team.Separatists.get())) {
      return new BadGuyButtons(player, pos);
    } else {
      return new GoodGuyButtons(player, pos);
    }
  }

  public abstract GooeyPage getGroundCannonsPage();

  public abstract GooeyPage getSmallAirSupportPage();

  public abstract GooeyPage getBarracksPage();

  public abstract GooeyPage getLargeAirSupportPage();

  public abstract GooeyPage getShieldGeneratorPage();

  public abstract GooeyPage getTorpedoGeneratorPage();

  public abstract GooeyPage getRayShieldPage();

  public abstract GooeyPage getExtrasPage();

  public static class GoodGuyButtons extends UIButtons {
    public GoodGuyButtons(PlayerEntity pl, BlockPos po) {
      this.player = pl;
      this.pos = po;
      StarWarsArcadeMode.LOGGER.info("Creating new GoodGuyButtons");
    }

    @Override
    public GooeyPage getGroundCannonsPage() {
      return GooeyPage.builder()
          .template(Objects.requireNonNull(
              ChestTemplate.builder(1).rowFromList(0, Objects.requireNonNull(List.of(
                  GooeyButton
                      .builder()
                      .display(new ItemStack(ItemRegistry.GROUND_CANNON_ICON))
                      .title(Text.translatable(EntityRegistry.AV7_CANNON.getTranslationKey()))
                      .onClick((action) -> {
                        build(new Av7Cannon(EntityRegistry.AV7_CANNON, player.getWorld()), player, pos, action);
                      })
                      .build())))
                  .build()))
          .build();
    }

    @Override
    public GooeyPage getSmallAirSupportPage() {
      return GooeyPage.builder()
          .template(Objects
              .requireNonNull(ChestTemplate.builder(1)
                  .rowFromList(0, Objects.requireNonNull(List.of(GooeyButton
                      .builder()
                      .display(new ItemStack(ItemRegistry.SMALL_AIR_SUPPORT_ICON))
                      .title("<placeholder> - nonfunctional")
                      .onClick((action) -> {
                        // FIXME: Nonfunctional
                        // build(new Av7Cannon(EntityRegistry.AV7_CANNON, player.getWorld()), player,
                        // pos, action);
                      })
                      .build())))
                  .build()))
          .build();
    }

    @Override
    public GooeyPage getBarracksPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getBarracksPage'");
    }

    @Override
    public GooeyPage getLargeAirSupportPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getLargeAirSupportPage'");
    }

    @Override
    public GooeyPage getShieldGeneratorPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getShieldGeneratorPage'");
    }

    @Override
    public GooeyPage getTorpedoGeneratorPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getTorpedoGeneratorPage'");
    }

    @Override
    public GooeyPage getRayShieldPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getRayShieldPage'");
    }

    @Override
    public GooeyPage getExtrasPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getExtrasPage'");
    }
  }

  public static class BadGuyButtons extends UIButtons {
    public BadGuyButtons(PlayerEntity pl, BlockPos po) {
      this.player = pl;
      this.pos = po;
      StarWarsArcadeMode.LOGGER.info("Creating new BadGuyButtons");
    }

    @Override
    public GooeyPage getGroundCannonsPage() {
      // FIXME: Wrong team.
      return GooeyPage.builder()
          .template(Objects.requireNonNull(
              ChestTemplate.builder(1).rowFromList(0, Objects.requireNonNull(List.of(
                  GooeyButton
                      .builder()
                      .display(new ItemStack(ItemRegistry.GROUND_CANNON_ICON))
                      .title(Text.translatable(EntityRegistry.AV7_CANNON.getTranslationKey()))
                      .onClick((action) -> {
                        build(new Av7Cannon(EntityRegistry.AV7_CANNON, player.getWorld()), player, pos, action);
                      })
                      .build())))
                  .build()))
          .build();
    }

    @Override
    public GooeyPage getSmallAirSupportPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getSmallAirSupportPage'");
    }

    @Override
    public GooeyPage getBarracksPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getBarracksPage'");
    }

    @Override
    public GooeyPage getLargeAirSupportPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getLargeAirSupportPage'");
    }

    @Override
    public GooeyPage getShieldGeneratorPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getShieldGeneratorPage'");
    }

    @Override
    public GooeyPage getTorpedoGeneratorPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getTorpedoGeneratorPage'");
    }

    @Override
    public GooeyPage getRayShieldPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getRayShieldPage'");
    }

    @Override
    public GooeyPage getExtrasPage() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getExtrasPage'");
    }
  }
}
