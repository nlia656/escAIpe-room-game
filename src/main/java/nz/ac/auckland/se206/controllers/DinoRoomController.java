package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** Controller class for the dino room scene. Extends the SceneController class. */
public class DinoRoomController extends SceneController {

  /**
   * Called by the FXMLLoader when initialization is complete. Starts the text sync for the game
   * timer and game master messages.
   */
  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster, lblHints, hintsLeft);
  }

  /**
   * Handles the "Go to Lobby" button click event. Changes the scene to the lobby and updates
   * GameState booleans.
   */
  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onDinoRoom = false;
    GameState.onArtRoom = true;
  }

  /**
   * Handles the "Open Phone" button click event. Changes the scene to the phone and sets chat
   * background.
   */
  @FXML
  private void onOpenPhone() {
    GameState.hasPhoneOpened = true;
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  /**
   * Handles the click event for the second set of books. Checks if the riddle is solved and starts
   * the book puzzle if conditions are met.
   */
  @FXML
  public void books2Clicked() {
    // Check if riddle is resolved and art is found
    if (GameState.isRiddleResolved && GameState.artFound) {
      App.setUi(AppUi.BOOK_PUZZLE);
      GameState.firstTimeCode = false;
      GameState.hasBookOpened = true;
    }
    // Check the click item
    checkClickItem("books2");
  }
}
