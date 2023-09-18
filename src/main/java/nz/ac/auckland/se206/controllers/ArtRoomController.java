package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ArtRoomController {
  @FXML Label lblGM;
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

  public void initialize() {}

  @FXML
  public void edaggerClicked(MouseEvent event) {
    System.out.println("dagger clicked");
  }

  @FXML
  public void armourClicked(MouseEvent event) {
    System.out.println("armour clicked");
  }

  @FXML
  public void pillarClicked(MouseEvent event) {
    System.out.println("pillar clicked");
  }

  @FXML
  public void crownClicked(MouseEvent event) {
    System.out.println("crown clicked");
  }

  @FXML
  public void vase1Clicked(MouseEvent event) {
    System.out.println("vase1 clicked");
  }

  @FXML
  public void swordClicked(MouseEvent event) {
    System.out.println("sword clicked");
  }

  @FXML
  public void vase2Clicked(MouseEvent event) {
    System.out.println("vase2 clicked");
  }

  @FXML
  public void bench1Clicked(MouseEvent event) {
    System.out.println("bench1 clicked");
  }

  @FXML
  public void bench2Clicked(MouseEvent event) {
    System.out.println("bench2 clicked");
  }

  @FXML
  public void painting1Clicked(MouseEvent event) {
    System.out.println("painting1 clicked");
  }

  @FXML
  public void painting2Clicked(MouseEvent event) {
    System.out.println("painting2 clicked");
  }

  @FXML
  public void painting3Clicked(MouseEvent event) {
    System.out.println("painting3 clicked");
  }

  @FXML
  public void painting4Clicked(MouseEvent event) {
    System.out.println("painting4 clicked");
  }

  @FXML
  public void painting5Clicked(MouseEvent event) {
    System.out.println("painting5 clicked");
  }

  @FXML
  public void books1Clicked(MouseEvent event) {
    System.out.println("books1 clicked");
  }

  @FXML
  private void onOpenGM() {
    App.setUi(AppUi.CHAT);
  }
}
