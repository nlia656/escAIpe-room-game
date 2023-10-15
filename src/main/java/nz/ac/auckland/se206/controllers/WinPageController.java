package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** This class is the controller for the win scene */
public class WinPageController {

  /**
   * This method is called to go back to start screen
   *
   * @throws IOException if the fxml file cannot be loaded
   */
  @FXML
  private void onStartPage() throws IOException {
    // Thread to unload rooms and load rooms when player goes back to start page
    Task<Void> task =
        new Task<>() {
          @Override
          protected Void call() throws Exception {
            GameState.isGameComplete = true;
            Thread.sleep(1000);
            App.loadRoom();
            return null;
          }
        };
    App.setUi(AppUi.START);
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }
}
