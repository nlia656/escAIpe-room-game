package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class WinPageController {

  @FXML
  private void goStartPage() {
    SceneManager.removeAppUi(AppUi.CHAT);
    SceneManager.removeAppUi(AppUi.ART_ROOM);
    SceneManager.removeAppUi(AppUi.DINO_ROOM);
    SceneManager.removeAppUi(AppUi.LOBBY_ROOM);
    SceneManager.removeAppUi(AppUi.BOOK_PUZZLE);
    SceneManager.removeAppUi(AppUi.SCROLL);
    App.setUi(AppUi.START);
  }
}
