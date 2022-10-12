package cs3500.marblesolitaire.controller;

/**
 * This interface represents operations offered by the controller for the marble solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Play a game of marble solitaire. Handle input from the user via the model, and output to the
   * view.
   *
   * @throws IllegalStateException if the controller is unable to read from the input or write to
   *                               the output
   */
  void playGame() throws IllegalStateException;
}
