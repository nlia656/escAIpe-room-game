package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class ArtRoomController {
    @FXML private Label lblGM;

    @FXML private ImageView artToDino;
    @FXML private ImageView artToLobby;

    @FXML private Rectangle dagger;
    @FXML private Rectangle armour;
    @FXML private Rectangle pillar;
    @FXML private Rectangle crown;
    @FXML private Rectangle vase1;
    @FXML private Rectangle sword;
    @FXML private Rectangle vase2;
    @FXML private Rectangle bench1;
    @FXML private Rectangle bench2;
    @FXML private Rectangle painting1;
    @FXML private Rectangle painting2;
    @FXML private Rectangle painting3;
    @FXML private Rectangle painting4;
    @FXML private Rectangle painting5;
    @FXML private Rectangle books1;

    @FXML private Button btnHelp;

    @FXML private TitledPane artRoomPane;

    public void initialize() {

    }

    @FXML
    private void onHelp() {
        if (GameState.onPaintPuzzle) {
            showDialogPic("Guess The Painting", "The rest of the hint got burned and you are only left with this.", "Click on the help button to view image again.");
        }
    }

    private void showDialogPic(String title, String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setWidth(800); // Set width
        alert.setHeight(600);
        Image image = new Image(getClass().getResource("/images/Painting-1.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(750);
        imageView.setPreserveRatio(true);
        alert.setGraphic(imageView);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showDialog(String title, String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void onOpenGM() {
        App.setUi(AppUi.CHAT);
    }

    @FXML
    private void goLobby() {
        App.setUi(AppUi.LOBBY_ROOM);
        GameState.onArtRoom = false;
        GameState.onLobbyRoom = true;
    }

    @FXML
    private void goDino() {
        App.setUi(AppUi.DINO_ROOM);
        GameState.onArtRoom = false;
        GameState.onDinoRoom = true;
    }

    @FXML
    private void daggerClicked() {
        // Testing for the alert and this will only be shown after the first riddle is solved and can be shown again through a help button.
        if (GameState.isArtComplete) {
            showDialog("Info", "You have already solved the puzzle", "Good Job!");
            return;
        }
        showDialogPic("Guess The Painting", "The rest of the hint got burned and you are only left with this.", "Click on the help button to view image again.");
        GameState.onPaintPuzzle = true;
        // Add your code for handling the daggerClicked event here
    }

    @FXML
    private void armourClicked() {
        // Add your code for handling the armourClicked event here
    }

    @FXML
    private void pillarClicked() {
        // Add your code for handling the pillarClicked event here
    }

    @FXML
    private void crownClicked() {
        // Add your code for handling the crownClicked event here
    }

    @FXML
    private void vase1Clicked() {
        // Add your code for handling the vase1Clicked event here
    }

    @FXML
    private void swordClicked() {
        // Add your code for handling the swordClicked event here
    }

    @FXML
    private void vase2Clicked() {
        // Add your code for handling the vase2Clicked event here
    }

    @FXML
    private void bench1Clicked() {
        // Add your code for handling the bench1Clicked event here
    }

    @FXML
    private void bench2Clicked() {
        // Add your code for handling the bench2Clicked event here
    }

    @FXML
    private void painting1Clicked() {
        // Add your code for handling the painting1Clicked event here
    }

    @FXML
    private void painting2Clicked() {
        // Add your code for handling the painting2Clicked event here
    }

    @FXML
    private void painting3Clicked() {
        // Add your code for handling the painting3Clicked event here
    }

    @FXML
    private void painting4Clicked() {
        if (GameState.onPaintPuzzle) {
            showDialog("One Room Complete", "You have solved the puzzle!", "Code: 1234");
            GameState.onPaintPuzzle = false;
            GameState.isArtComplete = true;
        }


    }

    @FXML
    private void painting5Clicked() {
        // Add your code for handling the painting5Clicked event here
    }

    @FXML
    private void books1Clicked() {
        // Add your code for handling the books1Clicked event here
    }
}