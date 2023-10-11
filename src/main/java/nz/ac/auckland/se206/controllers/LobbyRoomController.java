package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LobbyRoomController extends SceneController {

  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster1);
  }

  @FXML
  private void onOpenPhone() {
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onLobbyRoom = false;
    GameState.onArtRoom = true;
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
