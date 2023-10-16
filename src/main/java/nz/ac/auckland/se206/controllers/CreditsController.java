package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** Controller class for the credits scene. */
public class CreditsController {

  /**
   * This method is called when the player clicks the "Go to Lobby" button. It changes the scene to
   * the start screen.
   */
  @FXML
  public void onBackMainMenu() {
    // Remove the current scene from the hash map and navigate back to the start screen.
    App.setUi(AppUi.START);
  }
}
