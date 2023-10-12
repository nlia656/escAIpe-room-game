package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the lose scene
 */
public class LosePageController {

  /**
   * This method is called to go back to start screen
   * @throws IOException if the fxml file cannot be loaded
   */
  @FXML
  private void onStartPage() throws IOException {
    App.setUi(AppUi.START);
    App.unloadRoom();
    GameState.initial();
    App.loadRoom();
  }
}
