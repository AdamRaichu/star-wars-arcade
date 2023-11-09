package io.github.adamraichu.sw_arcade.game;

import java.util.Objects;

import io.github.adamraichu.sw_arcade.entity.helper.TeamAwareEntity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class Team {
  public final String name;
  public Team team;

  Team(String name) {
    this.name = name;
  }

  public static boolean hasTeam(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    return TeamAwareEntity.class.isAssignableFrom(obj.getClass());
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

  public static boolean areSameTeam(PlayerEntity player, TeamAwareEntity<?> entity) {
    Team playerTeam = GameInstance.getCurrent().playerTeamMap.get(player);
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
  }

  public static class Separatists extends Team {
    public static final Separatists team = new Separatists();

    Separatists() {
      super("Separatists");
    }

    public static Separatists get() {
      return team;
    }
  }
}
