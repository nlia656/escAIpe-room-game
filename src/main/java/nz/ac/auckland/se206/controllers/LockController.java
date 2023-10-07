package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LockController {

  private final ArrayList<Integer> passcode = new ArrayList<>();
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
  private void escapeButtonClicked() throws IOException {
    if (GameState.isUnlocked) {
      App.setUi(AppUi.WIN_SCREEN);
      App.unloadRoom();
      GameState.initial();
      App.loadRoom();
    }
  }

  @FXML
  private void onType0(ActionEvent event) {
    typeCode(0);
  }

  @FXML
  private void onType1(ActionEvent event) {
    typeCode(1);
  }

  @FXML
  private void onType2(ActionEvent event) {
    typeCode(2);
  }

  @FXML
  private void onType3(ActionEvent event) {
    typeCode(3);
  }

  @FXML
  private void onType4(ActionEvent event) {
    typeCode(4);
  }

  @FXML
  private void onType5(ActionEvent event) {
    typeCode(5);
  }

  @FXML
  private void onType6(ActionEvent event) {
    typeCode(6);
  }

  @FXML
  private void onType7(ActionEvent event) {
    typeCode(7);
  }

  @FXML
  private void onType8(ActionEvent event) {
    typeCode(8);
  }

  @FXML
  private void onType9(ActionEvent event) {
    typeCode(9);
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
