package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ArtRoomController extends SceneController {
  @FXML private Label lblGameMaster;
  @FXML private Label lblTime;
  @FXML private ImageView scrollArt;
  @FXML private ImageView artToDino;
  @FXML private ImageView artToLobby;
  @FXML private Rectangle dagger;
  @FXML private Rectangle armour;
  @FXML private Rectangle pillar;
  @FXML private Rectangle crown;
  @FXML private Rectangle vase1;
  @FXML private Rectangle sword;
  @FXML private Rectangle vase2;
  @FXML private Rectangle bench1;
  @FXML private Rectangle bench2;
  @FXML private Rectangle painting1;
  @FXML private Rectangle painting2;
  @FXML private Rectangle painting3;
  @FXML private Rectangle painting4;
  @FXML private Rectangle painting5;
  @FXML private Rectangle books1;
  @FXML private Button btnHelp;
  @FXML private TitledPane artRoomPane;

  @FXML
  public void initialize() {
    timerTextSet(lblTime);
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
  private void daggerClicked() {

    // Add your code for handling the daggerClicked event here
    clickForRiddle("dagger");
  }

  @FXML
  private void armourClicked() {
    // Add your code for handling the armourClicked event here
    clickForRiddle("armour");
  }

  @FXML
  private void pillarClicked() {
    // Add your code for handling the pillarClicked event here
    clickForRiddle("pillar");
  }

  @FXML
  private void crownClicked() {
    // Add your code for handling the crownClicked event here
    clickForRiddle("crown");
  }

  @FXML
  private void vase1Clicked() {
    // Add your code for handling the vase1Clicked event here
    clickForRiddle("vase");
  }

  @FXML
  private void swordClicked() {
    // Add your code for handling the swordClicked event here
    clickForRiddle("sword");
  }

  @FXML
  private void vase2Clicked() {
    // Add your code for handling the vase2Clicked event here
    clickForRiddle("vase");
  }

  @FXML
  private void bench1Clicked() {
    // Add your code for handling the bench1Clicked event here
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
  }

  @FXML
  private void bench2Clicked() {
    // Add your code for handling the bench2Clicked event here
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
  }

  @FXML
  private void painting1Clicked() {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "painting1"
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) { // Check conditions
      GameState.isPuzzleResolved = true;
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  private void painting2Clicked() {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "painting2"
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) { // Check conditions
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  private void painting3Clicked() {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "painting3"
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) { // Check conditions
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  private void painting4Clicked() {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "painting4"
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) { // Check conditions
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  private void painting5Clicked() {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "painting5"
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) { // Check conditions
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
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
    if (answer == GameState.riddleAnswer && GameState.isRiddleResolved) { // If riddle solved, and correct item, get code.
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      staticRiddleCodeLabel.setText(GameState.riddleCode);
      System.out.println(GameState.riddleCode);
      GameState.firstTimeCode = true;
      GameState.artFound = true;
    }
  }

}
