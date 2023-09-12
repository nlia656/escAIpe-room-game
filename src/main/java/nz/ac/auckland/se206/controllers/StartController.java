package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;

public class StartController {
@FXML
  private Button startButton;
@FXML
  private Button creditButton;
@FXML
  private Button exitButton;
  @FXML
  private void start() {
    try {
      App.setRoot("level");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @FXML
  private void credit() {
  }
  @FXML
  private void exit() {
    System.exit(0);
  }
}
