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

public class DinoRoomController {

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

  @FXML private Label lblGM;

  public void initialize() {}

  @FXML
  private void goArtRoom() {
    App.setUi(AppUi.ART_ROOM);
    GameState.onDinoRoom = false;
    GameState.onArtRoom = true;
  }

  public void books2(MouseEvent event) {
    System.out.println("books2 clicked");
    if (GameState.isRiddleResolved) {
      App.setUi(AppUi.BOOK_PUZZLE);
    }
  }

  @FXML
  public void mask(MouseEvent event) {
    System.out.println("mask clicked");
    if (BookPuzzleController.puzzleAnswer == "mask") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  public void poster1Clicked(MouseEvent event) {
    System.out.println("poster1 clicked");
    if (BookPuzzleController.puzzleAnswer == "poster1") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  public void poster2Clicked(MouseEvent event) {
    System.out.println("poster2 clicked");
    if (BookPuzzleController.puzzleAnswer == "poster2") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  public void poster3Clicked(MouseEvent event) {
    System.out.println("poster3 clicked");
    if (BookPuzzleController.puzzleAnswer == "poster3") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  public void couch1Clicked(MouseEvent event) {
    System.out.println("couch1 clicked");
    if (BookPuzzleController.puzzleAnswer == "couch1") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  public void robeClicked(MouseEvent event) {
    System.out.println("robe clicked");
    if (BookPuzzleController.puzzleAnswer == "robe") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  public void dinosaurClicked(MouseEvent event) {
    if (BookPuzzleController.puzzleAnswer == "dinosaur") {
      GameState.isPuzzleResolved = true;
    }
    System.out.println("dinosaur clicked");
  }

  @FXML
  public void vase3Clicked(MouseEvent event) {
    System.out.println("vase3 clicked");
    if (BookPuzzleController.puzzleAnswer == "vase") {
      GameState.isPuzzleResolved = true;
    }
  }

  @FXML
  private void onOpenGM() {
    App.setUi(AppUi.CHAT);
  }
}
