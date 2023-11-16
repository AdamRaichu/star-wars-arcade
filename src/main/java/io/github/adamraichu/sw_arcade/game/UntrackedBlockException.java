package io.github.adamraichu.sw_arcade.game;

public class UntrackedBlockException extends RuntimeException {
  UntrackedBlockException() {
    super();
  }

  UntrackedBlockException(String msg) {
    super(msg);
  }
}
