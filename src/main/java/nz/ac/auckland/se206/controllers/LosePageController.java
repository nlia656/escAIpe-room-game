package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class LosePageController {

  @FXML
  private void onStartPage() throws IOException {
    App.unloadRoom();
    GameState.initial();
    App.loadRoom();
    App.setUi(AppUi.START);
  }
}
