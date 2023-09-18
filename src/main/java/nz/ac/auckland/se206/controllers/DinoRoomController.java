package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class DinoRoomController {
  @FXML Label lblGM;
  @FXML Rectangle books2;
  @FXML Rectangle mask;
  @FXML Rectangle painting6;
  @FXML Rectangle poster1;
  @FXML Rectangle poster2;
  @FXML Rectangle poster3;
  @FXML Rectangle couch1;
  @FXML Rectangle robe;
  @FXML Rectangle dinosaur;
  @FXML Rectangle vase3;

  public void initialize() {}

  @FXML
  public void books2(MouseEvent event) {
    System.out.println("books2 clicked");
  }

  @FXML
  public void mask(MouseEvent event) {
    System.out.println("mask clicked");
  }

  @FXML
  public void poster1Clicked(MouseEvent event) {
    System.out.println("poster1 clicked");
  }

  @FXML
  public void poster2Clicked(MouseEvent event) {
    System.out.println("poster2 clicked");
  }

  @FXML
  public void poster3Clicked(MouseEvent event) {
    System.out.println("poster3 clicked");
  }

  @FXML
  public void couch1Clicked(MouseEvent event) {
    System.out.println("couch1 clicked");
  }

  @FXML
  public void robeClicked(MouseEvent event) {
    System.out.println("robe clicked");
  }

  @FXML
  public void dinosaurClicked(MouseEvent event) {
    System.out.println("dinosaur clicked");
  }

  @FXML
  public void vase3Clicked(MouseEvent event) {
    System.out.println("vase3 clicked");
  }

  @FXML
  private void onOpenGM() {
    App.setUi(AppUi.CHAT);
  }
}
