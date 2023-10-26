package io.github.adamraichu.sw_arcade.commands;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.config.JsonGameConfig;
import io.github.adamraichu.sw_arcade.config.JsonGameConfig.GameConfig;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ConfigurationCommands {
  private static GameConfig config = JsonGameConfig.getConfig();
  private static boolean hasTriedToReset = false;
  private static Timer timer = new Timer();
  public static String mayRequireRestart = "Note: Some configs may require a restart for changes to take effect. See the wiki for a full list.";

  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(
        literal("sw_arcade:config")
            .requires((src) -> src.hasPermissionLevel(config.commands.config.requiredPermissionLevel))
            .then(literal("reload")
                .executes(context -> reload(context)))
            .then(literal("reset").executes((cmd) -> reset(cmd))));
  }

  private static int reload(CommandContext<ServerCommandSource> ctx) {
    ServerCommandSource source = ctx.getSource();
    source.sendFeedback(TextSupplier.get("Reloading " + StarWarsArcadeMode.MOD_ID + " configs..."),
        config.commands.config.reload.shouldBroadcastFeedback);
    JsonGameConfig.setConfig(JsonGameConfig.reloadConfig());
    source.sendFeedback(TextSupplier.get("Success!"), config.commands.config.reload.shouldBroadcastFeedback);
    source.sendFeedback(
        TextSupplier.get(mayRequireRestart),
        config.commands.config.reload.shouldBroadcastFeedback);
    return 1;
  }

  private static int reset(CommandContext<ServerCommandSource> cmd) {
    ServerCommandSource source = cmd.getSource();
    if (hasTriedToReset) {
      timer.cancel();
      timer = new Timer();
      hasTriedToReset = false;
      try {
        JsonGameConfig.resetConfig$useWithCaution();
      } catch (Exception e) {
        source.sendError(Text.literal("Error writing to config file."));
        return 0;
      }
      source.sendFeedback(TextSupplier.get("Config has been reset. " + mayRequireRestart),
          config.commands.config.reset.shouldBroadcastFeedback);
      return 1;
    }
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        hasTriedToReset = false;
        source.sendFeedback(
            TextSupplier
                .get("Config reset was not confirmed within 30 seconds, and was therefore cancelled."),
            config.commands.config.reset.shouldBroadcastFeedback);
        timer.cancel();
        timer = new Timer();
      }
    }, 30000);
    hasTriedToReset = true;
    source.sendFeedback(TextSupplier.get(
        "Are you sure? This will reset all settings. Run again to confirm. This action will automatically cancel in 30 seconds."),
        config.commands.config.reset.shouldBroadcastFeedback);
    return 1;
  }

  static class TextSupplier implements Supplier<Text> {
    private Text message;

    TextSupplier(Text msg) {
      this.message = msg;
    }

    @Override
    public Text get() {
      return this.message;
    }

    public static TextSupplier get(String msg) {
      return new TextSupplier(Text.literal(msg));
    }

  }
}
