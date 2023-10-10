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
   * Initializes the room view, it is called when the room loads.
   */
  public void initialize() {
    if (GameState.isUnlocked) {
      textBox.clear();
      textBox.appendText("Door Unlocked");
      buttonDisable();
    }
    // Initialization code goes here
  }

  private void typeCode(int i) {
    if (textBox.getText().equals("Incorrect passcode")) {
      textBox.clear();
    }
    if (passcode.size() < 4) {
      passcode.add(i);
      textBox.appendText(Integer.toString(i));
    }
  }

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

  @FXML
  private void escapeButtonClicked() {
    if (GameState.isUnlocked) {
      isReleasedMouse = false;
      increaseProgressBar();
    }
  }

  @FXML
  private void escapeButtonReleased() throws IOException {
    isReleasedMouse = true;
    if (result) {
      backToHome();
    } else {
      resetProgressBar();
    }
  }

  private void resetProgressBar() {
    processBar.setProgress(0);
    setRed(process100);
    setRed(process75);
    setRed(process50);
    setRed(process25);
  }
  private void inprocessBar(int i){
    if (i== 25) {
      setGreen(process25);
    } else if (i == 40) {
      setGreen(process50);
    } else if (i == 65) {
      setGreen(process75);
    } else if (i == 90) {
      setGreen(process100);
    }
    int level = (GameState.buttonLevel + 1) * 25;
    result = level + 10 >= (int) (processBar.getProgress() * 100)
        && (int) (processBar.getProgress() * 100) >= level - 10;
    if (result) {
      setGreen(resultIndicator);
    }else{
      setRed(resultIndicator);
    }
  }

  private void setRed(ImageView indicator) {
    indicator.setImage(new Image("/images/redindicator.png"));
  }

  private void setGreen(ImageView indicator) {
    indicator.setImage(new Image("/images/greenindicator.png"));
  }

  private void backToHome() throws IOException {
    App.setUi(AppUi.WIN_SCREEN);
    GameState.initial();
    App.unloadRoom();
    App.loadRoom();
  }

  private void increaseProgressBar() {
    Task task = new Task<Void>() {
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

  @FXML
  private void onType(ActionEvent event) {
    typeCode(Integer.parseInt(((Button) event.getSource()).getText()));
  }

  @FXML
  private void onReset(ActionEvent event) {
    passcode.clear();
    textBox.clear();
  }

  @FXML
  private void onEnter(ActionEvent event) throws IOException {
    checkCode();
  }

  @FXML
  private void onBack(ActionEvent event) throws IOException {
    App.setUi(AppUi.LOBBY_ROOM);
  }

  private void buttonDisable() {
    // Disables all of the buttons on the lock as it is complete.
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
