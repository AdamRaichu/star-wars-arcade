package io.github.adamraichu.sw_arcade.client.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class WriteExampleWorldCommand {
  private static final String exampleWorldResourcePath = "example_world.zip";
  private static final String exampleWorldFolderName = UUID.randomUUID().toString();

  public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
    dispatcher.register(literal("sw_arcade:write_example_world").executes(ctx -> writeEmbeddedWorld(ctx)));
  }

  private static int writeEmbeddedWorld(CommandContext<FabricClientCommandSource> ctx) {
    MinecraftClient client = MinecraftClient.getInstance();
    Path savesDir = client.getLevelStorage().getSavesDirectory();
    File zipFile = savesDir.resolve(exampleWorldFolderName + ".zip").toFile();
    FabricClientCommandSource source = ctx.getSource();
    try {
      Resource exampleWorld = client
          .getResourceManager()
          .getResource(new Identifier(StarWarsArcadeMode.MOD_ID, exampleWorldResourcePath))
          .get();
      copyFileTo(exampleWorld.getInputStream(),
          zipFile.toString());
    } catch (Exception e) {
      StarWarsArcadeMode.LOGGER.error("Error writing embedded world zip file to saves folder:\n", e);
      source.sendError(
          Text.literal("Error writing embedded world zip file to saves folder. See output log for more details."));
      return 0;
    }

    source.sendFeedback(Text.literal("Successfully wrote zip file to saves folder."));

    try {
      unzipFile(zipFile, savesDir.resolve(exampleWorldFolderName).toFile());
    } catch (Exception e) {
      StarWarsArcadeMode.LOGGER.error("Error unzipping world zip file:\n", e);
      source.sendError(Text.literal("Error unzipping world zip file. See output log for more details."));
      return 0;
    }

    source.sendFeedback(Text.literal("Successfully unzipped world zip file."));

    try {
      zipFile.delete();
    } catch (Exception e) {
      StarWarsArcadeMode.LOGGER.error("Error deleting world zip file:\n", e);
      source.sendError(Text.literal("Error deleting world zip file. See output log for more details."));
    }

    source.sendFeedback(Text.literal("Successfully deleted zip file."));

    source.sendFeedback(Text.literal("World successfully written! Folder name: " + exampleWorldFolderName));

    return Command.SINGLE_SUCCESS;
  }

  private static void copyFileTo(InputStream source, String destination) throws IOException {
    StarWarsArcadeMode.LOGGER.info("Copying ->" + source + "\n\tto ->" + destination);
    Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
  }

  private static void unzipFile(File file, File outputDir) throws IOException {
    try (ZipFile zipFile = new ZipFile(file)) {
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        File entryDestination = new File(outputDir, entry.getName());
        if (entry.isDirectory()) {
          entryDestination.mkdirs();
        } else {
          entryDestination.getParentFile().mkdirs();
          try (InputStream in = zipFile.getInputStream(entry);
              OutputStream out = new FileOutputStream(entryDestination)) {
            IOUtils.copy(in, out);
          }
        }
      }
    }
  }
}
