package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** This is the entry point of the JavaFX application. */
public class App extends Application {

  private static Scene scene;
  public static volatile boolean timerRunning = true;

  public static void main(final String[] args) {
    launch();
  }

  /**
   * Loads an FXML file and returns the associated UI node.
   *
   * @param fxml The name of the FXML file (without extension).
   * @return The UI node of the FXML file.
   * @throws IOException If the file is not found.
   */
  private static Parent loadFxml(final String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml")).load();
  }

  /**
   * Set the UI to a specified scene.
   *
   * @param newUi The UI scene to set.
   */
  public static void setUi(AppUi newUi) {
    scene.setRoot(SceneManager.getAppUi(newUi));
  }

  /** Create and run a timer that handles game timing. */
  public static void makeTimer() {
    Task<Void> task =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            // Create a timer thread
            for (int i = GameState.timeLimit; i >= 0; i--) {
              if (!timerRunning) {
                break;
              }
              if (!GameState.isPaused) {
                final int finalI = i;
                Platform.runLater(
                    () -> {
                      GameState.timeLeft = finalI;
                      if (finalI == 0) {
                        // TODO: Handle game over here, e.g., transition to the game over screen
                        App.setUi(AppUi.LEVEL);
                      }
                    });
              }
              Thread.sleep(1000);
            }
            return null;
          }
        };
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  @Override
  public void start(final Stage stage) throws IOException {
    SceneManager.addAppUi(AppUi.START, loadFxml("start"));
    SceneManager.addAppUi(AppUi.LEVEL, loadFxml("level"));
    // SceneManager.addAppUi(AppUi.ROOM, loadFxml("room"));

    SceneManager.addAppUi(AppUi.END_PAGE, loadFxml("endPage"));

    scene = new Scene(SceneManager.getAppUi(AppUi.START), 720, 540);

    stage.setScene(scene);
    stage.show();
  }

  public static void loadRoom() throws IOException {
    SceneManager.addAppUi(AppUi.ART_ROOM, loadFxml("artRoom"));
    SceneManager.addAppUi(AppUi.CHAT, loadFxml("chat"));
    SceneManager.addAppUi(AppUi.DINO_ROOM, loadFxml("dinoRoom"));
    SceneManager.addAppUi(AppUi.LOBBY_ROOM, loadFxml("lobbyRoom"));
    SceneManager.addAppUi(AppUi.BOOK_PUZZLE, loadFxml("bookPuzzle"));
  }
}
