package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class BookPuzzleController {

  public static int puzzleCode = 0;
  @FXML
  private ImageView image;
  private double randNumber;

  @FXML
  public void initialize() {
    // Generate the puzzle code to give
    while (puzzleCode < 10) {
      randNumber = Math.random();
      puzzleCode = (int) (randNumber * 100);
    }
    image.setImage(new Image(String.format("/images/%sSnip.png",
        GameState.puzzleAnswer))); // Set the random image for puzzle
  }

  @FXML
  private void onCloseBook() {
    App.setUi(AppUi.DINO_ROOM);
  }
}
