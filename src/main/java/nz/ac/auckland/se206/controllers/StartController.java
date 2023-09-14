package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class StartController {

  @FXML
  private Button startButton;
  @FXML
  private Button creditButton;
  @FXML
  private Button exitButton;

  @FXML
  private void start() {
    App.setUi(AppUi.LEVEL);;
  }

  @FXML
  private void credit() {
  }

  @FXML
  private void exit() {
    System.exit(0);
  }
}
