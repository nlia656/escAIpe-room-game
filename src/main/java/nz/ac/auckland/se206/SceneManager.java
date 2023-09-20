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
    LOCK
  }

  private static HashMap<AppUi, Parent> uiMap = new HashMap<AppUi, Parent>();

  public static void addAppUi(AppUi appUi, Parent ui) {
    uiMap.put(appUi, ui);
  }

  public static void removeAppUi(AppUi appUi) {
    if (uiMap.containsKey(appUi)) {
        uiMap.remove(appUi);
    } else {
        return;
    }
}


  public static Parent getAppUi(AppUi appUi) {
    return uiMap.get(appUi);
  }
  public static void clearAppUi() {
    uiMap.clear();
  }
}
