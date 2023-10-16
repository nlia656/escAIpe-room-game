package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** This class is the controller for the start scene. */
public class StartController {

  @FXML private Button startButton;
  @FXML private Button creditsButton;
  @FXML private Button exitButton;

  /** This method is used to go to the level scene. */
  @FXML
  private void onStart() {
    App.setUi(AppUi.LEVEL);
  }

  /** This method is used to go to the credits scene. */
  @FXML
  private void onCredits() {
    App.setUi(AppUi.CREDITS);
  }

  /** This method is used to exit the game. */
  @FXML
  private void onExit() {
    System.exit(0);
  }
}
