package io.github.adamraichu.sw_arcade.commands;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.Collections;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import io.github.adamraichu.sw_arcade.config.JsonGameConfig;
import io.github.adamraichu.sw_arcade.config.JsonGameConfig.GameConfig;
import io.github.adamraichu.sw_arcade.game.GameInstance;
import io.github.adamraichu.sw_arcade.game.LocationData;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class StartGameCommand {
  private static GameConfig config = JsonGameConfig.getConfig();

  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("sw_arcade:start_game")
        .requires(src -> src.hasPermissionLevel(config.commands.startGame.requiredPermissionLevel))
        .then(literal("default")
            .then(literal("example_world")
                .executes(ctx -> startDefaultGame(ctx, "example_world")))));
  }

  public static int startDefaultGame(CommandContext<ServerCommandSource> ctx, String worldName) {
    ServerCommandSource src = ctx.getSource();
    List<ServerPlayerEntity> players = src.getWorld().getPlayers();

    if (((Integer.valueOf(players.size()).equals(Integer.valueOf(2))))
        || config.commands.startGame._default.allowExtraPlayers) {

      // I'm not sure how World.getPlayers() list is organized. Just in case there's
      // any sorting at all (e.g. alphabetically), shuffle the list.
      Collections.shuffle(players);
      GameInstance.init(players.get(0),
          players.get(0), // FIXME: testing (should be 1 not 0)
          LocationData.nameToDataMap.get(worldName).startGameDefaultNbt,
          src.getWorld());
      return 1;
    }

    src.sendError(Text.literal(
        "Exactly two players are required for the \"default\" subcommand. You can change this in the config."));

    return 0;
  }
}
