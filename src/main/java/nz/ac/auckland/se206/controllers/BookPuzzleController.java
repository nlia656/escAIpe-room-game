package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class BookPuzzleController {

  @FXML
  private ImageView image;


  @FXML
  public void initialize() {
    image.setImage(new Image(String.format("/images/%sSnip.png",
        GameState.puzzleAnswer))); // Set the random image for puzzle
  }

  @FXML
  private void onCloseBook() {
    App.setUi(AppUi.DINO_ROOM);
  }
}
