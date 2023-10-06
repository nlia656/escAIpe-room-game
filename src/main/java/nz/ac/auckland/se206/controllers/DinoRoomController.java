package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class DinoRoomController extends ScrollController {

  @FXML private ImageView dinoToArt;
  @FXML private Rectangle vase3;
  @FXML private Rectangle books2;
  @FXML private Rectangle mask;
  @FXML private Rectangle painting6;
  @FXML private Rectangle newspaper3;
  @FXML private Rectangle couch1;
  @FXML private Rectangle robe;
  @FXML private Rectangle newspaper2;
  @FXML private Rectangle newspaper1;
  @FXML private Rectangle dinosaur;
  @FXML private TitledPane dinoRoomPane;

  @FXML private Label lblTime;
  @FXML private Label lblGameMaster;

  @FXML
  public void initialize() {
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

  private void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onDinoRoom = false;
    GameState.onArtRoom = true;
  }

  @FXML
  private void scrollDinoClicked() {
    // Change scene to scroll and change alerts depending on game progress.
    App.setUi(AppUi.SCROLL);
    if (GameState.firstTimeCode) {
      showDialog("Info", "Code discovered!", "Now go find the book to continue.");
      GameState.firstTimeCode = false;
      GameState.isRiddleCodeGiven = true;
    } else if (GameState.secondTimeCode) {
      showDialog("Info", "Code discovered!", "You can try to escape through the elevator now.");
      GameState.secondTimeCode = false;
    }
  }


  @FXML
  private void onOpenGameMaster() {
    App.setUi(AppUi.CHAT);
  }

  @FXML
  public void books2Clicked(MouseEvent mouseEvent) {
    // Tell player to solve riddle first
    // If riddle code given, start book puzzle.
    if (GameState.isRiddleResolved && GameState.artFound) {
      App.setUi(AppUi.BOOK_PUZZLE);
      GameState.firstTimeCode = false;
      GameState.hasBookOpened = true;
    }
    checkClickItem("books2");
  }


  @FXML
  public void onClickItem(MouseEvent event){
    String name = ((Rectangle)event.getSource()).getId();
    checkClickItem(name);
  }
  private void checkClickItem(String name){
    if (!GameState.isRiddleResolved) {
      showDialog(
          "Info", "Solve the riddle!", "Click on the game master tab to get the riddle to solve!");
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
