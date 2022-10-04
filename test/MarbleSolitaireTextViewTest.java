import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the MarbleSolitaireTextView class and its methods.
 */
public class MarbleSolitaireTextViewTest {

  MarbleSolitaireModel armThickness1;

  MarbleSolitaireModel armThickness3;
  MarbleSolitaireModel armThickness5;
  MarbleSolitaireModel wonGame;
  MarbleSolitaireView viewArmThickness1;
  MarbleSolitaireView viewArmThickness3;
  MarbleSolitaireView viewArmThickness5;
  MarbleSolitaireView viewWonGame;

  @Before
  public void setUp() {
    armThickness1 = new EnglishSolitaireModel(1);
    armThickness3 = new EnglishSolitaireModel();
    armThickness5 = new EnglishSolitaireModel(5);
    wonGame = new EnglishSolitaireModel();

    wonGame.move(3, 1, 3, 3);
    wonGame.move(5, 2, 3, 2);
    wonGame.move(4, 0, 4, 2);
    wonGame.move(4, 3, 4, 1);
    wonGame.move(4, 5, 4, 3);
    wonGame.move(6, 4, 4, 4);
    wonGame.move(3, 4, 5, 4);
    wonGame.move(6, 2, 6, 4);
    wonGame.move(6, 4, 4, 4);
    wonGame.move(2, 2, 4, 2);
    wonGame.move(0, 2, 2, 2);
    wonGame.move(1, 4, 3, 4);
    wonGame.move(3, 4, 5, 4);
    wonGame.move(5, 4, 5, 2);
    wonGame.move(5, 2, 3, 2);
    wonGame.move(3, 2, 1, 2);
    wonGame.move(2, 0, 4, 0);
    wonGame.move(4, 0, 4, 2);
    wonGame.move(4, 2, 4, 4);
    wonGame.move(2, 6, 2, 4);
    wonGame.move(2, 3, 2, 5);
    wonGame.move(4, 6, 2, 6);
    wonGame.move(2, 6, 2, 4);
    wonGame.move(0, 4, 0, 2);
    wonGame.move(0, 2, 2, 2);
    wonGame.move(2, 1, 2, 3);
    wonGame.move(2, 3, 2, 5);
    wonGame.move(2, 5, 4, 5);
    wonGame.move(4, 5, 4, 3);
    wonGame.move(4, 3, 2, 3);
    wonGame.move(1, 3, 3, 3);

    viewArmThickness1 = new MarbleSolitaireTextView(armThickness1);
    viewArmThickness3 = new MarbleSolitaireTextView(armThickness3);
    viewArmThickness5 = new MarbleSolitaireTextView(armThickness5);
    viewWonGame = new MarbleSolitaireTextView(wonGame);
  }

  @Test
  public void testInvalidConstructor() {
    try {
      new MarbleSolitaireTextView(null);
      fail("Did not throw exception when passed invalid empty cell");
    } catch (IllegalArgumentException error) {
      assertEquals("The model cannot be null", error.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals("_", viewArmThickness1.toString());
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", viewArmThickness3.toString());
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", viewArmThickness5.toString());
    assertEquals(
        "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _", viewWonGame.toString());
  }
}