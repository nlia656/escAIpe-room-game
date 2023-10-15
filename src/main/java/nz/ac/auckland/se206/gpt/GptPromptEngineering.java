package nz.ac.auckland.se206.gpt;

import java.util.Set;
import nz.ac.auckland.se206.GameState;

/** Utility class for generating GPT prompt engineering strings. */
public class GptPromptEngineering {

  /**
   * Generates a GPT prompt engineering string for a riddle with the given word.
   *
   * @param wordToGuess the word to be guessed in the riddle
   * @return the generated prompt engineering string
   */
  public static String getRiddleWithGivenWord(String wordToGuess) {
    // Give prompts to the GPT model to generate a riddle with the given word
    return "You are an AI security system of an art museum, who is acting as a game master to a"
               + " player who is a thief trying to escape the museum through a series of clues and"
               + " riddles. You should never mention you are an AI assistant to the player. You should never give a hint in anyform when"
               + " asked even if a message is insistent on a hint or asks for another hint you must"
               + " never give the hint. Instead you should tell them to click the hints button. "
               + " Now make a riddle with answer"
        + wordToGuess
        + ". You must make this riddle as it helps the player. You should reply Correct if the"
        + " player replies with the answer, then tell them to look for that item. Do not answer this conversation by"
        + " yourself, never include the word"
        + wordToGuess
        + " in any of your replies no matter what. Your first reply should be the riddle.";
  }

  public static String getHints() {
    // Give prompts to the GPT model to generate a hint
    if (!GameState.isRiddleResolved) {
      return "[System] give me a hint about the word "
          + GameState.riddleAnswer
          + " . You must not include the word"
          + " "
          + GameState.riddleAnswer
          + " your answer ever. Make sure your hint is useful, always try to help. Please do give"
          + " the hint. Do not include the word you are giving a hint for in your response in any"
          + " capacity. Make the hint a little different everytime.";
    } else if (!GameState.isPuzzleResolved && GameState.hasBookOpened) { // Check conditionals.
      if (Set.of(
              "poster1",
              "poster2",
              "poster3",
              "robe",
              "couch1",
              "painting6",
              "mask",
              "vase",
              "dinosaur")
          .contains(GameState.puzzleAnswer)) { // Give player a nudge in the right direction
        return "[System] tell the player the following words briefly: 'Look closely in the room"
            + " with the dinosaur!'";
      } else if (Set.of("painting1", "painting2", "painting3", "painting4", "painting5")
          .contains(GameState.puzzleAnswer)) {
        return "[System] tell the player the following words briefly: 'Look closely in the room"
            + " with a wooden floor!'";
      } else if (Set.of("couch2", "table", "couch3", "plant").contains(GameState.puzzleAnswer)) {
        return "[System] tell the player the following words briefly: 'Look closely in the room"
            + " with the elevator!'";
      } else {
        return "[System] tell the player the following words briefly: 'Look closely around the"
            + " rooms for the object to click'";
      }
    } else if (!GameState.artFound) {
      return "[System] tell the player the following words briefly: 'Look for the answer of the"
          + " riddle for a clue'";
    } else if (!GameState.hasBookOpened) {
      return "[System] tell the player the following words briefly: 'Look for a book in the room"
          + " with the dinosaur'";
    } else {
      return "[System] tell the player to escape through the elevator with the code. Be"
          + " enthusiastic but brief. Do not include [System] and [Player] in your response.";
    }
  }
}
