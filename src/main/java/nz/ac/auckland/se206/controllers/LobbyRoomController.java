package nz.ac.auckland.se206.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

// import javafx.scene.Cursor;


public class LobbyRoomController extends ScrollController {
  @FXML private Label lblGM;
@FXML private Label lblTime;

  @FXML private ImageView lobbyToArt;

  @FXML private Rectangle elevator;
  @FXML private Rectangle couch2;
  @FXML private Rectangle table;
  @FXML private Rectangle couch3;
  @FXML private Rectangle plant;

  @FXML private TitledPane lobbyRoomPane;

@FXML
    public void initialize() {
        Thread timerThread = new Thread(ArtRoomController.getTimer(lblTime));
        timerThread.setDaemon(true);
        timerThread.start();
    }

  private void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  private void onOpenGM() {
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
    App.setUi(AppUi.SCROLL);
  }

  @FXML
  private void elevatorClicked() {
    // Add your code for handling the elevatorClicked event here
  }

  @FXML
  private void couch2Clicked() {
    if (BookPuzzleController.puzzleAnswer == "couch2") {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
    }
  }

  @FXML
  private void tableClicked() {
    if (BookPuzzleController.puzzleAnswer == "table") {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
    }
  }

  @FXML
  private void couch3Clicked() {
    if (BookPuzzleController.puzzleAnswer == "couch3") {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
    }
  }

  @FXML
  private void plantClicked() {
    if (BookPuzzleController.puzzleAnswer == "plant") {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
    }
  }
}
