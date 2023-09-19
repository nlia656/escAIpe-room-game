package nz.ac.auckland.se206.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;
//import javafx.scene.Cursor;

public class LobbyRoomController {
    @FXML private Label lblGM;
    @FXML private Label lblTime;

    @FXML private ImageView lobbyToArt;

    @FXML private Rectangle elevator;
    @FXML private Rectangle couch2;
    @FXML private Rectangle table;
    @FXML private Rectangle couch3;
    @FXML private Rectangle plant;

    @FXML private TitledPane lobbyRoomPane;


    public void initialize() {    Task timer = new Task() {
        @Override
        protected Object call() throws Exception {
            while (!GameState.isGameComplete) {
                if (!GameState.isPaused) {
                    lblTime.setText(String.valueOf(GameState.timeLeft));
                }
                Thread.sleep(100);
            }
            return null;
        }
    };
        Thread timerThread = new Thread(timer);
        timerThread.setDaemon(true);
        timerThread.start();
    }

    @FXML
    private void onOpenGM() {
        App.setUi(AppUi.CHAT);
    }

    @FXML
    private void goArtRoom() {
        App.setUi(AppUi.ART_ROOM);
        GameState.onLobbyRoom = false;
        GameState.onArtRoom = true;
    }

    @FXML
    private void elevatorClicked() {
        // Add your code for handling the elevatorClicked event here
    }

    @FXML
    private void couch2Clicked() {
        // Add your code for handling the couch2Clicked event here
    }

    @FXML
    private void tableClicked() {
        // Add your code for handling the tableClicked event here
    }

    @FXML
    private void couch3Clicked() {
        // Add your code for handling the couch3Clicked event here
    }

    @FXML
    private void plantClicked() {
        // Add your code for handling the plantClicked event here
    }
}
