package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * Controller class for the book puzzle scene. Handles events and interactions that occur in the
 * book puzzle scene.
 */
public class BookPuzzleController {

  @FXML private ImageView image;

  /**
   * Initializes the controller. Sets the image for the puzzle based on the puzzle answer in the
   * game state.
   */
  @FXML
  public void initialize() {
    image.setImage(new Image(String.format("/images/%sSnip.png", GameState.puzzleAnswer)));
  }

  /** Handles the click event for the "Close Book" button. Changes the scene to the dino room. */
  @FXML
  private void onCloseBook() {
    App.setUi(AppUi.DINO_ROOM);
  }
}
