package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult.Choice;
import nz.ac.auckland.se206.speech.TextToSpeech;

/**
 * Controller class for the chat scene. Handles events and interactions that occur in the chat
 * scene. Extends the SceneController class.
 */
public class ChatController extends SceneController {

  @FXML private Text hintRemains;
  @FXML private TextArea chatTextArea;
  @FXML private TextField inputText;
  @FXML private Button sendButton;
  @FXML private Button hintButton;
  @FXML private ImageView picDinoRoom;
  @FXML private ImageView picArtRoom;
  @FXML private ImageView picLobbyRoom;

  private ChatCompletionRequest chatCompletionRequest;
  private ChatCompletionRequest hintCompletionRequest;
  private boolean isGptRunning = false;

  /**
   * Initializes the controller. Runs the GPT model to generate the riddle. Sets up the timer label
   * in this scene.
   */
  @FXML
  public void initialize() {
    // Create a new thread to run the GPT model
    Task<Void> task =
        new Task<>() {
          @Override
          protected Void call() {
            inProcess();
            try {
              // Run the GPT model to generate the riddle
              runGpt(
                  new ChatMessage(
                      "user", GptPromptEngineering.getRiddleWithGivenWord(GameState.riddleAnswer)));
            } catch (ApiProxyException e) {
              showApiError(e);
            }
            Platform.runLater(
                () -> {
                  finishProcess();
                  try {
                    // Sleep 0.2 seconds
                    Thread.sleep(200);
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }
                });
            return null;
          }
        };

    // Configure the hints
    if (!GameState.isUnlimitedHint && GameState.remainsHint != 0) {
      hintRemains.setVisible(true);
    }

    // Setting the settings for chat and hints generation
    chatCompletionRequest =
        new ChatCompletionRequest().setN(1).setTemperature(0.3).setTopP(0.5).setMaxTokens(300);
    hintCompletionRequest =
        new ChatCompletionRequest().setN(1).setTemperature(0.2).setTopP(0.5).setMaxTokens(300);
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();

    // Set up timer label
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                event -> {
                  lblTime.setText(GameState.timeLeft);
                  // Set the hints label
                  if (GameState.isHard) {
                    lblHints.setText("");
                    hintsLeft.setText("No hints!");
                  } else if (GameState.isUnlimitedHint) {
                    lblHints.setText("");
                    hintsLeft.setText("Unlimited hints!");
                  } else {
                    lblHints.setText(Integer.toString(GameState.remainsHint));
                  }
                  if (GameState.remainsHint == 0) {
                    hintButton.setVisible(false);
                  } else {
                    hintButton.setVisible(true);
                  }
                }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  /**
   * Appends a chat message to the chat text area.
   *
   * @param msg the chat message to append
   * @param role the role of the sender
   */
  private void appendChatMessage(ChatMessage msg, String role) {
    chatTextArea.appendText(role + ": " + msg.getContent() + "\n\n");
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * @param msg the chat message to process
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  private void runGpt(ChatMessage msg) throws ApiProxyException {
    chatCompletionRequest.addMessage(msg);
    try {
      // Run the GPT model
      ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
      Choice result = chatCompletionResult.getChoices().iterator().next();
      chatCompletionRequest.addMessage(result.getChatMessage());
      if (result.getChatMessage().getRole().equals("assistant")) {
        appendChatMessage(result.getChatMessage(), "Security");
      } else {
        appendChatMessage(result.getChatMessage(), "You");
      }
      GameState.lastMsg = result.getChatMessage().getContent();
      // Check if the riddle is resolved
      if (result.getChatMessage().getRole().equals("assistant")
          && (GameState.lastMsg.contains("Correct") || GameState.lastMsg.contains("correct"))) {
        GameState.isRiddleResolved = true;
        System.out.println("MEOW");
      }
      Task<Void> tts =
          new Task<>() {
            @Override
            protected Void call() {
              // Run the text to speech
              TextToSpeech tts = new TextToSpeech();
              tts.speak(result.getChatMessage().getContent());
              Platform.runLater(() -> {});
              return null;
            }
          };
      if (GameState.isTts) {
        Thread thread = new Thread(tts);
        thread.setDaemon(true);
        thread.start();
      }
    } catch (ApiProxyException e) {
      showApiError(e);
    }
  }

  /** Sends a message to the GPT model. */
  @FXML
  private void onSendMessage() {
    // Get the message from the text field
    String message = inputText.getText();
    if (message.trim().isEmpty()) {
      return;
    }
    inputText.clear();
    ChatMessage msg = new ChatMessage("user", message);
    appendChatMessage(msg, "You");
    Task<Void> task =
        new Task<>() {
          @Override
          protected Void call() throws Exception {
            inProcess();
            // Run the GPT
            runGpt(msg);
            Platform.runLater(
                () -> {
                  finishProcess();
                  try {
                    // Sleep 0.4 seconds
                    Thread.sleep(400);
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }
                });
            return null;
          }
        };
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  /** Navigates back to the previous view. */
  @FXML
  private void onGoBack() {
    // Go back to previous scene
    if (GameState.onArtRoom) {
      App.setUi(AppUi.ART_ROOM);
    } else if (GameState.onDinoRoom) {
      App.setUi(AppUi.DINO_ROOM);
    } else {
      App.setUi(AppUi.LOBBY_ROOM);
    }
  }

  /** Displays typing animation while waiting for GPT response. */
  private void inProcess() {
    Task<Void> runAnis =
        new Task<>() {
          @Override
          protected Void call() throws Exception {
            int i = 0;
            // Updating the animation of Gpt typing
            while (isGptRunning) {
              switch (i) {
                case 0:
                  inputText.setText("");
                  inputText.setText("Security is typing .");
                  i++;
                  break;
                case 1:
                  inputText.setText("");
                  inputText.setText("Security is typing ..");
                  i++;
                  break;
                case 2:
                  inputText.setText("");
                  inputText.setText("Security is typing ...");
                  i = 0;
                  break;
              }
              // Sleep 0.2 seconds
              Thread.sleep(200);
            }
            Platform.runLater(() -> inputText.setText(""));
            return null;
          }
        };
    isGptRunning = true;
    inputText.setDisable(true);
    sendButton.setDisable(true);
    hintButton.setDisable(true);
    Thread thread = new Thread(runAnis);
    thread.setDaemon(true);
    thread.start();
  }

  /** This method disables the buttons and text field. */
  private void finishProcess() {
    isGptRunning = false;
    inputText.setDisable(false);
    sendButton.setDisable(false);
    hintButton.setDisable(false);
  }

  /**
   * Displays an error alert for API proxy issues.
   *
   * @param e the exception containing the error message
   */
  private void showApiError(ApiProxyException e) {
    // Display an error alert
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Warning");
    alert.setHeaderText("OpenAI Api Error");
    alert.setContentText(e.getMessage());
    alert.showAndWait();
  }

  /** Asks for hints from the GPT model. */
  @FXML
  private void onAskHint() {
    Task<Void> task =
        new Task<>() {
          @Override
          protected Void call() {
            // Run the GPT model to generate hints
            inProcess();
            try {
              hintCompletionRequest.addMessage(
                  new ChatMessage("user", GptPromptEngineering.getHints()));
              ChatCompletionResult hintCompletionResult = hintCompletionRequest.execute();
              Choice result = hintCompletionResult.getChoices().iterator().next();
              hintCompletionRequest.addMessage(result.getChatMessage());
              appendChatMessage(result.getChatMessage(), "Security");
              GameState.lastMsg = result.getChatMessage().getContent();
            } catch (ApiProxyException e) {
              showApiError(e);
            }
            Platform.runLater(
                () -> {
                  finishProcess();
                  try {
                    // Sleep 0.4 seconds
                    Thread.sleep(400);
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }
                });
            return null;
          }
        };
    // Decrease the number of hints left
    if (!GameState.isUnlimitedHint) {
      GameState.remainsHint--;
      // If no hints left, don't show button
      if (GameState.remainsHint == 0) {
        hintButton.setVisible(false);
      }
    }
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  /** Sets the chat background based on the current room. */
  public void setChatBackground() {
    // Set the chat background based on the current room
    if (GameState.onArtRoom) {
      picArtRoom.setVisible(true);
      picDinoRoom.setVisible(false);
      picLobbyRoom.setVisible(false);
    } else if (GameState.onDinoRoom) {
      picArtRoom.setVisible(false);
      picDinoRoom.setVisible(true);
      picLobbyRoom.setVisible(false);
    } else {
      picArtRoom.setVisible(false);
      picDinoRoom.setVisible(false);
      picLobbyRoom.setVisible(true);
    }
  }
}
