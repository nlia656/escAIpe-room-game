package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class BenchController {

  @FXML private Rectangle fourBar;

  @FXML private Button goBack;

  @FXML private Rectangle oneBar;

  @FXML private Rectangle threeBar;

  @FXML private Rectangle twoBar;

  @FXML
  public void initialize() {
    oneBar.setVisible(false);
    twoBar.setVisible(false);
    threeBar.setVisible(false);
    fourBar.setVisible(false);
    if (GameState.buttonLevel == 3) {
      fourBar.setVisible(true);
    } else if (GameState.buttonLevel == 2) {
      threeBar.setVisible(true);
    } else if (GameState.buttonLevel == 1) {
      twoBar.setVisible(true);
    } else if (GameState.buttonLevel == 0) {
      oneBar.setVisible(true);
    }
  }

  @FXML
  void onCloseBench(ActionEvent event) {
    App.setUi(AppUi.ART_ROOM);
  }
}
