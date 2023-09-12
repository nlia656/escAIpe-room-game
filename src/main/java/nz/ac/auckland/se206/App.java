package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the entry point of the JavaFX application, while you can change this class, it should
 * remain as the class that runs the JavaFX application.
 */
public class App extends Application {

  private static Scene scene;
  public static volatile boolean timerRunning = true;
  public static void main(final String[] args) {
    launch();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  /**
   * Returns the node associated to the input file. The method expects that the file is located in
   * "src/main/resources/fxml".
   *
   * @param fxml The name of the FXML file (without extension).
   * @return The node of the input file.
   * @throws IOException If the file is not found.
   */
  private static Parent loadFxml(final String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml")).load();
  }
  public static void makeTimer() {
    //create a timer for the game at GameSettings.timeLimit seconds, can be paused and resumed,
    // as the end, change the scene to GameOver,can be showed in the room scene
    Task<Void> task = new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        //make a timer thread
        for (int i = GameState.timeLimit; i >= 0; i--) {
          if (!timerRunning) {
            break;
          }
          if (GameState.isPaused) {
            i++;
            Thread.sleep(1000);
          } else {
            final int finalI = i;
            Platform.runLater(() -> {
              GameState.timeLeft = finalI;
              if (finalI == 0) {
                try {
                  //todo gameover screen should be made later with replay feature
                  setRoot("gameOver");
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            });
            Thread.sleep(1000);
          }
        }
        return null;
      }
    };
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }
  /**
   * This method is invoked when the application starts. It loads and shows the "Canvas" scene.
   *
   * @param stage The primary stage of the application.
   * @throws IOException If "src/main/resources/fxml/canvas.fxml" is not found.
   */

  @Override
  public void start(final Stage stage) throws IOException {
    Parent root = loadFxml("start");
    scene = new Scene(root, 600, 470);
    stage.setScene(scene);
    stage.show();
    root.requestFocus();
  }

}
