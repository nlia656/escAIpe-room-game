package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class SceneController {
  @FXML public static Label staticPuzzleCodeLabel;
  @FXML public static Label staticRiddleCodeLabel;
  @FXML private Label puzzleCodeLabel;
  @FXML private Label riddleCodeLabel;

  public void initialize() {
    staticPuzzleCodeLabel = puzzleCodeLabel;
    staticRiddleCodeLabel = riddleCodeLabel;
  }

  @FXML
  private void onCloseScroll() {
    // Change the scene back to the previous on scene.
    if (GameState.onArtRoom) {
      App.setUi(AppUi.ART_ROOM);
    } else if (GameState.onDinoRoom) {
      App.setUi(AppUi.DINO_ROOM);
    } else {
      App.setUi(AppUi.LOBBY_ROOM);
    }
  }
  public static void timerTextSet(Label lblTime) {
    StringProperty time = new SimpleStringProperty();
    lblTime.textProperty().bind(time);
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(0.5),event -> {
          time.setValue(String.valueOf(GameState.timeLeft));
        })
    );
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
    timeline.play();
  }
  protected void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }
  protected void showRiddleNotSolved() {
    showDialog(
        "Info", "Solve the riddle!", "Click on the game master tab to get the riddle to solve!");
  }
  protected void checkClickItem(String name){
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    System.out.println(name+" clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer.equals(name)
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }
}
