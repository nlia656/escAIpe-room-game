package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LosePageController {

  @FXML
  private void onStartPage() {
    App.unloadRoom();
    App.setUi(AppUi.START);
  }
}
