package nz.ac.auckland.se206;

import java.util.HashMap;
import javafx.scene.Parent;

/** This class is used to manage the scenes and their associated UI elements. */
public class SceneManager {
  /** Enumerations representing the various user interface (UI) scenes in the application. */
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

  /**
   * Adds an AppUi and its associated UI element to the SceneManager.
   *
   * @param appUi The name of the room.
   * @param ui The scene of the room.
   */
  public static void addAppUi(AppUi appUi, Parent ui) {
    uiMap.put(appUi, ui);
  }

  /**
   * Removes an AppUi from the SceneManager.
   *
   * @param appUi The name of the room to remove.
   */
  public static void removeAppUi(AppUi appUi) {
    uiMap.remove(appUi);
  }

  /**
   * Retrieves the UI element associated with an AppUi.
   *
   * @param appUi The name of the room.
   * @return The scene of the room.
   */
  public static Parent getAppUi(AppUi appUi) {
    return uiMap.get(appUi);
  }

  /** Clears all AppUi elements from the SceneManager. */
  public static void clearAppUi() {
    uiMap.clear();
  }
}
