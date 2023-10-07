package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;
import org.controlsfx.control.Notifications;

public class SceneController {

  @FXML public static Label staticPuzzleCodeLabel;
  @FXML public static Label staticRiddleCodeLabel;
  @FXML private Label puzzleCodeLabel;
  @FXML private Label riddleCodeLabel;
  @FXML protected Label lblTime;
  @FXML protected Label lblGameMaster;

  public static void startTextSync(Label lblTime, Label lblGameMaster) {
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                event -> {
                  lblTime.setText(String.valueOf(GameState.timeLeft));
                  lblGameMaster.setText(GameState.lastMsg);
                }));
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
    timeline.play();
  }

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

  // protected void showDialog(String title, String headerText, String message) {
  //   Alert alert = new Alert(Alert.AlertType.INFORMATION);
  //   alert.setTitle(title);
  //   alert.setHeaderText(headerText);
  //   alert.setContentText(message);
  //   alert.showAndWait();
  // }

  protected void showNotifications(String title, String message) {
    Notifications notification = Notifications.create();
    notification.title(title);
    notification.text(message);
    notification.position(Pos.CENTER);
    notification.hideAfter(Duration.seconds(3));
    notification.show();
  }

  protected void showRiddleNotSolved() {
    showNotifications(
        "Solve the riddle!", "Click on the game master tab to get the riddle to solve!");
  }

  @FXML
  public void onClickItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    checkClickItem(name);
  }

  protected void checkClickItem(String name) {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    System.out.println(name + " clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer.equals(name)
        && GameState.isRiddleResolved
        && !GameState.isPuzzleCodeGiven
        && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showNotifications("Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }
}
