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

/** This class is the mother class controller for the scene */
public class SceneController {

  @FXML public static Label staticPuzzleCodeLabel;
  @FXML public static Label staticRiddleCodeLabel;
  @FXML private Label puzzleCodeLabel;
  @FXML private Label riddleCodeLabel;
  @FXML protected Label lblTime;
  @FXML protected Label lblGameMaster;
  @FXML protected Label lblHints;

  /**
   * This method is used to sync the time and game master text
   *
   * @param lblTime the label for the time
   * @param lblGameMaster the label for the game master text
   */
  public static void startTextSync(Label lblTime, Label lblGameMaster, Label lblHints) {
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                event -> {
                  lblTime.setText(GameState.timeLeft);
                  lblGameMaster.setText(GameState.lastMsg);
                  lblHints.setText(Integer.toString(GameState.remainsHint));
                }));
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
    timeline.play();
  }

  /** This method is to get the code label */
  public void initialize() {
    staticPuzzleCodeLabel = puzzleCodeLabel;
    staticRiddleCodeLabel = riddleCodeLabel;
  }

  /** This method is called when the player clicks to close the scroll */
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
   *
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

  /** This method is called when the player clicks on the scroll */
  @FXML
  protected void scrollClicked() {
    // Change scene to scroll and change alerts depending on game progress.
    App.setUi(AppUi.SCROLL);
    if (GameState.firstTimeCode) {
      showNotifications("Code discovered!", "Now go find the book to continue.");
      GameState.firstTimeCode = false;
      GameState.isRiddleCodeGiven = true;
    } else if (GameState.secondTimeCode) {
      if (!GameState.isBenchPuzzle) {
        showNotifications("Code discovered!", "Now go take a seat and find the next clue.");
      } else {
        showNotifications("Code discovered!", "Now go to the elevator and try to escape!");
      }
      GameState.secondTimeCode = false;
      GameState.isPuzzleCodeGiven = true;
    }
  }

  /** This method is used to show riddle not solved notification */
  protected void showRiddleNotSolved() {
    if (GameState.hasPhoneOpened) {
      showNotifications("Solve the riddle!", "Open your phone to see your first clue to escape!");
    } else {
      showNotifications("Check your phone!", "You have a new message!");
    }
  }

  /**
   * This method is used to handle the click on the item
   *
   * @param event the object that is clicked
   */
  @FXML
  public void onClickItem(MouseEvent event) {
    String name = ((Rectangle) event.getSource()).getId();
    checkClickItem(name);
  }

  /**
   * This method is used to check if the item is clicked
   *
   * @param name the name of the item
   */
  protected void checkClickItem(String name) {
    if (!GameState.isRiddleResolved) {
      showRiddleNotSolved();
      return;
    }
    System.out.println(name + " clicked");
    if ((name.equals("couch3") || name.equals("couch2") || name.equals("couch1"))
        && GameState.isPuzzleCodeGiven
        && !GameState.isBenchPuzzle) {
      showNotifications("Try again.", "There's nothing here. Maybe another seat?");
      return;
    }
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
