package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class WinPageController {

  @FXML
  private void onStartPage() {
    App.setUi(AppUi.START);
  }
}
