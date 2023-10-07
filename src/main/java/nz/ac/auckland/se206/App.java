package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** This is the entry point of the JavaFX application. */
public class App extends Application {

  public static volatile boolean timerRunning = true;
  private static Scene scene;
  public static Stage Stage;

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
    Task<Void> task = new Task<>() { // Specify the generic type as Void
          @Override
          protected Void call() throws Exception {
            // Create a timer thread
            for (int i = GameState.timeLimit; i >= 0; i--) {
              if (!timerRunning) {
                break;
              }
              if (GameState.isGameComplete) {
                GameState.isGameComplete = false;
                return null;
              }
              if (!GameState.isPaused) {
                final int finalI = i;
                Platform.runLater(
                    () -> {
                      GameState.timeLeft = finalI;
                      if (finalI == 0) {
                        App.setUi(AppUi.LOSE_SCREEN); // When timer runs out, show lose page.
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

  @FXML
  public static void loadRoom() throws IOException {
    // Add the scenes to the HashMap
    SceneManager.addAppUi(AppUi.ART_ROOM, loadFxml("artRoom"));
    SceneManager.addAppUi(AppUi.CHAT, loadFxml("chat"));
    SceneManager.addAppUi(AppUi.DINO_ROOM, loadFxml("dinoRoom"));
    SceneManager.addAppUi(AppUi.LOBBY_ROOM, loadFxml("lobbyRoom"));
    SceneManager.addAppUi(AppUi.BOOK_PUZZLE, loadFxml("bookPuzzle"));
    SceneManager.addAppUi(AppUi.SCROLL, loadFxml("codeScroll"));
    SceneManager.addAppUi(AppUi.LOCK, loadFxml("lock"));
  }

  @FXML
  public static void unloadRoom() {
    // Remove the scenes from the HashMap
    GameState.isGameComplete = true;
    SceneManager.removeAppUi(AppUi.ART_ROOM);
    SceneManager.removeAppUi(AppUi.CHAT);
    SceneManager.removeAppUi(AppUi.DINO_ROOM);
    SceneManager.removeAppUi(AppUi.LOBBY_ROOM);
    SceneManager.removeAppUi(AppUi.BOOK_PUZZLE);
    SceneManager.removeAppUi(AppUi.SCROLL);
    SceneManager.removeAppUi(AppUi.LOCK);
  }

  public static Stage getStage() {
    return Stage;
  }

  @Override
  public void start(final Stage stage) throws IOException {
    App.Stage = stage;
    // Load the scenes that don't need to be reset when replaying games.
    SceneManager.addAppUi(AppUi.START, loadFxml("start"));
    SceneManager.addAppUi(AppUi.LEVEL, loadFxml("level"));
    SceneManager.addAppUi(AppUi.CREDITS, loadFxml("creditsScene"));
    SceneManager.addAppUi(AppUi.WIN_SCREEN, loadFxml("winPage"));
    SceneManager.addAppUi(AppUi.LOSE_SCREEN, loadFxml("losePage"));
    stage.setResizable(false);
    scene = new Scene(SceneManager.getAppUi(AppUi.START), 720, 540);
    stage.setTitle("Heist of the Century: The Art Museum Affair");
    stage.setScene(scene);
    stage.show();
  }
}
