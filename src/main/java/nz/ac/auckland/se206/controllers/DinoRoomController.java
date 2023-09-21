package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
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

  @FXML
  private Label lblTime;

@FXML
  public void initialize() {
    Thread timerThread = new Thread(ArtRoomController.getTimer(lblTime,lblGM));
    timerThread.setDaemon(true);
    timerThread.start();
  }
  @FXML private Label lblGM;

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
    App.setUi(AppUi.SCROLL);
    if (GameState.firstTimeCode) {
      showDialog(
          "Info",
          "Code discovered!",
          "Now go find the book to continue.");
      GameState.firstTimeCode = false;
    } else if (GameState.secondTimeCode) {
      showDialog(
          "Info",
          "Code discovered!",
          "You can try to escape through the elevator now.");
      GameState.secondTimeCode = false;
    }
  }

  @FXML
  public void poster1Clicked(MouseEvent event) {
    System.out.println("poster1 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "poster1" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  public void poster2Clicked(MouseEvent event) {
    System.out.println("poster2 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "poster2" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  public void poster3Clicked(MouseEvent event) {
    System.out.println("poster3 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "poster3" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  public void couch1Clicked(MouseEvent event) {
    System.out.println("couch1 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "couch1" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  public void robeClicked(MouseEvent event) {
    System.out.println("robe clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "robe" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  public void dinosaurClicked(MouseEvent event) {
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "dinosaur" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
    System.out.println("dinosaur clicked");
  }

  @FXML
  public void vase3Clicked(MouseEvent event) {
    System.out.println("vase3 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "vase" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  private void onOpenGM() {
    App.setUi(AppUi.CHAT);
  }

  @FXML
  public void books2Clicked(MouseEvent mouseEvent) {
    System.out.println("books2 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.isRiddleResolved) {
      App.setUi(AppUi.BOOK_PUZZLE);
      GameState.firstTimeCode = false;
      GameState.hasBookOpened = true;
    }
  }

  @FXML
  public void maskClicked(MouseEvent mouseEvent) {
    System.out.println("mask clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "mask" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }

  @FXML
  public void painting6Clicked(MouseEvent mouseEvent) {
    System.out.println("painting6 clicked");
    if (GameState.isPuzzleResolved) {
      return;
    }
    if (GameState.puzzleAnswer == "painting6" && GameState.isRiddleResolved && !GameState.isPuzzleCodeGiven && GameState.hasBookOpened) {
      GameState.isPuzzleResolved = true;
      staticPuzzleCodeLabel.setText(Integer.toString(BookPuzzleController.puzzleCode));
      showDialog("Info", "Code discovered!", "Click the scroll in the top left to view the code.");
      GameState.secondTimeCode = true;
    }
  }
}
