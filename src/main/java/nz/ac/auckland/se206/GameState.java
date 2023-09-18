package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {

  public static final String[] rooms = {"lobby", "dino"};
  public static final String[] lobbyAnswers = {"couch", "table", "plant"};
  public static final String[] dinoAnswers = {
    "dinosaur", "vase", "book", "mask", "painting", "poster", "robe", "couch"
  };

  // public static final String[] artAnswers = {"painting", "vase", "book", "mask", "dinosaur"};
  /** Indicates whether the riddle has been resolved. */
  public static String room;

  public static String answer;

  public static boolean isRiddleResolved = false;
  public static boolean isPuzzleResolved = false;

  /** Indicates whether the key has been found. */
  public static boolean isKeyFound = false;

  public static int timeLeft;
  public static boolean isPaused = false;

  public static int timeLimit;

  public static boolean isUnlimitedHint;
  public static int remainsHint;

  public static boolean isTts;

  public static int getRandom(int range) {
    return (int) (Math.random() * range);
  }

  public static void initial() {
    // randomize the answer
    isUnlimitedHint = false;
    remainsHint = 0;
    timeLeft = timeLimit;

    isTts = false;
    room = rooms[getRandom(rooms.length - 1)];
    switch (room) {
      case "lobby" -> answer = lobbyAnswers[getRandom(lobbyAnswers.length - 1)];
      case "dino" -> answer = dinoAnswers[getRandom(dinoAnswers.length - 1)];
      default -> answer = "couch";
    }
  }
}