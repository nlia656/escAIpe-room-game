package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
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

  @FXML
  private void vase3Clicked() {
    // Add your code for handling the vase3Clicked event here
  }

  @FXML
  private void books2Clicked() {
    // Add your code for handling the books2Clicked event here
  }

  @FXML
  private void maskClicked() {
    // Add your code for handling the maskClicked event here
  }

  @FXML
  private void painting6Clicked() {
    // Add your code for handling the painting6Clicked event here
  }

  @FXML
  private void newspaper3Clicked() {
    // Add your code for handling the newspaper3Clicked event here
  }

  @FXML
  private void couch1Clicked() {
    // Add your code for handling the couch1Clicked event here
  }

  @FXML
  private void robeClicked() {
    // Add your code for handling the robeClicked event here
  }

  @FXML
  private void newspaper2Clicked() {
    // Add your code for handling the newspaper2Clicked event here
  }

  @FXML
  private void newspaper1Clicked() {
    // Add your code for handling the newspaper1Clicked event here
  }

  @FXML
  private void dinosaurClicked() {
    // Add your code for handling the dinosaurClicked event here
  }

  @FXML
  private void onOpenGM() {
    App.setUi(AppUi.CHAT);
  }
}
