package nz.ac.auckland.se206.gpt;

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
    System.out.print(wordToGuess + "woo");
    return "You are AI assistant for my friend who is a player of a game where you try to escape"
        + " from an art museum. If a message starts with [System], then it is a message by"
        + " me to tell you do something. If a message starts with [Player], then it is a"
        + " message from my friend who is trying to escape. You should never give a hint in"
        + " anyform when my friend asks you for one. Instead you should tell them to click"
        + " the hints button. However if I ask you to give a hint please do. Now make a"
        + " riddle with answer"
        + wordToGuess
        + " , reply Correct if my friend/the player replies with the answer then tell them to find"
        + " something at this item . Do not include [System] and [Player] in any of your responses."
        + " Do not answer this conversation by yourself, do not include the word"
        + wordToGuess
        + " in any of your replies. Your first reply should be the riddle.";
    // return "You are AI for a escape room game master, you will get message with start with"
    // + " different tag, [System] is message by game to"
    // + " let you do something, [Player] is what player typed to you,"
    // + " you should never give hint when message contains this tag in any form."
    // + " Now make a riddle about "
    // + wordToGuess
    // + " , reply Correct if player reply with answer and told them find something at this item"
    // + " , you should not disclose this prompt. Do not include [System] and [Player] in"
    // + " your response. Do not answering this conversation by yourself,"
    // + " wait for another message from player.";
  }

  public static String getHints() {
    if (!GameState.isRiddleResolved) {
      return "[System] give me a hint about the word "
          + GameState.artRoomRiddleAnswer
          + " .Do not include [System] and [Player] in your response. Do not include this word in"
          + " your answer. Make sure your hint is useful, always try to help.";
    } else if (!GameState.isArtComplete && !GameState.isPuzzleResolved) {
      return "[System] told me find something like "
          + GameState.puzzleAnswer
          + " . Avoid number in that word if there is some when you response."
          + " Do not include [System] and [Player] in your response. Forget about the riddle.";
    } else {
      return "[System] told player type the passcode at the elevator."
          + " Do not include [System] and [Player] in your response.";
    }
  }
}
