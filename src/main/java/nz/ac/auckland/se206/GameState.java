package nz.ac.auckland.se206;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;

/**
 * Represents the state of the game.
 */
public class GameState {

  public static final String[] rooms = {"lobby", "dino"};
  public static final String[] lobbyAnswers = {"couch", "table", "plant"};
  public static final String[] dinoAnswers = {"dinosaur", "vase", "book", "mask", "painting",
      "poster", "robe", "couch"};
  public static final String[] artroomRiddleAnswers = {"dagger","book","armour","crown","vase","pillar","sword"};
  //public static final String[] artAnswers = {"painting", "vase", "book", "mask", "dinosaur"};
  /**
   * Indicates whether the riddle has been resolved.
   */
  public static String room;
  public static String answer;


  public static boolean isRiddleResolved = false;
  public static String artroomRiddleAnswer;

  /** Indicates whether the puzzle has been resolved. */
  public static boolean isPuzzleResolved = false;

  public static int timeLeft;
  public static boolean isPaused = false;
  public static int timeLimit;
  public static boolean isUnlimitedHint;
  public static int remainsHint;
  public static boolean isTts;
  public static ChatCompletionRequest chatCompletionRequest;
  public static String chatHistory="";
  public static boolean onArtRoom = false;
  public static boolean onDinoRoom = false;
  public static boolean onLobbyRoom = false;
  public static boolean onPaintPuzzle = false;
  public static boolean isArtComplete = false;
  public static int getRandom(int range) {
    return (int) (Math.random() * range);
  }

  public static void initial() {
    //randomize the answer
    isUnlimitedHint = false;
    remainsHint=0;
    timeLeft=timeLimit;
    artroomRiddleAnswer=artroomRiddleAnswers[getRandom(artroomRiddleAnswers.length-1)];
    isTts = false;
    room = rooms[getRandom(rooms.length-1)];
    chatCompletionRequest = new ChatCompletionRequest();
    chatHistory = "";
    remainsHint = 0;
    timeLeft = timeLimit;
    isTts = false;
  }
}
