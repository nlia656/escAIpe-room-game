package nz.ac.auckland.se206.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.speech.TextToSpeech;

/**
 * This class is the controller for the level scene
 */
public class LevelController {

  @FXML private Button startButton;
  @FXML private Slider levelSlider;

  @FXML private Slider timeSlider;

  @FXML private Text levelText;
  @FXML private CheckBox ttsButton;

  /**
   * This method is used to round the slider value to the nearest integer
   */
  @FXML
  private void finishSlide() {
    double level = levelSlider.getValue();
    levelSlider.setValue(Math.round(level));
    setLevelText();
  }

  /**
   * This method is called to go back to start screen
   */
  @FXML
  private void onBack() {
    App.setUi(AppUi.START);
  }

  /**
   * This method is used to round the slider value to the nearest integer
   */
  @FXML
  private void finishTime() {
    double time = timeSlider.getValue();
    timeSlider.setValue(Math.round(time));
  }

  /**
   * This method is called to start the game
   * it sets time, hint and difficulty based on the user's selection
   * it also sets the tts option
   * it also starts the timer
   * then it loads the art room
   */
  @FXML
  private void onStart() {
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
    GameState.isTts = ttsButton.isSelected();
    // todo change to intro screen later
    App.makeTimer();
    // App.loadRoom();
    App.setUi(AppUi.ART_ROOM);
    GameState.onArtRoom = true;
    Task<Void> tts = new Task<>() { // Specify the generic type as Void
      @Override
      protected Void call() {
        TextToSpeech tts = new TextToSpeech();
        tts.speak(GameState.lastMsg);
        Platform.runLater(() -> {});
        return null;
      }
    };
    if (GameState.isTts) { // Check Text to Speech
      Thread thread = new Thread(tts);
      thread.setDaemon(true);
      thread.start();
    }
  }

  /**
   * This method is called to set the text for which level the user selects.
   * Change the text based on the level the user selects.
   */
  @FXML
  private void setLevelText() { // Set the text for which level the user selects.
    switch ((int) levelSlider.getValue()) {
      case 0:
        levelText.setText("In Easy Mode, you can get any amount of hints from the game master.");
        break;
      case 1:
        levelText.setText(
            "In Medium Mode, you can get a maximum of 5 hints from the game master.");
        break;
      case 2:
        levelText.setText("In Hard Mode, you cannot get any hints from game master.");
        break;
      default:
        break;
    }
  }
}
