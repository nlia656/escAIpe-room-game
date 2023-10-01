package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LevelController {

  @FXML private Button startButton;
  @FXML private Slider levelSlider;

  @FXML private Slider timeSlider;

  @FXML private Text levelTexts;
  @FXML private CheckBox ttsButton;

  @FXML
  private void finishSlide() {
    double level = levelSlider.getValue();
    levelSlider.setValue(Math.round(level));
    setLevelText();
  }

  @FXML
  private void onBack() {
    App.setUi(AppUi.START);
  }

  @FXML
  private void finishTime() {
    double time = timeSlider.getValue();
    timeSlider.setValue(Math.round(time));
  }

  @FXML
  private void onStart() throws IOException {
    GameState.initial();
    switch ((int) levelSlider.getValue()) {
      case 0:
        GameState.isUnlimitedHint = true;
        GameState.remainsHint = 0;
        GameState.isHard = false;
        break;
      case 1:
        GameState.remainsHint = 5;
        GameState.isHard = false;
        break;
      case 2:
        GameState.remainsHint = 0;
        GameState.isHard = true;
        break;
      default:
        break;
    }
    switch ((int) timeSlider.getValue()) {
      case 0:
        GameState.timeLimit = 120;
        break;
      case 1:
        GameState.timeLimit = 240;
        break;
      case 2:
        GameState.timeLimit = 360;
        break;
      default:
        break;
    }
    App.loadRoom();
    GameState.isTts = ttsButton.isSelected();
    // todo change to intro screen later
    App.makeTimer();
    // App.loadRoom();
    App.setUi(AppUi.ART_ROOM);
    GameState.onArtRoom = true;
  }

  @FXML
  private void setLevelText() { // Set the text for which level the user selects.
    switch ((int) levelSlider.getValue()) {
      case 0:
        levelTexts.setText("In Easy Mode, you can get any amount of hints from the game master.");
        break;
      case 1:
        levelTexts.setText(
            "In Medium Mode, you can get a maximum of 5 hints from the game master.");
        break;
      case 2:
        levelTexts.setText("In Hard Mode, you cannot get any hints from game master.");
        break;
      default:
        break;
    }
  }
}
