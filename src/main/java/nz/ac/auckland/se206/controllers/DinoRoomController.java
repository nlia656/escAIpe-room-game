package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class DinoRoomController extends SceneController {

  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster);
  }

  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onDinoRoom = false;
    GameState.onArtRoom = true;
  }

  @FXML
  private void scrollDinoClicked() {
    // Change scene to scroll and change alerts depending on game progress.
    App.setUi(AppUi.SCROLL);
    if (GameState.firstTimeCode) {
      showDialog("Info", "Code discovered!", "Now go find the book to continue.");
      GameState.firstTimeCode = false;
      GameState.isRiddleCodeGiven = true;
    } else if (GameState.secondTimeCode) {
      showDialog("Info", "Code discovered!", "You can try to escape through the elevator now.");
      GameState.secondTimeCode = false;
    }
  }


  @FXML
  private void onOpenGameMaster() {
    App.setUi(AppUi.CHAT);
  }

  @FXML
  public void books2Clicked(MouseEvent mouseEvent) {
    // Tell player to solve riddle first
    // If riddle code given, start book puzzle.
    if (GameState.isRiddleResolved && GameState.artFound) {
      App.setUi(AppUi.BOOK_PUZZLE);
      GameState.firstTimeCode = false;
      GameState.hasBookOpened = true;
    }
    checkClickItem("books2");
  }

}
