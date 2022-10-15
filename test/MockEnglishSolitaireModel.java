import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;

public class MockEnglishSolitaireModel implements MarbleSolitaireModel {

  private Appendable output;

  /**
   * Constructs a new MockEnglishSolitaireModel.
   *
   * @param output the Appendable to append to
   */
  public MockEnglishSolitaireModel(Appendable output) {
    this.output = output;
  }

  /**
   * Mock for the move method.
   *
   * @param fromRow fake row to move from
   * @param fromCol fake column to move from
   * @param toRow   fake row to move to
   * @param toCol   fake column to move to
   * @throws IllegalArgumentException every time the method is called
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    throw new IllegalArgumentException("Invalid move");
  }

  /**
   * Mock for the getGameState method.
   *
   * @return false every time the method is called
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Mock for the getScore method.
   *
   * @return 0 every time the method is called
   */
  @Override
  public int getBoardSize() {
    return 0;
  }

  /**
   * Mock for the getGameState method.
   *
   * @param row fake row to get the state of
   * @param col fake column to get the state of
   * @return IllegalArgumentException every time the method is called
   * @throws IllegalArgumentException every time the method is called
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    try {
      this.output.append("getSlotAt() called");
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to append to output");
    }
    return null;
  }

  /**
   * Mock for the getScore method.
   *
   * @return 0 every time the method is called
   */
  @Override
  public int getScore() {
    return 0;
  }
}
