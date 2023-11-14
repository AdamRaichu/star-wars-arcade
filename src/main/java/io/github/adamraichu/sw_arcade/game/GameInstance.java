package io.github.adamraichu.sw_arcade.game;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class GameInstance {
  private static GameInstance current;

  public HashMap<UUID, Team> playerTeamMap;

  public static GameInstance getCurrent() {
    return current;
  }

  public static void init(PlayerEntity good, PlayerEntity bad, NbtCompound nbt) {
    current = new GameInstance(good, bad, nbt);
  }

  public GameInstance(PlayerEntity good, PlayerEntity bad, NbtCompound nbt) {
  }
}