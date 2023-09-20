package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ScrollController {
  @FXML private Text riddleCode;
  @FXML private static Text puzzleCodeDisplay;

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

  public void setRiddleCode(String code) {
    riddleCode.setText(code);
  }

  public static void setPuzzleCode() {
    System.out.println(BookPuzzleController.puzzleCode);
    puzzleCodeDisplay.setText("hi");
  }
}
