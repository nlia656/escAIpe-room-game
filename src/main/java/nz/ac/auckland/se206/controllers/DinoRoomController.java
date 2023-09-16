package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class DinoRoomController {
    @FXML Label lblGM;
    
    public void initialize() {
    }

    @FXML
    private void onOpenGM() {
        App.setUi(AppUi.CHAT);
    } 
    
}
