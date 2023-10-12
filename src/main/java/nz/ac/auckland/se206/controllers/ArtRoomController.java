package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the art room scene
 * It handles the events that occur in the art room
 * It extends the SceneController class
 * It contains methods that are called when the player clicks on an item
 */
public class ArtRoomController extends SceneController {

  /**
   * This method is called by the FXMLLoader when initialization is complete Starts the text sync
   * for the game timer and game master messages
   */
  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster);
  }

  /**
   * This method is called when the player clicks the "Go to Lobby" button Changes the scene to the
   * lobby Sets the GameState booleans to reflect the current scene
   */
  @FXML
  private void goLobby() {
    App.setUi(AppUi.LOBBY_ROOM);
    GameState.onArtRoom = false;
    GameState.onLobbyRoom = true;
  }

  /*
   *This method is called when the player clicks the "Go to Dino" button
   * Changes the scene to the dino room
   * Sets the GameState booleans to reflect the current scene
   *
   */
  @FXML
  private void goDino() {
    App.setUi(AppUi.DINO_ROOM);
    GameState.onArtRoom = false;
    GameState.onDinoRoom = true;
  }

  /**
   * This method is called when the player clicks the phone button Changes the scene to the chat
   * Sets the chat background to the art room
   * Sets the GameState booleans to reflect the current scene
   */
  @FXML
  private void onOpenPhone() {
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  /**
   * This method is called when the player clicks a riddle item
   * Post the name of the item to the clickForRiddle method to check if the answer is correct
   * @param event the mouse event
   */
  @FXML
  private void onRiddleItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    clickForRiddle(name);
  }

  /**
   * This method is called when the player clicks the bench
   * Changes the scene to the bench puzzle
   */
  @FXML
  private void benchClicked() {
    if (GameState.isPuzzleCodeGiven) {
      showNotifications("Clue Found!", "Go to the elevator and try to escape!");
    }
    // Add your code for handling the bench1Clicked event here
    App.setUi(AppUi.BENCH_PUZZLE);
    GameState.isBenchPuzzle = true;
  }

  /**
   * This method is called when the player clicks the books
   * If the riddle is not solved, it will check is this book is the correct answer
   * If the riddle is solved, the game master will tell the player to find the other book
   */
  @FXML
  private void books1Clicked() {
    // Add your code for handling the books1Clicked event here
    if (GameState.isRiddleResolved && GameState.artFound && !GameState.hasBookOpened) {
      showNotifications("Wrong book!", "Find the other book!");
    } else {
      clickForRiddle("book");
    }
  }

  /**
   * This method is called when the player clicks an item
   * If the riddle is not solved, it will tell the player to solve the riddle first
   * If the riddle is solved, it will check if the item is the correct answer
   * If the item is the correct answer, the game master will tell the player to go to the scroll
   * @param answer the name of the item
   */
  private void clickForRiddle(String answer) {
    // If riddle isn't solved, show alert
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isRiddleCodeGiven) {
      return;
    }
    if (answer.contains("vase")) {
      answer = "vase";
    }
    if (GameState.riddleAnswer.contains(answer)) { // If riddle solved, and correct item, get code.
      showNotifications("Code discovered!", "Click the scroll in the top left to view the code.");
      staticRiddleCodeLabel.setText(GameState.riddleCode);
      System.out.println(GameState.riddleCode);
      GameState.firstTimeCode = true;
      GameState.artFound = true;
    }
  }
}
