import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

public class MarbleSolitaireControllerTest {

  Readable input;
  Appendable output;
  EnglishSolitaireModel model;
  MarbleSolitaireView view;
  MarbleSolitaireController controller;

  @Before
  public void setUp() {
    this.input = new StringReader("Quit");
    this.output = new StringBuilder();
    this.model = new EnglishSolitaireModel();
    this.view = new MarbleSolitaireTextView(this.model, this.output);
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
  }

  @Test
  public void testPlayGameStart() {
    this.controller.playGame();
    String expectedString =
        "    O O O\n" + "    O O O\n" + "O O O O O O O\n" + "O O O _ O O O\n" + "O O O O O O O\n"
            + "    O O O\n" + "    O O O\n" + "Score: 32\n";
    String[] expected = expectedString.split("\n");
    String[] output = this.output.toString().split("\n");
    int length = 8;
    while (length > 0) {
      assertEquals(expected[length - 1], output[length - 1]);
      length--;
    }
  }

  @Test
  public void testPlayGameEnd() {
    this.controller.playGame();
    String expectedString =
        "Game quit!\n" + "State of game when quit:\n" + "    O O O\n" + "    O O O\n"
            + "O O O O O O O\n" + "O O O _ O O O\n" + "O O O O O O O\n" + "    O O O\n"
            + "    O O O\n" + "Score: 32";
    String[] expected = expectedString.split("\n");
    String[] output = this.output.toString().split("\n");
    int length = 18;
    while (length > 8) {
      assertEquals(expected[length - 9], output[length - 1]);
      length--;
    }
  }

  @Test
  public void testConstructorErrors() {
    try {
      this.model = null;
      this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException error) {
      assertEquals("Model, view, and input cannot be null", error.getMessage());
    }

    try {
      this.view = null;
      this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException error) {
      assertEquals("Model, view, and input cannot be null", error.getMessage());
    }

    try {
      this.input = null;
      this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException error) {
      assertEquals("Model, view, and input cannot be null", error.getMessage());
    }
  }

  @Test
  public void testQuitAfterMove() {
    this.input = new StringReader("4 6 4 4 Quit");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
    controller.playGame();

    assertEquals(
        "    O O O\n" + "    O O O\n" + "O O O O O O O\n" + "O O O _ O O O\n" + "O O O O O O O\n"
            + "    O O O\n" + "    O O O\n" + "Score: 32\n" + "    O O O\n" + "    O O O\n"
            + "O O O O O O O\n" + "O O O O _ _ O\n" + "O O O O O O O\n" + "    O O O\n"
            + "    O O O\n" + "Score: 31\n" + "Game quit!\n" + "State of game when quit:\n"
            + "    O O O\n" + "    O O O\n" + "O O O O O O O\n" + "O O O O _ _ O\n"
            + "O O O O O O O\n" + "    O O O\n" + "    O O O\n" + "Score: 31\n",
        this.output.toString());
  }

  @Test
  public void testQuitCapitalization() {
    String[] quits = new String[]{"Quit", "QUIT", "qUIT", "quiT", "qUiT", "quIT", "quIt", "quiT",
        "Q", "q"};
    for (String quit : quits) {
      this.input = new StringReader(quit);
      this.output = new StringBuilder();
      this.model = new EnglishSolitaireModel();
      this.view = new MarbleSolitaireTextView(this.model, this.output);
      this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
      this.controller.playGame();

      assertEquals(
          "    O O O\n" + "    O O O\n" + "O O O O O O O\n" + "O O O _ O O O\n" + "O O O O O O O\n"
              + "    O O O\n" + "    O O O\n" + "Score: 32\n" + "Game quit!\n"
              + "State of game when quit:\n" + "    O O O\n" + "    O O O\n" + "O O O O O O O\n"
              + "O O O _ O O O\n" + "O O O O O O O\n" + "    O O O\n" + "    O O O\n"
              + "Score: 32\n", this.output.toString());
    }
  }

  @Test
  public void testWonGame() {
    this.input = new StringReader("6 4 4 4 5 2 5 4 4 4 6 4 7 4 5 4 7 3 5 3 5 4 5 2 5 6 5 4 "
        + "7 5 5 5 5 4 5 6 5 1 5 3 4 3 6 3 3 2 5 2 3 1 5 1 5 1 5 3 6 3 4 3 5 7 5 5 4 "
        + "5 6 5 3 7 5 7 3 6 5 6 5 7 5 5 6 5 4 5 3 4 3 2 1 3 3 3 4 3 2 3 1 5 1 3 1 3 3 3 3 2 "
        + "3 4 2 4 4 4 4 4 4 6 2 5 4 5 4 6 4 4");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
    this.controller.playGame();

    assertEquals(true, this.output.toString().contains(
        "Game over!\n" + "    _ _ _\n" + "    _ _ _\n" + "_ _ _ _ _ _ _\n" + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n" + "    _ _ _\n" + "    _ _ _\n" + "Score: 1"));
  }

}