package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the bench scene
 * It handles the events that occur in the bench scene
 */
public class BenchController {

  @FXML private Rectangle fourBar;

  @FXML private Button goBack;

  @FXML private Rectangle oneBar;

  @FXML private Rectangle threeBar;

  @FXML private Rectangle twoBar;

  /**
   * This method is called by the FXMLLoader when initialization is complete
   */
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

  /**
   * This method is called when the player clicks the "Go to Lobby" button
   * @param event The event that triggered this method
   */
  @FXML
  void onCloseBench(ActionEvent event) {
    App.setUi(AppUi.ART_ROOM);
  }
}
