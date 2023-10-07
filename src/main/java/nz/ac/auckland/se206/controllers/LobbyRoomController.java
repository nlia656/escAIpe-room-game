package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LobbyRoomController extends SceneController {

  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster);
  }

  @FXML
  private void onOpenGameMaster() {
    App.setUi(AppUi.CHAT);
  }

  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onLobbyRoom = false;
    GameState.onArtRoom = true;
  }

  @FXML
  private void scrollLobbyClicked() {
    // Change scene to scroll and show alerts depending on game progress.
    App.setUi(AppUi.SCROLL);
    if (GameState.firstTimeCode) {
      showNotifications("Code discovered!", "Now go find the book to continue.");
      GameState.firstTimeCode = false;
    } else if (GameState.secondTimeCode) {
      showNotifications("Code discovered!", "You can try to escape through the elevator now.");
      GameState.secondTimeCode = false;
    }
  }

  @FXML
  private void elevatorClicked() {
    // Change scene to lock if both puzzle and riddle are solved.
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (!GameState.isPuzzleResolved) {
      showNotifications("Elevator locked!", "You need to solve the puzzle and riddle first.");
    } else {
      App.setUi(AppUi.LOCK);
    }
  }
}
