package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {
  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved = false;

  /** Indicates whether the puzzle has been resolved. */
  public static boolean isPuzzleResolved = false;

  public static int timeLeft;
  public static boolean isPaused = false;
  public static int timeLimit;
  public static boolean isUnlimitedHint;
  public static int remainsHint;
  public static boolean isTts;
  public static boolean onArtRoom = false;
  public static boolean onDinoRoom = false;
  public static boolean onLobbyRoom = false;
  public static boolean onPaintPuzzle = false;
  public static boolean isArtComplete = false;
}
