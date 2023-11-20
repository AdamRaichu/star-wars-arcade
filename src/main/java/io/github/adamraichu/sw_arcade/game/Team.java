package io.github.adamraichu.sw_arcade.game;

import java.util.Objects;

import io.github.adamraichu.sw_arcade.entity.helper.TeamAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public abstract class Team {
  public final String name;
  public Team team;

  Team(String name) {
    this.name = name;
  }

  public abstract Text getVictoryMessage();

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Team ? this.name.equals(((Team) obj).name) : false;
  }

  public static boolean hasTeam(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    return TeamAwareEntity.class.isAssignableFrom(obj.getClass());
  }

  public static boolean hasTeam(PlayerEntity player) {
    return !Objects.isNull(GameInstance.getCurrent().playerTeamMap.get(player.getUuid()));
  }

  public static boolean areBothTeamed(Object obj1, Object obj2) {
    return hasTeam(obj1) && hasTeam(obj2);
  }

  public static boolean areSameTeam(Object obj1, Object obj2) {
    if (!areBothTeamed(obj1, obj2)) {
      return false;
    }
    return ((TeamAwareEntity<?>) obj1).getTeam().equals(((TeamAwareEntity<?>) obj2).getTeam());
  }

  /**
   * 
   * @param player
   * @return If there is a game in progress, the current team of the player. If no
   *         game is in progress,
   *         {@link Republic}.
   * @see {@link GameInstance#playerTeamMap}
   */
  public static Team getPlayerTeam(PlayerEntity player) {
    GameInstance current = GameInstance.getCurrent();
    return Objects.isNull(current) ? Team.Republic.get() : current.playerTeamMap.get(player.getUuid());
  }

  public static boolean areSameTeam(PlayerEntity player, TeamAwareEntity<?> entity) {
    Team playerTeam = getPlayerTeam(player);
    Team entityTeam = entity.getTeam();
    return Objects.isNull(playerTeam) ? false : playerTeam.equals(entityTeam);
  }

  public static class Republic extends Team {
    public static final Republic team = new Republic();

    Republic() {
      super("Republic");
    }

    public static Republic get() {
      return team;
    }

    @Override
    public Text getVictoryMessage() {
      return Text.literal("Republic wins!").formatted(Formatting.BLUE);
    }
  }

  public static class Separatists extends Team {
    public static final Separatists team = new Separatists();

    Separatists() {
      super("Separatists");
    }

    public static Separatists get() {
      return team;
    }

    @Override
    public Text getVictoryMessage() {
      return Text.literal("Separatists win!").formatted(Formatting.DARK_RED);
    }
  }
}
