package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
 * Controller class for the chat view.
 */
public class ChatController {

  @FXML
  private Text hintRemains;
  @FXML
  private Button backButton;
  @FXML
  private TextArea chatTextArea;
  @FXML
  private TextField inputText;
  @FXML
  private Button noTtsButton;
  @FXML
  private Button sendButton;
  @FXML
  private ProgressIndicator progressBar;
  @FXML
  private Button hintButton;
  private ChatCompletionRequest chatCompletionRequest;

  /**
   * Initializes the chat view, loading the riddle.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    chatCompletionRequest = GameState.chatCompletionRequest;
    Task task = new Task() {
      @Override
      protected Object call() throws Exception {
        inProcess();
        try {
          runGpt(new ChatMessage("user",
              GptPromptEngineering.getRiddleWithGivenWord(GameState.artRoomRiddleAnswer)));
        } catch (ApiProxyException e) {
          showApiError(e);
        }
        Platform.runLater(() -> {
          finishProcess();
        });
        return null;
      }
    };
    if (!GameState.isUnlimitedHint && GameState.remainsHint != 0) {
      hintRemains.setVisible(true);
    }
    if (GameState.remainsHint == 0 && !GameState.isUnlimitedHint) {
      hintButton.setDisable(true);
    }
    if (GameState.chatHistory.isEmpty()) {
      chatCompletionRequest = new ChatCompletionRequest().setN(1).setTemperature(0.2).setTopP(0.5)
          .setMaxTokens(140);
      Thread thread = new Thread(task);
      thread.setDaemon(true);
      thread.start();
    } else {
      chatTextArea.setText(GameState.chatHistory);
    }
  }

  /**
   * Appends a chat message to the chat text area.
   *
   * @param msg the chat message to append
   */
  private void appendChatMessage(ChatMessage msg, String msgText) {
    chatTextArea.appendText(msg.getRole() + ": " + msgText + "\n\n");
  }

  @FXML
  private void onDisableTts(ActionEvent event) {
    noTtsButton.setDisable(true);
    noTtsButton.setVisible(false);
    GameState.isTts = false;
    TextToSpeech tts = new TextToSpeech();
    tts.terminate();
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * @param msg the chat message to process
   * @return the response chat message
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  private ChatMessage runGpt(ChatMessage msg) throws ApiProxyException {
    chatCompletionRequest.addMessage(msg);
    try {
      ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
      Choice result = chatCompletionResult.getChoices().iterator().next();
      chatCompletionRequest.addMessage(result.getChatMessage());
      appendChatMessage(result.getChatMessage(),result.getChatMessage().getContent());
      Task tts = new Task() {
        @Override
        protected Object call() {
          TextToSpeech tts = new TextToSpeech();
          tts.speak(result.getChatMessage().getContent());
          Platform.runLater(() -> {
          });
          return null;
        }
      };
      Thread thread = new Thread(tts);
      thread.setDaemon(true);
      thread.start();
      return result.getChatMessage();
    } catch (ApiProxyException e) {
      showApiError(e);
      return null;
    }
  }

  /**
   * Sends a message to the GPT model.
   *
   * @param event the action event triggered by the send button
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException       if there is an I/O error
   */
  @FXML
  private void onSendMessage(ActionEvent event)
      throws ApiProxyException, IOException, InterruptedException {
    String message = inputText.getText();
    if (message.trim().isEmpty()) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    sb.append("[Player]: ");
    sb.append(message);
    // Clear input text field
    inputText.clear();
    ChatMessage msg = new ChatMessage("user", sb.toString());
    appendChatMessage(msg, message);
    // Run GPT model in a separate thread
    Task task = new Task() {
      @Override
      protected Object call() throws Exception {
        inProcess();
        runGpt(msg);
        Platform.runLater(() -> {
          finishProcess();
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
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException       if there is an I/O error
   */
  @FXML
  private void onGoBack(ActionEvent event) throws ApiProxyException, IOException {
    if (GameState.onArtRoom) {
      App.setUi(AppUi.ART_ROOM);
    } else if (GameState.onDinoRoom) {
      App.setUi(AppUi.DINO_ROOM);
    } else {
      App.setUi(AppUi.LOBBY_ROOM);
    }
  }

  private void inProcess() {
    progressBar.setVisible(true);
    inputText.setDisable(true);
    sendButton.setDisable(true);
  }

  private void finishProcess() {
    progressBar.setVisible(false);
    inputText.setDisable(false);
    sendButton.setDisable(false);
  }

  private void showApiError(ApiProxyException e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Warning");
    alert.setHeaderText("OpenAI Api Error");
    alert.setContentText(e.getMessage());
    alert.showAndWait();
  }

  @FXML
  private void askHint() {
    Task task = new Task() {
      @Override
      protected Object call() throws Exception {
        inProcess();
        try {
          runGpt(new ChatMessage("user", GptPromptEngineering.getHints()));
        } catch (ApiProxyException e) {
          showApiError(e);
        }
        Platform.runLater(() -> {
          finishProcess();
        });
        return null;
      }
    };
    if (!GameState.isUnlimitedHint) {
      GameState.remainsHint--;
      hintRemains.setText(Integer.toString(GameState.remainsHint) + "/5");
      if (GameState.remainsHint == 0) {
        hintButton.setDisable(true);
        hintRemains.setVisible(false);
      }
    }
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }
}
