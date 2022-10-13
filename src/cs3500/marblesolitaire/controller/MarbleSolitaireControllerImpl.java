package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    while (!this.model.isGameOver()) {
      this.renderBoardISE();
      this.renderMessageISE(
          System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
      int cursor = 0;
      int[] move = new int[4];
      while (cursor < 4) {
        if (scanner.hasNext(Pattern.compile("^(q|quit)$", Pattern.CASE_INSENSITIVE))) {
          this.renderMessageISE("Game quit!" + System.lineSeparator() + "State of game when quit:"
              + System.lineSeparator());
          this.renderBoardISE();
          this.renderMessageISE(
              System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
          return;
        } else if (scanner.hasNextInt()) {
          move[cursor] = scanner.nextInt();
          cursor++;
        } else {
          scanner.next();
        }
      }
      try {
        this.model.move(move[0] - 1, move[1] - 1, move[2] - 1, move[3] - 1);
        this.renderMessageISE(
            "Moving from (" + move[0] + ", " + move[1] + ") to (" + move[2] + ", " + move[3] + ")"
                + System.lineSeparator());
      } catch (IllegalArgumentException e) {
        this.renderMessageISE("Invalid move. Try again." + System.lineSeparator());
        scanner.next();
      }

    }
  }

  private void renderBoardISE() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void renderMessageISE(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}