package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import java.io.IOException;

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
  private final Appendable appendable;

  /**
   * Constructs a new text view of the given model with a given appendable.
   *
   * @param model      the model object to be represented
   * @param appendable the appendable to append the text to
   * @throws IllegalArgumentException if the model or appendable are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
      throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("The model and appendable cannot be null");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Constructs a new text view of the given model.
   *
   * @param model the model object to be represented
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    this(model, System.out);
  }

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
            row >= firstRowCol && row <= lastRowCol && col < (this.model.getBoardSize() - 1))) {
          state.append(" ");
        }
      }
      if (row != this.model.getBoardSize() - 1) {
        state.append("\n");
      }
    }
    return state.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.appendable.append(this.toString());
    } catch (IOException e) {
      throw new IOException("Error when attempting to render board");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IOException("Error when attempting to render message");
    }
  }
}
