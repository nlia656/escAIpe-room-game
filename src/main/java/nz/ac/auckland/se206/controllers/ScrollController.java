package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ScrollController {
  @FXML private Label puzzleCodeLabel;
  public static Label staticPuzzleCodeLabel;

  public void initialize() {
    staticPuzzleCodeLabel = puzzleCodeLabel;
  }

  @FXML
  private void closeScroll() {
    if (GameState.onArtRoom) {
      App.setUi(AppUi.ART_ROOM);
    } else if (GameState.onDinoRoom) {
      App.setUi(AppUi.DINO_ROOM);
    } else {
      App.setUi(AppUi.LOBBY_ROOM);
    }
  }

  // public void setRiddleCode(String code) {
  //   riddleCodeDisplay.setText(code);
  // }

  // public static void setPuzzleCode() {
  //   System.out.println(BookPuzzleController.puzzleCode);
  //   puzzleCodeDisplay.setText("hi");
  // }
  @FXML
  private void testShow() {
    puzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
  }
}
