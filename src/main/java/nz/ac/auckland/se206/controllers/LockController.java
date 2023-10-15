package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This class is the controller for the lock scene
 */
public class LockController {

  @FXML private ImageView resultIndicator;
  private boolean result = false;

  private final ArrayList<Integer> passcode = new ArrayList<>();
  @FXML
  private ImageView process100;
  @FXML
  private ImageView process75;
  @FXML
  private ImageView process50;
  @FXML
  private ImageView process25;
  @FXML
  private ProgressBar processBar;
  @FXML
  private TextArea textBox;
  @FXML
  private Button enterButton;
  @FXML
  private Button resetButton;
  @FXML
  private Button button0;
  @FXML
  private Button button1;
  @FXML
  private Button button2;
  @FXML
  private Button button3;
  @FXML
  private Button button4;
  @FXML
  private Button button5;
  @FXML
  private Button button6;
  @FXML
  private Button button7;
  @FXML
  private Button button8;
  @FXML
  private Button button9;
  @FXML
  private Button backButton;
  @FXML
  private ImageView escapeButton;
  private boolean isReleasedMouse = false;

  /**
   * This method is used to type the code into the text box
   * @param input the number that is pressed
   */
  private void typeCode(int input) {
    if (textBox.getText().equals("Incorrect passcode")) {
      textBox.clear();
    }
    if (passcode.size() < 4) {
      passcode.add(input);
      textBox.appendText(Integer.toString(input));
    }
  }

  /**
   * This method is used to check whether the code is correct
   */
  private void checkCode() {
    // If passcode is correct length (4), check whether it is the correct code as the riddle and
    // puzzle code.
    if (passcode.size() == 4) {
      StringBuilder sb = new StringBuilder();
      StringBuilder exitKey = new StringBuilder();
      exitKey.append(GameState.riddleCode);
      exitKey.append(GameState.puzzleCode);
      sb.append(passcode.get(0));
      sb.append(passcode.get(1));
      sb.append(passcode.get(2));
      sb.append(passcode.get(3));
      if (sb.toString().contentEquals(exitKey)) {
        textBox.clear();
        textBox.appendText("Door Unlocked");
        buttonDisable();
        GameState.isUnlocked = true;
        escapeButton.setVisible(true);
        resultIndicator.setVisible(true);
        escapeButton.setCursor(Cursor.OPEN_HAND);
      } else {
        textBox.clear();
        textBox.appendText("Incorrect passcode");
        passcode.clear();
      }
    } else { // If not correct length (4), incorrect answer.
      textBox.clear();
      textBox.appendText("Incorrect passcode");
      passcode.clear();
    }
  }

  /**
   * This method is used to increase the process bar
   */
  @FXML
  private void escapeButtonClicked() {
    if (GameState.isUnlocked) {
      isReleasedMouse = false;
      increaseProgressBar();
    }
  }

  /**
   * This method is used to go back to the home screen
   */
  @FXML
  private void escapeButtonReleased() {
    isReleasedMouse = true;
    if (result) {
      backToHome();
    } else {
      resetProgressBar();
    }
  }

  /**
   * This method is used to reset the process bar
   */
  private void resetProgressBar() {
    processBar.setProgress(0);
    setRed(process100);
    setRed(process75);
    setRed(process50);
    setRed(process25);
  }

  /**
   * This method is used to change the indicator of the process bar
   * @param i the current progress of the process bar
   */
  private void inprocessBar(int i){
    if (i== 20) {
      setGreen(process25);
    } else if (i == 45) {
      setGreen(process50);
    } else if (i == 70) {
      setGreen(process75);
    } else if (i == 95) {
      setGreen(process100);
    }
    int level = (GameState.buttonLevel + 1) * 25;
    result = level + 5 >= (int) (processBar.getProgress() * 100)
        && (int) (processBar.getProgress() * 100) >= level - 5;
    if (result) {
      setGreen(resultIndicator);
    }else{
      setRed(resultIndicator);
    }
  }

  /**
   * This method is used to set the indicator to green
   * @param indicator the indicator that is going to be set
   */
  private void setRed(ImageView indicator) {
    indicator.setImage(new Image("/images/redindicator.png"));
  }

  /**
   * This method is used to set the indicator to red
   * @param indicator the indicator that is going to be set
   */
  private void setGreen(ImageView indicator) {
    indicator.setImage(new Image("/images/greenindicator.png"));
  }

  /**
   * This method is used to go back to the home screen
   * Changes the scene to the win screen
   * Unloads the room
   * Resets the game state
   * Loads the room
   */
  private void backToHome() {
    App.setUi(AppUi.WIN_SCREEN);
  }

  /**
   * This method is used to increase the process bar
   */
  private void increaseProgressBar() {
    Task<Void> task = new Task<>() {
      @Override
      public Void call() throws InterruptedException {
        for (int i = 0; i <= 100; i++) {
          if (isReleasedMouse) {
            return null;
          }
          processBar.setProgress(processBar.getProgress() + 0.01);
          inprocessBar(i);
          Thread.sleep(50);
        }
        return null;
      }
    };
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * This method handles buttons input
   * @param event the button that is pressed
   */
  @FXML
  private void onType(ActionEvent event) {
    typeCode(Integer.parseInt(((Button) event.getSource()).getText()));
  }

  /**
   * This method is used to reset the text box when the reset button is pressed
   */
  @FXML
  private void onReset() {
    passcode.clear();
    textBox.clear();
  }

  /**
   * This method is used to check the code when the enter button is pressed
   */
  @FXML
  private void onEnter() {
    checkCode();
  }

  /**
   * This method is used to go back to the lobby room
   */
  @FXML
  private void onBack() {
    App.setUi(AppUi.LOBBY_ROOM);
  }

  /**
   * This method is used to disable all the buttons after the lock is unlocked
   */
  private void buttonDisable() {
    // Disables all the buttons on the lock as it is complete.
    button0.setDisable(true);
    button1.setDisable(true);
    button2.setDisable(true);
    button3.setDisable(true);
    button4.setDisable(true);
    button5.setDisable(true);
    button6.setDisable(true);
    button7.setDisable(true);
    button8.setDisable(true);
    button9.setDisable(true);
    enterButton.setDisable(true);
    resetButton.setDisable(true);
  }
}
