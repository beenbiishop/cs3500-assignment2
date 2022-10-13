package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents an implemented controller for a Marble Solitaire game.
 *
 * <p>This controller takes in a MarbleSolitaireModel and a MarbleSolitaireView and allows the user
 * to play the game by reading their input and displaying changes to the game state.</p>
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable input;

  /**
   * Constructs a new controller for the given model, view, and input.
   *
   * @param model the model to be played
   * @param view  the view to be displayed
   * @param input the input to be read
   * @throws IllegalArgumentException if the model, view, or input are null
   */
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
        if (scanner.hasNextInt()) {
          int theInt = scanner.nextInt();
          if (theInt > 0) {
            move[cursor] = theInt;
            cursor++;
          }
        } else if (scanner.hasNext(Pattern.compile("q", Pattern.CASE_INSENSITIVE))
            || scanner.hasNext(Pattern.compile("quit", Pattern.CASE_INSENSITIVE))) {
          this.renderMessageISE("Game quit!" + System.lineSeparator() + "State of game when quit:"
              + System.lineSeparator());
          this.renderBoardISE();
          this.renderMessageISE(
              System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
          return;
        } else {
          scanner.next();
          System.out.println("here");
        }
      }
      try {
        this.model.move(move[0] - 1, move[1] - 1, move[2] - 1, move[3] - 1);
      } catch (IllegalArgumentException e) {
        this.renderMessageISE("Invalid move. Try again." + System.lineSeparator());
      }
    }
    if (this.model.isGameOver()) {
      this.renderMessageISE(System.lineSeparator() + "Game over!" + System.lineSeparator());
      this.renderBoardISE();
      this.renderMessageISE(
          System.lineSeparator() + "Score: " + this.model.getScore() + System.lineSeparator());
    }
  }

  /**
   * Renders the board to this view's appendable.
   *
   * @throws IllegalStateException if the appendable throws an IOException
   */
  private void renderBoardISE() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Renders the given message to this view's appendable.
   *
   * @param message the message to append
   * @throws IllegalStateException if the appendable throws an IOException
   */
  private void renderMessageISE(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}