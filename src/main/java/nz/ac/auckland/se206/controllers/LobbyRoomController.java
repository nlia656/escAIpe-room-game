package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** Controller class for the lobby room scene. Extends SceneController class. */
public class LobbyRoomController extends SceneController {

  /**
   * Initializes the lobby room scene. Called when the scene is loaded to sync the time and game
   * master text.
   */
  @FXML
  @Override
  public void initialize() {
    startTextSync(lblTime, lblGameMaster, lblHints, hintsLeft);
  }

  /**
   * Handles the event when the player clicks the phone. Changes the scene to the phone and toggles
   * the chat background.
   */
  @FXML
  private void onOpenPhone() {
    GameState.hasPhoneOpened = true;
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  /**
   * Handles the event when the player clicks the art room arrow. Changes the scene to the art room
   * and sets the GameState booleans to reflect the current scene.
   */
  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onLobbyRoom = false;
    GameState.onArtRoom = true;
  }

  /**
   * Handles the event when the player clicks the elevator. If the riddle and puzzle are not solved,
   * shows an alert. Otherwise, changes the scene to the lock.
   */
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
