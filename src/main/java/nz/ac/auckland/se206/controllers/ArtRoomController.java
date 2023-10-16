package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * Controller class for the art room scene. Handles events and interactions that occur in the art
 * room. Extends the SceneController class.
 */
public class ArtRoomController extends SceneController {

  /**
   * Initializes the controller. Starts the text synchronization for game timer, game master
   * messages, and hints.
   */
  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster, lblHints, hintsLeft);
  }

  /** Changes the scene to the lobby and updates GameState booleans. */
  @FXML
  private void goLobby() {
    App.setUi(AppUi.LOBBY_ROOM);
    GameState.onArtRoom = false;
    GameState.onLobbyRoom = true;
  }

  /** Changes the scene to the dino room and updates GameState booleans. */
  @FXML
  private void goDino() {
    App.setUi(AppUi.DINO_ROOM);
    GameState.onArtRoom = false;
    GameState.onDinoRoom = true;
  }

  /** Opens the phone chat, sets the chat background, and updates GameState booleans. */
  @FXML
  private void onOpenPhone() {
    GameState.hasPhoneOpened = true;
    ChatController chatController = App.getChatController();
    chatController.setChatBackground();
    App.setUi(AppUi.CHAT);
  }

  /**
   * Handles the click event for riddle items.
   *
   * @param event the mouse event
   */
  @FXML
  private void onRiddleItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    clickForRiddle(name);
  }

  /** Handles the click event for the bench and changes the scene to the bench puzzle. */
  @FXML
  private void benchClicked() {
    if (GameState.isPuzzleCodeGiven && !GameState.isBenchPuzzle) {
      showNotifications("Clue Found!", "Go to the elevator and try to escape!");
    }
    App.setUi(AppUi.BENCH_PUZZLE);
    GameState.isBenchPuzzle = true;
  }

  /** Handles the click event for the books and checks the riddle solution. */
  @FXML
  private void books1Clicked() {
    if (GameState.isRiddleResolved && GameState.artFound && !GameState.hasBookOpened) {
      showNotifications("Wrong book!", "Find the other book!");
    } else {
      clickForRiddle("book");
    }
  }

  /**
   * Checks the riddle solution for the clicked item.
   *
   * @param answer the name of the item
   */
  private void clickForRiddle(String answer) {
    // If riddle isn't solved, show not solved notification
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
    // If the answer is correct, show the code
    if (GameState.riddleAnswer.contains(answer)) {
      showNotifications("Code discovered!", "Click the scroll in the top left to view the code.");
      staticRiddleCodeLabel.setText(GameState.riddleCode);
      System.out.println(GameState.riddleCode);
      GameState.firstTimeCode = true;
      GameState.artFound = true;
    }
  }
}
