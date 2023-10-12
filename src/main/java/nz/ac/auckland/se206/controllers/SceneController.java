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

/**
 * This class is the mother class controller for the scene
 */
public class SceneController {

  @FXML public static Label staticPuzzleCodeLabel;
  @FXML public static Label staticRiddleCodeLabel;
  @FXML private Label puzzleCodeLabel;
  @FXML private Label riddleCodeLabel;
  @FXML protected Label lblTime;
  @FXML protected Label lblGameMaster;

  /**
   * This method is used to sync the time and game master text
   * @param lblTime the label for the time
   * @param lblGameMaster the label for the game master text
   */
  public static void startTextSync(Label lblTime, Label lblGameMaster) {
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                event -> {
                  lblTime.setText(GameState.timeLeft);
                  lblGameMaster.setText(GameState.lastMsg);
                }));
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
    timeline.play();
  }

  /**
   * This method is to get the code label
   */
  public void initialize() {
    staticPuzzleCodeLabel = puzzleCodeLabel;
    staticRiddleCodeLabel = riddleCodeLabel;
  }

  /**
   * This method is called when the player clicks to close the scroll
   */
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

  /**
   * This method is used to make a notification in game
   * @param title the title of the notification
   * @param message the message of the notification
   */
  protected void showNotifications(String title, String message) {
    Notifications notification = Notifications.create();
    notification.title(title);
    notification.text(message);
    notification.position(Pos.BOTTOM_RIGHT);
    notification.hideAfter(Duration.seconds(3));
    notification.owner(App.getStage());
    notification.show();
  }

  /**
   * This method is used to show riddle not solved notification
   */
  protected void showRiddleNotSolved() {
    showNotifications(
        "Solve the riddle!", "Click on the game master tab to get the riddle to solve!");
  }

  /**
   * This method is used to handle the click on the item
   * @param event the object that is clicked
   */
  @FXML
  public void onClickItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    checkClickItem(name);
  }

  /**
   * This method is used to check if the item is clicked
   * @param name the name of the item
   */
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
      staticPuzzleCodeLabel.setText(GameState.puzzleCode);
      showNotifications("Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }
}
