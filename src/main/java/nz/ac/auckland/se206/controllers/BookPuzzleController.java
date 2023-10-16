package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the book puzzle scene It handles the events that occur in the
 * book puzzle scene
 */
public class BookPuzzleController {

  @FXML private ImageView image;

  /** This method is called by the FXMLLoader for initialization It sets the image for the puzzle */
  @FXML
  public void initialize() {
    image.setImage(
        new Image(
            String.format(
                "/images/%sSnip.png", GameState.puzzleAnswer))); // Set the random image for puzzle
  }

  /**
   * This method is called when the player clicks the "Go to Dino" button Changes the scene to the
   * dino room
   */
  @FXML
  private void onCloseBook() {
    App.setUi(AppUi.DINO_ROOM);
  }
}
