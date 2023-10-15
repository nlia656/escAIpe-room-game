package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the dino room scene It extends from the SceneController class
 */
public class DinoRoomController extends SceneController {
  @FXML private Label newMessage;

  /**
   * This method is called by the FXMLLoader when initialization is complete Starts the text sync
   * for the game timer and game master messages
   */
  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster, lblHints);
  }

  /**
   * This method is called when the player clicks the "Go to Lobby" button Changes the scene to the
   * lobby Sets the GameState booleans to reflect the current scene
   */
  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onDinoRoom = false;
    GameState.onArtRoom = true;
  }

  /**
   * This method is called when user clicks on the phone Changes the scene to the phone Also toggles
   * the chat background
   */
  @FXML
  private void onOpenPhone() {
    GameState.hasPhoneOpened = true;
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  /** This method is called when the player clicks the book */
  @FXML
  public void books2Clicked() {
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
