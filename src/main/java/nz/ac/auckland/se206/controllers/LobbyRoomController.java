package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** This class is the controller for the lobby room scene Extends SceneController */
public class LobbyRoomController extends SceneController {

  @FXML private Label newMessage;

  /** This method is called when the scene is loaded to sync the time and game master text */
  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster, lblHints, hintsLeft);
  }

  /**
   * This method is called when the player clicks the phone Changes the scene to the phone Also
   * toggles the chat background
   */
  @FXML
  private void onOpenPhone() {
    GameState.hasPhoneOpened = true;
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  /**
   * This method is called when the player clicks the art room arrow Changes the scene to the art
   * room Sets the GameState booleans to reflect the current scene
   */
  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onLobbyRoom = false;
    GameState.onArtRoom = true;
  }

  /**
   * This method is called when the player clicks the elevator if the riddle and puzzle is not
   * solved, it shows an alert else it changes the scene to the lock
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
