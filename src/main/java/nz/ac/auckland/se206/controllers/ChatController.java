package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
 * This class is the controller for the chat scene. It handles the events that occur in the chat
 * scene. It extends the SceneController class.
 */
public class ChatController extends SceneController {

  @FXML private Text hintRemains;
  @FXML private Text hintsGone;
  @FXML private Button backButton;
  @FXML private TextArea chatTextArea;
  @FXML private TextField inputText;
  @FXML private Button noTtsButton;
  @FXML private Button sendButton;
  @FXML private Button hintButton;
  @FXML private ImageView picDinoRoom;
  @FXML private ImageView picArtRoom;
  @FXML private ImageView picLobbyRoom;

  private ChatCompletionRequest chatCompletionRequest;
  private ChatCompletionRequest hintCompletionRequest;
  private boolean isGptRunning = false;

  /**
   * This method is called by the FXMLLoader when initialization is complete. It runs the GPT model
   * to generate the riddle. It also sets up the timer label in this scene.
   */
  @FXML
  public void initialize() {
    // Create a new thread to run the GPT model
    Task<Void> task =
        new Task<>() {
          @Override
          protected Void call() { // Specify the generic type as Void
            inProcess();
            try {
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
    if (GameState.isHard) {
      hintButton.setDisable(true);
    }
    chatCompletionRequest =
        new ChatCompletionRequest().setN(1).setTemperature(0.1).setTopP(0.5).setMaxTokens(140);
    hintCompletionRequest =
        new ChatCompletionRequest().setN(1).setTemperature(0.1).setTopP(0.5).setMaxTokens(140);
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                event -> {
                  lblTime.setText(GameState.timeLeft);
                  lblHints.setText(Integer.toString(GameState.remainsHint));
                }));
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
    timeline.play();
  }

  /**
   * Appends a chat message to the chat text area.
   *
   * @param msg the chat message to append
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
    try { // Run the GPT model
      ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
      Choice result = chatCompletionResult.getChoices().iterator().next();
      chatCompletionRequest.addMessage(result.getChatMessage());
      if (result.getChatMessage().getRole().equals("assistant")) {
        appendChatMessage(result.getChatMessage(), "Game master");
      } else {
        appendChatMessage(result.getChatMessage(), "You");
      }
      GameState.lastMsg = result.getChatMessage().getContent();
      if (result.getChatMessage().getRole().equals("assistant")
          && result.getChatMessage().getContent().startsWith("Correct")) {
        GameState.isRiddleResolved = true;
        System.out.println("MEOW");
      }
      Task<Void> tts = new Task<>() { // Specify the generic type as Void
            @Override
            protected Void call() {
              TextToSpeech tts = new TextToSpeech();
              tts.speak(result.getChatMessage().getContent());
              Platform.runLater(() -> {});
              return null;
            }
          };
      if (GameState.isTts) { // Check Text to Speech
        Thread thread = new Thread(tts);
        thread.setDaemon(true);
        thread.start();
      }
    } catch (ApiProxyException e) {
      showApiError(e);
    }
  }

  /**
   * Sends a message to the GPT model.
   *
   * @param event the action event triggered by the send button
   */
  @FXML
  private void onSendMessage(ActionEvent event) {
    String message = inputText.getText();
    if (message.trim().isEmpty()) {
      return;
    }
    // Clear input text field
    inputText.clear();
    ChatMessage msg = new ChatMessage("user", message);
    appendChatMessage(msg, "You said");
    // Run GPT model in a separate thread
    Task<Void> task = new Task<>() { // Specify the generic type as Void
          @Override
          protected Void call() throws Exception { // Specify the generic type as Void
            inProcess();
            runGpt(msg);
            Platform.runLater(
                () -> {
                  finishProcess();
                  try {
                    Thread.sleep(200);
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

  /**
   * Navigates back to the previous view.
   *
   * @param event the action event triggered by the go back button
   */
  @FXML
  private void onGoBack(ActionEvent event) {
    // Go back to the previous scene
    if (GameState.onArtRoom) {
      App.setUi(AppUi.ART_ROOM);
    } else if (GameState.onDinoRoom) {
      App.setUi(AppUi.DINO_ROOM);
    } else {
      App.setUi(AppUi.LOBBY_ROOM);
    }
  }

  private void inProcess() {
    Task<Void> runAnis =
        new Task<>() {
          @Override
          protected Void call() throws Exception {
            int i = 0;
            while (isGptRunning) {
              switch (i) {
                case 0:
                  inputText.setText("Game master is typing .");
                  i++;
                  break;
                case 1:
                  inputText.setText("Game master is typing ..");
                  i++;
                  break;
                case 2:
                  inputText.setText(
                      "Game master is typing ..."); // Update the graphics so that the user knows
                  // the GPT is replying.
                  i = 0;
                  break;
              }
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

  private void finishProcess() {
    // Reset the graphics
    isGptRunning = false;
    inputText.setDisable(false);
    sendButton.setDisable(false);
    hintButton.setDisable(false);
  }

  private void showApiError(ApiProxyException e) {
    // Error alert for GPT
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Warning");
    alert.setHeaderText("OpenAI Api Error");
    alert.setContentText(e.getMessage());
    alert.showAndWait();
  }

  @FXML
  private void onAskHint() {
    // Give hints depending on difficulty of game
    Task<Void> task =
        new Task<>() {
          @Override
          protected Void call() { // Specify the generic type as Void
            inProcess();
            try { // Run the GPT to give hints to user
              hintCompletionRequest.addMessage(
                  new ChatMessage("user", GptPromptEngineering.getHints()));
              ChatCompletionResult hintCompletionResult = hintCompletionRequest.execute();
              Choice result = hintCompletionResult.getChoices().iterator().next();
              hintCompletionRequest.addMessage(result.getChatMessage());
              appendChatMessage(result.getChatMessage(), "Game master");
              GameState.lastMsg = result.getChatMessage().getContent();
            } catch (ApiProxyException e) {
              showApiError(e);
            }
            Platform.runLater(
                () -> {
                  finishProcess();
                  try {
                    Thread.sleep(200);
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }
                });
            return null;
          }
        };
    if (!GameState.isUnlimitedHint) {
      System.out.println("is it medium");
      GameState.remainsHint--;
      if (GameState.remainsHint == 0) {
        System.out.println("hints gone");
        hintButton.setDisable(true);
      }
    }
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  public void setChatBackground() {
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
