package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ArtRoomController {
  @FXML private Label lblGM;

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

  @FXML private TitledPane artRoomPane;

  public void initialize() {}

  @FXML
  private void onOpenGM() {
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
  private void daggerClicked() {
    // Add your code for handling the daggerClicked event here
  }

  @FXML
  private void armourClicked() {
    // Add your code for handling the armourClicked event here
  }

  @FXML
  private void pillarClicked() {
    // Add your code for handling the pillarClicked event here
  }

  @FXML
  private void crownClicked() {
    // Add your code for handling the crownClicked event here
  }

  @FXML
  private void vase1Clicked() {
    // Add your code for handling the vase1Clicked event here
  }

  @FXML
  private void swordClicked() {
    // Add your code for handling the swordClicked event here
  }

  @FXML
  private void vase2Clicked() {
    // Add your code for handling the vase2Clicked event here
  }

  @FXML
  private void bench1Clicked() {
    // Add your code for handling the bench1Clicked event here
  }

  @FXML
  private void bench2Clicked() {
    // Add your code for handling the bench2Clicked event here
  }

  @FXML
  private void painting1Clicked() {
    // Add your code for handling the painting1Clicked event here
  }

  @FXML
  private void painting2Clicked() {
    // Add your code for handling the painting2Clicked event here
  }

  @FXML
  private void painting3Clicked() {
    // Add your code for handling the painting3Clicked event here
  }

  @FXML
  private void painting4Clicked() {
    // Add your code for handling the painting4Clicked event here
  }

  @FXML
  private void painting5Clicked() {
    // Add your code for handling the painting5Clicked event here
  }

  @FXML
  private void books1Clicked() {
    // Add your code for handling the books1Clicked event here
  }
}
