package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

// import javafx.scene.Cursor;

public class LobbyRoomController extends SceneController {

  @FXML
  private Label lblGameMaster;
  @FXML
  private Label lblTime;

  @FXML
  private ImageView lobbyToArt;

  @FXML
  private Rectangle elevator;
  @FXML
  private Rectangle couch2;
  @FXML
  private Rectangle table;
  @FXML
  private Rectangle couch3;
  @FXML
  private Rectangle plant;

  @FXML
  private TitledPane lobbyRoomPane;

  @FXML
  public void initialize() {
    timerTextSet(lblTime);
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
      showDialog("Info", "Code discovered!", "Now go find the book to continue.");
      GameState.firstTimeCode = false;
    } else if (GameState.secondTimeCode) {
      showDialog("Info", "Code discovered!", "You can try to escape through the elevator now.");
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
    if (!GameState.isPuzzleResolved || !GameState.isRiddleResolved) {
      showDialog("Info", "Elevator locked!", "You need to solve the puzzle and riddle first.");
    } else if (GameState.isPuzzleResolved && GameState.isRiddleResolved) {
      App.setUi(AppUi.LOCK);
    }
  }

  @FXML
  public void onClickItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    checkClickItem(name);
  }

}
