package nz.ac.auckland.se206;

/**
 * Represents the state of the game.
 */
public class GameState {

  public static final String[] artRoomRiddleAnswers = {
      "dagger", "book", "armour", "crown", "vase1", "pillar", "sword", "vase2"
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

  public static String puzzleAnswer;
  public static boolean isRiddleResolved;
  public static String riddleAnswer;

  /**
   * Indicates whether the puzzle has been resolved.
   */
  public static boolean isPuzzleResolved;

  public static String timeLeft;
  public static boolean isPaused;
  public static int timeLimit;
  public static boolean isUnlimitedHint;
  public static int remainsHint;
  public static boolean isTts;
  public static boolean isUnlocked;
  public static boolean isHard;
  public static String lastMsg = "";
  public static boolean onArtRoom;
  public static boolean onDinoRoom;
  public static boolean onLobbyRoom;
  public static boolean isGameComplete;
  public static int randomCode;
  public static String riddleCode;
  public static boolean firstTimeCode;
  public static boolean secondTimeCode;
  public static boolean isRiddleCodeGiven;
  public static boolean isPuzzleCodeGiven;
  public static boolean artFound;
  public static boolean hasBookOpened;
  private static double randNumber;

  public static int getRandom(int range) {
    return (int) (Math.random() * range);
  }

  public static void initial() {
    // Reset all the booleans to their original states so that the game runs from the beginning each
    // time.
    isPaused = false;
    isUnlocked = false;
    onArtRoom = false;
    onDinoRoom = false;
    onLobbyRoom = false;
    hasBookOpened = false;
    isUnlimitedHint = false;
    remainsHint = 0;
    timeLeft = "";
    isGameComplete = false;
    firstTimeCode = false;
    secondTimeCode = false;
    isRiddleCodeGiven = false;
    isPuzzleCodeGiven = false;
    isHard = false;
    isRiddleResolved = false;
    isPuzzleResolved = false;
    isTts = false;
    puzzleAnswer = puzzleObjects[getRandom(puzzleObjects.length - 1)]; // randomize the answer
    riddleAnswer = artRoomRiddleAnswers[getRandom(artRoomRiddleAnswers.length - 1)];
    lastMsg = "";
    remainsHint = 0;
    randNumber = Math.random();
    randomCode = (int) (randNumber * 100);
    if (randomCode < 10) {
      riddleCode = "0" + randomCode;
    } else {
      riddleCode = Integer.toString(randomCode);
    }
    System.out.println(riddleAnswer);
    System.out.println(riddleCode);
    System.out.println(puzzleAnswer);
    System.out.println(randomCode);
  }
}
