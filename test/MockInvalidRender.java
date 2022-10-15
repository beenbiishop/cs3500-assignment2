import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;

/**
 * Represents a mock text view with an invalid board render.
 */
public class MockInvalidRender implements MarbleSolitaireView {

  /**
   * Constructs a new MockInvalidRender.
   *
   * @param out the Appendable to append to
   */
  public MockInvalidRender(Appendable out) {
    return;
  }

  /**
   * Mock for the toString method.
   *
   * @return an empty string
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * renderBoard() that always throws an IOException.
   *
   * @throws IOException every time the method is called
   */
  @Override
  public void renderBoard() throws IOException {
    throw new IOException("renderBoard() called");
  }

  /**
   * renderMessage() that always throws an IOException.
   *
   * @param message the message to be transmitted
   * @throws IOException every time the method is called
   */
  @Override
  public void renderMessage(String message) throws IOException {
    throw new IOException("renderMessage() called");
  }

}
