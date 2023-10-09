package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LosePageController {

  @FXML
  private void onStartPage() throws IOException {
    App.setUi(AppUi.START);
    GameState.initial();
    App.unloadRoom();
    App.loadRoom();
  }
}
