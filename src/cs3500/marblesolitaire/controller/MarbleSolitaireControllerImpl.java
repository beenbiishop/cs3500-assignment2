package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;
import java.util.Scanner;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable input;

  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Model, view, and input cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    // Create a new scanner to read the input
    Scanner scanner = new Scanner(this.input);
    // Try rendering the input
    try {
      // While the game is not over, render the board and ask for input
      while (!this.model.isGameOver()) {
        // Render the board and the current score
        this.view.renderBoard();
        this.view.renderMessage(
            System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
        // Initialize cursor and move variables
        int cursor = 0;
        int[] move = new int[4];
        // While there are less than 4 valid inputs, ask for more inputs
        while (cursor < 4) {
          // Check if scanner has another input token
          if (scanner.hasNext()) {
            // Store the next input token as a variable
            String next = scanner.next();
            // Check if the next input token is a "q" or "quit", if so quit the game
            if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
              // Try to render the quit message
              try {
                this.view.renderMessage(
                    "Game quit!" + System.lineSeparator() + "State of game when quit:"
                        + System.lineSeparator());
                this.view.renderBoard();
                this.view.renderMessage(System.lineSeparator() + "Score: " + this.model.getScore()
                    + System.lineSeparator());
              } catch (IOException e) {
                throw new IllegalStateException(e.getMessage());
              }
              return;
            } else if (stringIsInts(next)) { // Check if the next input token is all integers
              String[] split = next.split(" ");
              for (String s : split) {
                move[cursor] = Integer.parseInt(s);
                cursor++;
              }
            } else {
              throw new IllegalStateException("Input is invalid");
            }
          } else {
            throw new IllegalStateException("Input is invalid");
          }
        }
        try {
          this.model.move(move[0] - 1, move[1] - 1, move[2] - 1, move[3] - 1);
          this.view.renderMessage("Game over!" + System.lineSeparator());
          this.view.renderMessage(
              System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
        } catch (IOException e) {
          throw new IllegalStateException(e.getMessage());
        }
      }
      this.view.renderMessage("Game over!");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Checks if the given string contains valid integers seperated by spaces.
   *
   * @param s the string to be checked
   * @return true if the string contains only valid integers, false otherwise
   */
  private boolean stringIsInts(String s) {
    String[] split = s.split(" ");
    for (String str : split) {
      try {
        Integer.parseInt(s);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }
}