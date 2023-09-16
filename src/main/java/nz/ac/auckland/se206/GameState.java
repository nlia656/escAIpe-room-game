package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved = false;

  /** Indicates whether the key has been found. */
  public static boolean isKeyFound = false;
  public static int timeLeft;
  public static boolean isPaused = false;

  public static int timeLimit;

  public static boolean isUnlimitedHint;
  public static int remainsHint;

  public static boolean isTts;
  public static boolean isUnlocked;
  public static String passcode;
}
