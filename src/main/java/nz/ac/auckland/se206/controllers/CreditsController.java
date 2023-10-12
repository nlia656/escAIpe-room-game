package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the credits scene
 */
public class CreditsController {

  /**
   * This method is called when the player clicks the "Go to Lobby" button Changes the scene to the
   * start screen.
   *
   */
  @FXML
  public void onGoBack() {
    // Remove the scenes from the hash map to be reloaded.
    App.setUi(AppUi.START);
  }
}
