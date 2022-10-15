import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.StringReader;
import org.junit.Test;

/**
 * Tests the validity of the MarbleSolitaireTextView class using mocks.
 */
public class MockTextViewText {

  @Test
  public void testRenderBoardError() {
    try {
      Readable input = new StringReader("");
      StringBuilder output = new StringBuilder();
      MarbleSolitaireModel model = new MockEnglishSolitaireModel(output);
      MarbleSolitaireView view = new MockInvalidRender(output);
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
      controller.playGame();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException error) {
      assertEquals("renderBoard() called", error.getMessage());
    }
  }

  @Test
  public void testRenderMessageError() {
    try {
      Readable input = new StringReader("");
      StringBuilder output = new StringBuilder();
      MarbleSolitaireModel model = new MockEnglishSolitaireModel(output);
      MarbleSolitaireView view = new MockInvalidMessage(output);
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
      controller.playGame();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException error) {
      assertEquals("Unable to append to output", error.getMessage());
    }
  }

}
