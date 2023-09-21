package nz.ac.auckland.se206;

import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;

/** Represents the state of the game. */
public class GameState {

  public static final String[] rooms = {"lobby", "dino"};
  public static final String[] lobbyAnswers = {"couch", "table", "plant"};
  public static final String[] dinoAnswers = {
    "dinosaur", "vase", "book", "mask", "painting", "poster", "robe", "couch"
  };
  public static final String[] artRoomRiddleAnswers = {
    "dagger", "book", "armour", "crown", "vase", "pillar", "sword"
  };
  public static final String[] puzzleObjects = {
    "couch1",
    "dinosaur",
    "mask",
    "robe",
    "poster3",
    "poster2",
    "poster1",
    "vase",
    "painting1",
    "painting2",
    "painting3",
    "painting4",
    "painting5",
    "painting6",
    "couch2",
    "couch3",
    "plant",
    "table"
  };

  public static String room;

  public static String puzzleAnswer;
  public static boolean isRiddleResolved = false;
  public static String artRoomRiddleAnswer;

  /** Indicates whether the puzzle has been resolved. */
  public static boolean isPuzzleResolved = false;

  public static int timeLeft;
  public static boolean isPaused = false;
  public static int timeLimit;
  public static boolean isUnlimitedHint;
  public static int remainsHint;
  public static boolean isTts;
  public static boolean isUnlocked;
  public static String passcode;
  public static ChatCompletionRequest chatCompletionRequest;
  public static String lastMsg = "";
  public static boolean onArtRoom = false;
  public static boolean onDinoRoom = false;
  public static boolean onLobbyRoom = false;
  public static boolean onPaintPuzzle = false;
  public static boolean isArtComplete = false;
  public static boolean isGameComplete = false;
  public static int randomCode;
  public static String riddleCode;
  public static boolean isPopShowed;
  public static boolean firstTimeCode = false;
  public static boolean secondTimeCode = false;
  public static boolean timeOver = false;
  private static double randNumber;
  public static boolean isRiddleCodeGiven = false;

  public static int getRandom(int range) {
    return (int) (Math.random() * range);
  }

  public static void initial() {
    // randomize the answer
    isUnlimitedHint = false;
    remainsHint = 0;
    timeLeft = timeLimit;
    isGameComplete = false;
    puzzleAnswer = puzzleObjects[getRandom(puzzleObjects.length - 1)];
    artRoomRiddleAnswer = artRoomRiddleAnswers[getRandom(artRoomRiddleAnswers.length - 1)];
    isTts = false;
    room = rooms[getRandom(rooms.length - 1)];
    chatCompletionRequest = new ChatCompletionRequest();
    lastMsg = "";
    remainsHint = 0;
    timeLeft = timeLimit;
    isTts = false;
    isPopShowed = false;
    randNumber = Math.random();
    randomCode = (int) (randNumber * 100);
    if (randomCode < 10) {
      riddleCode = "0" + Integer.toString(randomCode);
    } else {
      riddleCode = Integer.toString(randomCode);
    }
    System.out.println(artRoomRiddleAnswer);
    System.out.println(riddleCode);
  }
}
