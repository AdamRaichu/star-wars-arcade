package io.github.adamraichu.sw_arcade.config;

import java.io.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import net.fabricmc.loader.api.FabricLoader;

public class JsonGameConfig {
  public static final int VERSION = 4;
  public static File configFile = FabricLoader.getInstance().getConfigDir()
      .resolve(StarWarsArcadeMode.MOD_ID + ".json")
      .toFile();
  private static ObjectMapper mapper = new ObjectMapper();
  private static GameConfig currentConfig = reloadConfig();

  public static GameConfig getConfig() {
    return currentConfig;
  }

  public static GameConfig reloadConfig() {
    GameConfig _new = new GameConfig();
    if (!configFile.exists()) {
      // If the config file does not exist, write default config.
      StarWarsArcadeMode.LOGGER.info("Configuration file does not exist; writing default values.");
      try {
        writeConfigFile(_new);
      } catch (Exception e) {
        StarWarsArcadeMode.LOGGER.error("Error while writing config file:\n", e);
      }
      return _new;
    }

    try {
      // If it does exist, try to read it.
      GameConfig old = mapper.readValue(configFile, GameConfig.class);

      if (old.$VERSION < VERSION) {
        // Config file is for an out of date version.
        StarWarsArcadeMode.LOGGER.info("Updating " + StarWarsArcadeMode.MOD_ID + ".json because values were missing.");
        _new = updateOutdatedConfig(old);
        writeConfigFile(_new);
        return _new;
      } else {
        return old;
      }
    } catch (Exception e) {
      StarWarsArcadeMode.LOGGER.error("Error while reading/writing config file:\n", e);
      return new GameConfig();
    }
  }

  public static void setConfig(GameConfig config) {
    currentConfig = config;
  }

  public static void resetConfig$useWithCaution() throws Exception {
    StarWarsArcadeMode.LOGGER.info("Resetting config file.");
    setConfig(new GameConfig());
    writeConfigFile(getConfig());
    reloadConfig();
  }

  public static void writeConfigFile(GameConfig config) throws Exception {
    mapper.writeValue(configFile, config);
  }

  private static GameConfig updateOutdatedConfig(GameConfig config) {
    int cv = config.$VERSION;
    if (!(cv < VERSION)) {
      return config;
    }
    if (cv <= 1) {
      config.commands.config.requiredPermissionLevel = 2;
    }
    if (cv <= 2) {
      config.commands.config.reload.shouldBroadcastFeedback = true;
    }
    if (cv <= 3) {
      config.commands.config.reset.shouldBroadcastFeedback = true;
    }
    config.$VERSION = VERSION;
    return config;
  }

  public static class GameConfig {
    public int $VERSION = JsonGameConfig.VERSION;
    public int blasterDamage = 1;
    public boolean ignoreFriendlyFire = true;
    public Commands commands = new Commands();

    public static class Commands {
      public Config config = new Config();

      public static class Config {
        public int requiredPermissionLevel = 2;
        public Reload reload = new Reload();
        public Reset reset = new Reset();

        public static class Reload {
          public boolean shouldBroadcastFeedback = true;
        }

        public static class Reset {
          public boolean shouldBroadcastFeedback = true;
        }
      }
    }
  }

  public static String getAsString() {
    try {
      return mapper.writeValueAsString(getConfig());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "JsonGameConfig{ErrorReadingConfig=true}";
    }
  }
}
