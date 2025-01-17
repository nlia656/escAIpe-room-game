package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * Controller class for the bench scene. Handles events and interactions that occur in the bench
 * scene.
 */
public class BenchController {

  @FXML private Rectangle fourBar;
  @FXML private Rectangle oneBar;
  @FXML private Rectangle threeBar;
  @FXML private Rectangle twoBar;

  /**
   * Initializes the controller. Sets the visibility of bars based on the button level in the game
   * state.
   */
  @FXML
  public void initialize() {
    // Initialize the button level
    oneBar.setVisible(false);
    twoBar.setVisible(false);
    threeBar.setVisible(false);
    fourBar.setVisible(false);

    // Set the visibility of bars based on the button level
    switch (GameState.buttonLevel) {
      case 3:
        fourBar.setVisible(true);
        break;
      case 2:
        threeBar.setVisible(true);
        break;
      case 1:
        twoBar.setVisible(true);
        break;
      case 0:
        oneBar.setVisible(true);
        break;
    }
  }

  /** Handles the click event for the "Go to Lobby" button. */
  @FXML
  private void onBackStart() {
    App.setUi(AppUi.ART_ROOM);
  }
}
