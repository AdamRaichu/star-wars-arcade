package io.github.adamraichu.sw_arcade.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import io.github.adamraichu.sw_arcade.game.LocationData.Directions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;

public class GameInstance {
  private static GameInstance current;

  public HashMap<UUID, Team> playerTeamMap = new HashMap<>();
  public HashMap<BlockPos, Team> baseTeamMap = new HashMap<>();
  private List<BlockPos> baseCenters = new ArrayList<>();

  public GameInstance(PlayerEntity good, PlayerEntity bad, NbtCompound nbt) {
    this.playerTeamMap.put(good.getUuid(), Team.Republic.get());
    this.playerTeamMap.put(bad.getUuid(), Team.Separatists.get());

    nbt.getList("bases", NbtElement.COMPOUND_TYPE).forEach((_el) -> {
      NbtCompound el = ((NbtCompound) _el);
      BlockPos pos = NbtHelper.toBlockPos(el);
      baseCenters.add(pos);
    });
  }

  public static GameInstance getCurrent() {
    return current;
  }

  public static void init(PlayerEntity good, PlayerEntity bad, NbtCompound nbt) {
    current = new GameInstance(good, bad, nbt);
  }

  public BlockPos getCenterFor(BlockPos buildOptionBlock) {
    BlockPos match = new BlockPos(0, 0, 0);
    baseCenters.forEach((center) -> {
      for (Directions dir : Directions.values()) {
        // if the center, offset according to direction, equals the position of the
        // buildOptionBlock, this is the correct center
        if (center.add(LocationData.dirToOffsetMap.get(dir)).equals(buildOptionBlock)) {
          match.add(center);
        }
      }
    });

    if (match.equals(new BlockPos(0, 0, 0))) {
      throw new UntrackedBlockException("Provided buildOptionBlock does not have a center in GameInstance#baseCenters");
    }

    return match;
  }

  public int getBaseCount(Team checkTeam) {
    AtomicInteger count = new AtomicInteger(0);
    baseTeamMap.forEach((pos, baseTeam) -> {
      if (baseTeam.equals(checkTeam)) {
        count.incrementAndGet();
      }
    });

    return count.get();
  }
}
