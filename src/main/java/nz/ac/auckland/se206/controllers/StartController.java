package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class StartController {

  @FXML private Button startButton;
  @FXML private Button creditsButton;
  @FXML private Button exitButton;

  @FXML
  private void onStart() {
    App.setUi(AppUi.LEVEL);
  }

  @FXML
  private void onCredits() {
    App.setUi(AppUi.CREDITS);
  }

  @FXML
  private void onExit() {
    System.exit(0);
  }
}
