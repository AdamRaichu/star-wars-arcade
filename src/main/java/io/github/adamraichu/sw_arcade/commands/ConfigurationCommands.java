package io.github.adamraichu.sw_arcade.commands;

import static net.minecraft.server.command.CommandManager.literal;

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

  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(
        literal("sw_arcade:config")
            .requires((src) -> src.hasPermissionLevel(config.commands.config.requiredPermissionLevel))
            .then(literal("reload").executes(context -> reload(context))));
  }

  private static int reload(CommandContext<ServerCommandSource> ctx) {
    ServerCommandSource source = ctx.getSource();
    source.sendFeedback(TextSupplier.get("Reloading " + StarWarsArcadeMode.MOD_ID + " configs..."),
        config.commands.config.reload.shouldBroadcastFeedback);
    JsonGameConfig.setConfig(JsonGameConfig.reloadConfig());
    source.sendFeedback(TextSupplier.get("Success!"), config.commands.config.reload.shouldBroadcastFeedback);
    source.sendFeedback(
        TextSupplier
            .get("Note: Some configs may require a restart for changes to take effect. See the wiki for a full list."),
        config.commands.config.reload.shouldBroadcastFeedback);
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
