package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a textual view of a Marble Solitaire game.
 *
 * <p>The class takes in a MarbleSolitaireModel and generates a textual representation of the model
 * based on the slot state of each slot on the board. Marbles are represented as "O", empty slots
 * are represented as "_", and invalid slots are represented as " ". At the end of each row, if only
 * invalid slots remain, a new line is started.</p>
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model;

  /**
   * Constructs a new text view of the given model.
   *
   * @param model the model object to be represented
   * @throws IllegalArgumentException if the given model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    this.model = model;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, _ or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String toString() {
    StringBuilder state = new StringBuilder();
    for (int row = 0; row < this.model.getBoardSize(); row++) {
      for (int col = 0; col < this.model.getBoardSize(); col++) {
        int firstRowCol = (this.model.getBoardSize() - 1) / 3;
        int lastRowCol = (2 * (this.model.getBoardSize() - 1)) / 3;
        switch (this.model.getSlotAt(row, col)) {
          case Marble:
            state.append("O");
            break;
          case Invalid:
            if ((row < firstRowCol && col < lastRowCol) || (row > lastRowCol && col < lastRowCol)) {
              state.append(" ");
            }
            break;
          case Empty:
            state.append("_");
            break;
          default:
            throw new IllegalArgumentException("Invalid slot state");
        }
        if ((row < firstRowCol && col < lastRowCol) || (row > lastRowCol && col < lastRowCol) || (
            row >= firstRowCol && row <= lastRowCol && col < (this.model.getBoardSize()
                - 1))) {
          state.append(" ");
        }
      }
      if (row != this.model.getBoardSize() - 1) {
        state.append("\n");
      }
    }
    return state.toString();
  }
}
