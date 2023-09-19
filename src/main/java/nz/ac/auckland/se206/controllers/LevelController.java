package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LevelController {

  @FXML
  private Button startButton;
  @FXML
  private Slider levelSlider;

  @FXML
  private Slider timeSlider;

  @FXML
  private Text levelTexts;
  @FXML
  private CheckBox ttsButton;
  @FXML
  private void finishSlide() {
    double level = levelSlider.getValue();
    levelSlider.setValue(Math.round(level));
    setLevelText();
  }

  @FXML
  private void finishTime() {
    double time = timeSlider.getValue();
    timeSlider.setValue(Math.round(time));
  }

  @FXML
  private void start() throws IOException {
    GameState.initial();
    switch ((int) levelSlider.getValue()) {
      case 0:
        GameState.isUnlimitedHint = true;
        GameState.remainsHint = 0;
        break;
      case 1:
        GameState.remainsHint = 5;
        break;
      case 2:
        GameState.remainsHint = 0;
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
    if(ttsButton.isSelected()){
      GameState.isTts=true;
    }
    else{
      GameState.isTts=false;
    }
    SceneManager.addAppUi(AppUi.CHAT, App.loadFxml("chat"));
    //todo change to intro screen later
    App.setUi(AppUi.ART_ROOM);
    GameState.onArtRoom = true;

  }

  @FXML
  private void setLevelText() {
    switch ((int) levelSlider.getValue()) {
      case 0:
        levelTexts.setText(
            "In Easy Mode, you can attempt to get any times of hints from game master");
        break;
      case 1:
        levelTexts.setText(
            "In Medium Mode, you can attempt to get 5 times of hints from game master");
        break;
      case 2:
        levelTexts.setText("In Hard Mode, you cannot get any hints from game master");
        break;
      default:
        break;
    }
  }

}
