package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class BookPuzzleController {
  public static String puzzleAnswer;
  @FXML private ImageView couch1Snip;
  @FXML private ImageView dinosaurSnip;
  @FXML private ImageView maskSnip;
  @FXML private ImageView robeSnip;
  @FXML private ImageView poster3Snip;
  @FXML private ImageView poster2Snip;
  @FXML private ImageView poster1Snip;
  @FXML private ImageView vaseSnip;
  @FXML private ImageView painting1Snip;
  @FXML private ImageView painting2Snip;
  @FXML private ImageView painting3Snip;
  @FXML private ImageView painting4Snip;
  @FXML private ImageView painting5Snip;
  @FXML private ImageView painting6Snip;
  @FXML private ImageView couch2Snip;
  @FXML private ImageView couch3Snip;
  @FXML private ImageView plantSnip;
  @FXML private ImageView tableSnip;

  public void initialize() {
    String[] puzzleObjects = {
      "couch1",
      "dinosaur",
      "mask",
      "robe",
      "poster3",
      "poster2",
      "poster1",
      "vase",
      "painting1",
      "painting2",
      "painting3",
      "painting4",
      "painting5",
      "painting6",
      "couch2",
      "couch3",
      "plant",
      "table"
    };
    int randomNumber = (int) (Math.random() * puzzleObjects.length);
    puzzleAnswer = puzzleObjects[randomNumber];
    switch ((String) puzzleAnswer) {
      case "couch1":
        couch1Snip.setVisible(true);
        break;
      case "dinosaur":
        dinosaurSnip.setVisible(true);
        break;
      case "mask":
        maskSnip.setVisible(true);
        break;
      case "robe":
        robeSnip.setVisible(true);
        break;
      case "poster3":
        poster3Snip.setVisible(true);
        break;
      case "poster2":
        poster2Snip.setVisible(true);
        break;
      case "poster1":
        poster1Snip.setVisible(true);
        break;
      case "vase":
        vaseSnip.setVisible(true);
        break;
      case "painting1":
        painting1Snip.setVisible(true);
        break;
      case "painting2":
        painting2Snip.setVisible(true);
        break;
      case "painting3":
        painting3Snip.setVisible(true);
        break;
      case "painting4":
        painting4Snip.setVisible(true);
        break;
      case "painting5":
        painting5Snip.setVisible(true);
        break;
      case "painting6":
        painting6Snip.setVisible(true);
        break;
      case "couch2":
        couch2Snip.setVisible(true);
        break;
      case "couch3":
        couch3Snip.setVisible(true);
        break;
      case "plant":
        plantSnip.setVisible(true);
        break;
      case "table":
        tableSnip.setVisible(true);
        break;
    }
  }

  @FXML
  private void closeBook() {
    App.setUi(AppUi.DINO_ROOM);
  }
}
