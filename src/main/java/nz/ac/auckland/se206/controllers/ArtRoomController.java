package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ArtRoomController extends SceneController {


  @FXML
  public void initialize() {
    startTextSync(lblTime, lblGameMaster);
  }


  @FXML
  private void onOpenGameMaster() {
    App.setUi(AppUi.CHAT);
  }

  @FXML
  private void goLobby() {
    App.setUi(AppUi.LOBBY_ROOM);
    GameState.onArtRoom = false;
    GameState.onLobbyRoom = true;
  }

  @FXML
  private void goDino() {
    App.setUi(AppUi.DINO_ROOM);
    GameState.onArtRoom = false;
    GameState.onDinoRoom = true;
  }

  @FXML
  private void scrollArtClicked() {
    // Change scene to scroll to see code, change alerts based on progress.
    App.setUi(AppUi.SCROLL);
    if (GameState.firstTimeCode) {
      showDialog("Info", "Code discovered!", "Now go find the book to continue.");
      GameState.firstTimeCode = false;
      GameState.isRiddleCodeGiven = true;
    } else if (GameState.secondTimeCode) {
      showDialog("Info", "Code discovered!", "You can try to escape through the elevator now.");
      GameState.secondTimeCode = false;
      GameState.isPuzzleCodeGiven = true;
    }
  }

  @FXML
  private void onRiddleItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    clickForRiddle(name);
  }

  @FXML
  private void benchClicked() {
    // Add your code for handling the bench1Clicked event here
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
    }
  }


  @FXML
  private void books1Clicked() {
    // Add your code for handling the books1Clicked event here
    if (GameState.isRiddleResolved && GameState.artFound && !GameState.hasBookOpened) {
      showDialog("Info", "Wrong book!", "Find the other book!");
    } else {
      clickForRiddle("book");
    }
  }

  private void clickForRiddle(String answer) {
    // If riddle isnt solved, show alert
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isRiddleCodeGiven) {
      return;
    }
    if (answer.equals(GameState.riddleAnswer)) { // If riddle solved, and correct item, get code.
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      staticRiddleCodeLabel.setText(GameState.riddleCode);
      System.out.println(GameState.riddleCode);
      GameState.firstTimeCode = true;
      GameState.artFound = true;
    }
  }

}
