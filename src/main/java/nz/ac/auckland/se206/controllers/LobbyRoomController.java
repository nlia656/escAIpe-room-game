package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LobbyRoomController {
  @FXML Label lblGM;
  @FXML private Rectangle elevator;
  @FXML private Rectangle couch2;
  @FXML private Rectangle table;
  @FXML private Rectangle couch3;
  @FXML private Rectangle plant;

  public void initialize() {}

  @FXML
  public void elevatorClicked(MouseEvent event) {
    System.out.println("elevator clicked");
  }

  @FXML
  public void couch2Clicked(MouseEvent event) {
    System.out.println("couch2 clicked");
  }

  @FXML
  public void tableClicked(MouseEvent event) {
    System.out.println("table clicked");
  }

  @FXML
  public void couch3Clicked(MouseEvent event) {
    System.out.println("couch3 clicked");
  }

  @FXML
  public void plantClicked(MouseEvent event) {
    System.out.println("plant clicked");
  }

  @FXML
  private void onOpenGM() {
    App.setUi(AppUi.CHAT);
  }
}
