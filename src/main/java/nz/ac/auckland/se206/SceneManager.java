package nz.ac.auckland.se206;

import java.util.HashMap;
import javafx.scene.Parent;

/**
 * This class is used to manage the scenes
 */
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

  private static final HashMap<AppUi, Parent> uiMap = new HashMap<>();

  /**
   * This method is used to add the app ui
   * @param appUi the name of room
   * @param ui the scene of the room
   */
  public static void addAppUi(AppUi appUi, Parent ui) {
    uiMap.put(appUi, ui);
  }

  /**
   * This method is used to remove the app ui
   * @param appUi the name of room
   */
  public static void removeAppUi(AppUi appUi) {
    uiMap.remove(appUi);
  }

  /**
   * This method is used to get the app ui
   * @param appUi the name of room
   * @return the scene of the room
   */
  public static Parent getAppUi(AppUi appUi) {
    return uiMap.get(appUi);
  }

  public static void clearAppUi() {
    uiMap.clear();
  }
}
