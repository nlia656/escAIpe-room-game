package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** This class is the controller for the lose scene. */
public class LosePageController {

  /**
   * This method is called to go back to the start screen.
   */
  @FXML
  private void onStartPage() {
    App.setUi(AppUi.START);
  }
}
