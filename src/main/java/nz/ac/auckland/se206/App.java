package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.controllers.ChatController;

/**
 * This is the entry point of the JavaFX application.
 */
public class App extends Application {

  public static Stage stage;
  private static Scene scene;
  private static ChatController chatController;

  public static void main(final String[] args) {
    launch();
  }

  /**
   * Loads an FXML file and returns the associated UI node.
   *
   * @param fxml The name of the FXML file (without extension).
   * @return The UI node of the FXML file.
   */
  private static FXMLLoader loadFxml(final String fxml) {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
  }

  /**
   * Set the UI to a specified scene.
   *
   * @param newUi The UI scene to set.
   */
  public static void setUi(AppUi newUi) {
    scene.setRoot(SceneManager.getAppUi(newUi));
  }

  /**
   * Create and run a timer that handles game timing.
   */
  public static void makeTimer() {
    Task<Void> task = new Task<>() { // Specify the generic type as Void
      @Override
      protected Void call() throws Exception {
        // Create a timer thread
        for (int i = GameState.timeLimit; i >= 0; i--) {
          if (GameState.isGameComplete) {
            return null;
          }
          if (!GameState.isPaused) {
            GameState.timeLeft = String.format("%d:%02d", i / 60, i % 60);
            if (i == 0) {
              App.setUi(AppUi.LOSE_SCREEN); // When timer runs out, show lose page.
            }
          }
          Thread.sleep(1000);
          // If the game is complete, stop the timer.
        }
        return null;
      }
    };
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * This method is used to load the room
   *
   * @throws IOException if the fxml file is not found
   */
  @FXML
  public static void loadRoom() throws IOException {
    unloadRoom();
    GameState.initial();
    // Add the scenes to the HashMap
    Task<Void> task = new Task<>() {
      @Override
      protected Void call() throws Exception {
        FXMLLoader chat = loadFxml("chat");
        SceneManager.addAppUi(AppUi.CHAT, chat.load());
        chatController = chat.getController();
        SceneManager.addAppUi(AppUi.ART_ROOM, loadFxml("artRoom").load());
        SceneManager.addAppUi(AppUi.DINO_ROOM, loadFxml("dinoRoom").load());
        SceneManager.addAppUi(AppUi.LOBBY_ROOM, loadFxml("lobbyRoom").load());
        SceneManager.addAppUi(AppUi.BOOK_PUZZLE, loadFxml("bookPuzzle").load());
        SceneManager.addAppUi(AppUi.SCROLL, loadFxml("codeScroll").load());
        SceneManager.addAppUi(AppUi.LOCK, loadFxml("lock").load());
        SceneManager.addAppUi(AppUi.BENCH_PUZZLE, loadFxml("benchPuzzle").load());
        return null;
      }
    };
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * This method is used to get the chat controller
   *
   * @return the chat controller
   */
  public static ChatController getChatController() {
    return chatController;
  }

  /**
   * This method is used to unload the room
   */
  @FXML
  public static void unloadRoom() {
    // Remove the scenes from the HashMap
    GameState.isGameComplete = true;
    SceneManager.removeAppUi(AppUi.ART_ROOM);
    SceneManager.removeAppUi(AppUi.CHAT);
    SceneManager.removeAppUi(AppUi.DINO_ROOM);
    SceneManager.removeAppUi(AppUi.LOBBY_ROOM);
    SceneManager.removeAppUi(AppUi.BOOK_PUZZLE);
    SceneManager.removeAppUi(AppUi.BENCH_PUZZLE);
    SceneManager.removeAppUi(AppUi.SCROLL);
    SceneManager.removeAppUi(AppUi.LOCK);
  }

  /**
   * This method is used to get the stage
   *
   * @return the stage
   */
  public static Stage getStage() {
    return stage;
  }

  /**
   * This method is used to start the application
   *
   * @param stage the stage
   * @throws IOException if the fxml file is not found
   */
  @Override
  public void start(final Stage stage) throws IOException {
    Font.loadFont(App.class.getResource("/css/bank-gothic-medium-bt.ttf").toExternalForm(), 12);
    Font.loadFont(App.class.getResource("/css/britanic.ttf").toExternalForm(), 12);
    Font.loadFont(App.class.getResource("/css/papyrus.ttf").toExternalForm(), 12);
    App.stage = stage;
    // Load the scenes that don't need to be reset when replaying games.
    SceneManager.addAppUi(AppUi.START, loadFxml("start").load());
    SceneManager.addAppUi(AppUi.LEVEL, loadFxml("level").load());
    SceneManager.addAppUi(AppUi.CREDITS, loadFxml("creditsScene").load());
    SceneManager.addAppUi(AppUi.WIN_SCREEN, loadFxml("winPage").load());
    SceneManager.addAppUi(AppUi.LOSE_SCREEN, loadFxml("losePage").load());
    loadRoom();
    stage.setResizable(false);
    scene = new Scene(SceneManager.getAppUi(AppUi.START), 720, 540);
    scene.getStylesheets().add("/css/styling.css");
    stage.setTitle("Heist of the Century: The Art Museum Affair");
    stage.setScene(scene);
    stage.show();
  }
}
