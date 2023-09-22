package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class CreditsController {

  @FXML
  void onBackToStartPage(ActionEvent event) {
    // Remove the scenes from the hash map to be reloaded.
    SceneManager.removeAppUi(AppUi.CHAT);
    SceneManager.removeAppUi(AppUi.ART_ROOM);
    SceneManager.removeAppUi(AppUi.DINO_ROOM);
    SceneManager.removeAppUi(AppUi.LOBBY_ROOM);
    SceneManager.removeAppUi(AppUi.BOOK_PUZZLE);
    SceneManager.removeAppUi(AppUi.SCROLL);
    App.setUi(AppUi.START);
  }
}
