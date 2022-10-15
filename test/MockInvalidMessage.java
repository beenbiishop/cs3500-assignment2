import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;

/**
 * Represents a mock text view with an invalid render message.
 */
public class MockInvalidMessage implements MarbleSolitaireView {

  private final Appendable out;

  /**
   * Constructs a new MockInvalidMessage.
   *
   * @param out the Appendable to append to
   */
  public MockInvalidMessage(Appendable out) {
    this.out = out;
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
   * @throws IOException if the Appendable throws an IOException
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      this.out.append("renderBoard() called");
    } catch (IOException e) {
      throw new IOException("Unable to append to output");
    }
  }

  /**
   * renderMessage() that always throws an IOException.
   *
   * @param message the message to be transmitted
   * @throws IOException if the Appendable throws an IOException
   */
  @Override
  public void renderMessage(String message) throws IOException {
    throw new IOException("Unable to append to output");
  }
}
