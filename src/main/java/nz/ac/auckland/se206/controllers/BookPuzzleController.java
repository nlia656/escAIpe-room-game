package nz.ac.auckland.se206.controllers;

public class BookPuzzleController {
  public static String puzzleAnswer;

  public void initialize() {
    String[] puzzleObjects = {
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
    int randomNumber = (int) (Math.random() * puzzleObjects.length);
    puzzleAnswer = puzzleObjects[randomNumber];
  }
}
