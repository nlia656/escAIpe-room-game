package nz.ac.auckland.se206;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
  public enum AppUi {
    ART_ROOM,
    CHAT,
    LEVEL,
    START,
    DINO_ROOM,
    LOBBY_ROOM,
    BOOK_PUZZLE,
    SCROLL,
    LOSE_SCREEN,
    WIN_SCREEN,
    LOCK,
    CREDITS,
    BENCH_PUZZLE
  }

  private static HashMap<AppUi, Parent> uiMap = new HashMap<>();

  public static void addAppUi(AppUi appUi, Parent ui) {
    uiMap.put(appUi, ui);
  }

  public static void removeAppUi(AppUi appUi) {
    uiMap.remove(appUi);
  }

  public static Parent getAppUi(AppUi appUi) {
    return uiMap.get(appUi);
  }

  public static void clearAppUi() {
    uiMap.clear();
  }
}
