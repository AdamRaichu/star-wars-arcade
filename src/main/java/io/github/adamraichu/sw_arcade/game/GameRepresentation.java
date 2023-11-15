package io.github.adamraichu.sw_arcade.game;

import java.util.EnumMap;
import java.util.HashMap;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;

public class GameRepresentation {
  private static NbtCompound example_world_nbt = new NbtCompound();
  /**
   * A map of a {@link Directions direction} to a BlockPos.
   * A BlockPos is used here because it is immutable, and only accepts integer
   * values.
   */
  private static EnumMap<Directions, BlockPos> dirToOffsetMap = new EnumMap<>(Directions.class);
  public static HashMap<String, GameRepresentation> nameToDataMap = new HashMap<>();

  static {
    // Setup dirToOffsetMap
    dirToOffsetMap.put(Directions.NORTH, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.NORTHEAST, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.EAST, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.SOUTHEAST, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.SOUTH, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.SOUTHWEST, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.WEST, new BlockPos(0, 0, 0));
    dirToOffsetMap.put(Directions.NORTHWEST, new BlockPos(0, 0, 0));
  }

  static {
    // For `example_world`
    NbtCompound goodGuyCenter = new NbtCompound();
    goodGuyCenter.put("pos", NbtHelper.fromBlockPos(new BlockPos(0, 0, 0)));
    NbtCompound badGuyCenter = new NbtCompound();
    badGuyCenter.put("pos", NbtHelper.fromBlockPos(new BlockPos(0, 0, 0)));

    NbtCompound commandCenters = new NbtCompound();
    commandCenters.put("good", goodGuyCenter);
    commandCenters.put("bad", badGuyCenter);

    example_world_nbt.put("command_centers", commandCenters);
  }

  public static final GameRepresentation example_world = new GameRepresentation(example_world_nbt);

  static {
    // Put representations in `nameToDataMap`.
    nameToDataMap.put("example_world", example_world);
  }

  public NbtCompound startGameDefaultNbt;

  public GameRepresentation(NbtCompound nbt) {
    this.startGameDefaultNbt = nbt;
  }

  public static enum Directions {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST
  }
}