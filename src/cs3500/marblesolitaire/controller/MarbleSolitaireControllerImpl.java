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
    Scanner scanner = new Scanner(this.input);
    try {
      while (!this.model.isGameOver()) {
        this.view.renderBoard();
        this.view.renderMessage(
            System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
        int cursor = 0;
        int[] move = new int[4];
        while (cursor < 4) {
          if (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
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
            } else if (stringIsInts(next)) {
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
