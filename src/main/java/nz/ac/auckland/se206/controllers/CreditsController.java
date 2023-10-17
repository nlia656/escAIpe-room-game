package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * Controller class for the credits scene.
 */
public class CreditsController {

  @FXML
  private TextArea creditText;

  @FXML
  private void initialize() {
    String credit =
        "Image by redgreystock\n"
            + "https://www.freepik.com/free-vector/isometric-elevator-hall-interior_8407434.htm\n"
            + "Image by macrovector\n"
            + "https://www.freepik.com/free-vector/ethnic-museum-isometric-composition_6380056.htm\n"
            + "https://www.freepik.com/free-vector/isometric-color-illustration-museum-interior-with-exhibit-painting_3791816.htm\n"
            + "https://www.freepik.com/free-vector/law-justice-trial-with-prisoner-cage_15080049.htm\n "
            + "https://www.freepik.com/free-vector/super-agent-cartoon-poster-with-man-running-shooting-with-gun-vector-illustration_37420219.htm\n"
            + "Image by Freepik\n"
            + "https://www.freepik.com/free-photo/top-view-blank-menu-book_7453988.htm\n"
            + "Image by upklyak\n"
            + "https://www.freepik.com/free-vector/realistic-open-parchment-scroll-transparent_39845337.htm\n"
            + "Image by vectorpouch\n"
            + "https://www.freepik.com/free-vector/robbery-jewelry-shop-robber-with-nail-puller_5603493.htm\n"
            + "Image by brgfx\n"
            + "https://www.freepik.com/free-vector/empty-wooden-sign-banner-isolated_24553855.htm\n"
            + "Image by svstudioart\n"
            + "https://www.freepik.com/free-vector/new-modern-realistic-front-view-black-iphone-mockup-isolated-white-mobile-template-vector_33632328.htm\n"
            + "Image by rawpixel.com\n"
            + "https://www.freepik.com/free-vector/blur-pink-blue-abstract-gradient-background-vector_34378069.htm";
    creditText.setText(credit);
  }

  /**
   * This method is called when the player clicks the "Go to Lobby" button. It changes the scene to
   * the start screen.
   */
  @FXML
  private void onBackStart() {
    // Remove the current scene from the hash map and navigate back to the start screen.
    App.setUi(AppUi.START);
  }
}
