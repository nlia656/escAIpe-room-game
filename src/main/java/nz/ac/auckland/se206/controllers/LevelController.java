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
 * Controller class for the level selection scene. Handles user interactions related to level and
 * game settings.
 */
public class LevelController {

  @FXML private Button startButton;
  @FXML private Slider levelSlider;
  @FXML private Slider timeSlider;
  @FXML private Text levelText;
  @FXML private CheckBox ttsButton;

  /** Rounds the slider value to the nearest integer. */
  @FXML
  private void finishSlide() {
    double level = levelSlider.getValue();
    levelSlider.setValue(Math.round(level));
    setLevelText();
  }

  /** Navigates back to the start screen. */
  @FXML
  private void onBack() {
    App.setUi(AppUi.START);
  }

  /** Rounds the slider value for time selection to the nearest integer. */
  @FXML
  private void finishTime() {
    double time = timeSlider.getValue();
    timeSlider.setValue(Math.round(time));
  }

  /**
   * Starts the game with selected settings. Sets time, hints, difficulty, and text-to-speech
   * option. Starts the game timer and loads the art room scene.
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
    Task<Void> tts =
        new Task<>() {
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

  /** Sets the description text based on the selected level. */
  @FXML
  private void setLevelText() {
    switch ((int) levelSlider.getValue()) {
      case 0:
        levelText.setText("In Easy Mode, you can get any amount of hints from the game master.");
        break;
      case 1:
        levelText.setText("In Medium Mode, you can get a maximum of 5 hints from the game master.");
        break;
      case 2:
        levelText.setText("In Hard Mode, you cannot get any hints from the game master.");
        break;
      default:
        break;
    }
  }
}
