package nz.ac.auckland.se206.gpt;

import nz.ac.auckland.se206.GameState;

/**
 * Utility class for generating GPT prompt engineering strings.
 */
public class GptPromptEngineering {

  /**
   * Generates a GPT prompt engineering string for a riddle with the given word.
   *
   * @param wordToGuess the word to be guessed in the riddle
   * @return the generated prompt engineering string
   */
  public static String getRiddleWithGivenWord(String wordToGuess) {
    return "You are a AI for a escape room game master, you will get message with start with"
        + " different tag, [System] is message by game told you what player did or"
        + " let you do something, [Player] is what player typed to you,"
        + " you should not give hint to Player without [System] let you to do."
        + " response you generated will give to player directly. Now give me a riddle about "
        + GameState.answer +
        " and tell player this is located in "+GameState.room+ " room";
  }
}
