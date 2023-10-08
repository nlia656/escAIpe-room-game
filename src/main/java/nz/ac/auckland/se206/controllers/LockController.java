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
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LockController {

  private final ArrayList<Integer> passcode = new ArrayList<>();
  @FXML private ProgressBar processBar;
  @FXML private TextArea textBox;
  @FXML private Button enterButton;
  @FXML private Button resetButton;
  @FXML private Button button0;
  @FXML private Button button1;
  @FXML private Button button2;
  @FXML private Button button3;
  @FXML private Button button4;
  @FXML private Button button5;
  @FXML private Button button6;
  @FXML private Button button7;
  @FXML private Button button8;
  @FXML private Button button9;
  @FXML private Button backButton;
  @FXML private ImageView escapeButton;
  private boolean isReleasedMouse = false;

  /** Initializes the room view, it is called when the room loads. */
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
      exitKey.append(BookPuzzleController.puzzleCode);
      sb.append(passcode.get(0));
      sb.append(passcode.get(1));
      sb.append(passcode.get(2));
      sb.append(passcode.get(3));
      if (sb.toString().equals(exitKey.toString())) {
        textBox.clear();
        textBox.appendText("Door Unlocked");
        buttonDisable();
        GameState.isUnlocked = true;

        escapeButton.setVisible(true);
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
  private void escapeButtonReleased() {
    isReleasedMouse = true;
    int level = (GameState.buttonLevel+1)*25;
    boolean result = level+10>=(int)(processBar.getProgress()*100)&&(int)(processBar.getProgress()*100)>=level-10;
    if(result){
      backToHome();
    }
    else{
      processBar.setProgress(0);
    }
  }
  private void backToHome(){
    App.setUi(AppUi.WIN_SCREEN);
    App.unloadRoom();
  }
  private void increaseProgressBar() {
    Task task = new Task<Void>() {
      @Override
      public Void call() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
          if(isReleasedMouse){
            return null;
          }
          processBar.setProgress(processBar.getProgress() + 0.01);
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
