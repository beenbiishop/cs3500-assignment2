import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the EnglishSolitaireModel class and its methods.
 */
public class EnglishSolitaireModelTest {

  MarbleSolitaireModel noParams;
  MarbleSolitaireModel armThickness1;
  MarbleSolitaireModel armThickness3;
  MarbleSolitaireModel emptySlot0by4;
  MarbleSolitaireModel emptySlot5by3;
  MarbleSolitaireModel armThickness5EmptySlot4by4;
  MarbleSolitaireModel armThickness7EmptySlot12by9;

  @Before
  public void setUp() {
    noParams = new EnglishSolitaireModel();
    armThickness1 = new EnglishSolitaireModel(1);
    armThickness3 = new EnglishSolitaireModel(3);
    emptySlot0by4 = new EnglishSolitaireModel(0, 4);
    emptySlot5by3 = new EnglishSolitaireModel(5, 3);
    armThickness5EmptySlot4by4 = new EnglishSolitaireModel(5, 4, 4);
    armThickness7EmptySlot12by9 = new EnglishSolitaireModel(7, 12, 9);
  }

  @Test
  public void testInvalidConstructors() {
    try {
      new EnglishSolitaireModel(8, 3);
      fail("Did not throw exception when passed invalid empty cell");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid empty cell position (8, 3)", error.getMessage());
    }

    try {
      new EnglishSolitaireModel(2);
      fail("Did not throw exception when passed even arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EnglishSolitaireModel(-1);
      fail("Did not throw exception when passed negative arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EnglishSolitaireModel(2, 3, 3);
      fail("Did not throw exception when passed invalid arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Arm thickness must be a positive odd number", error.getMessage());
    }

    try {
      new EnglishSolitaireModel(3, 4, 8);
      fail("Did not throw exception when passed invalid empty cell for valid arm thickness");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid empty cell position (4, 8)", error.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(1, armThickness1.getBoardSize());
    assertEquals(7, noParams.getBoardSize());
    assertEquals(19, armThickness7EmptySlot12by9.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    Assert.assertEquals(MarbleSolitaireModelState.SlotState.Empty,
        new EnglishSolitaireModel(1).getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
        new EnglishSolitaireModel(5).getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, armThickness3.getSlotAt(2, 0));
  }

  @Test
  public void testGetSlotAtBeyondBoard() {
    try {
      armThickness5EmptySlot4by4.getSlotAt(-1, 0);
      fail("Did not throw exception for negative slot");
    } catch (IllegalArgumentException error) {
      assertEquals("Slot position is beyond board dimensions", error.getMessage());
    }

    try {
      armThickness1.getSlotAt(1, 1);
      fail("Did not throw exception for slot beyond board");
    } catch (IllegalArgumentException error) {
      assertEquals("Slot position is beyond board dimensions", error.getMessage());
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(0, armThickness1.getScore());
    assertEquals(32, noParams.getScore());
    assertEquals(104, new EnglishSolitaireModel(5).getScore());
    armThickness3.move(3, 5, 3, 3);
    armThickness3.move(3, 2, 3, 4);
    armThickness3.move(1, 3, 3, 3);
    assertEquals(29, armThickness3.getScore());
  }

  @Test
  public void testMove() {
    emptySlot0by4.move(0, 2, 0, 4);
    assertSame(emptySlot0by4.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertSame(emptySlot0by4.getSlotAt(0, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertSame(emptySlot0by4.getSlotAt(0, 4), MarbleSolitaireModelState.SlotState.Marble);

    emptySlot5by3.move(3, 3, 5, 3);
    emptySlot5by3.move(4, 5, 4, 3);
    assertSame(emptySlot5by3.getSlotAt(4, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertSame(emptySlot5by3.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertSame(emptySlot5by3.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveInvalid() {
    try {
      MarbleSolitaireModel startTop = new EnglishSolitaireModel(0, 3);
      startTop.move(-1, 3, 1, 3);
      fail("Did not throw exception for invalid from row");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (-1, 3) to (1, 3)", error.getMessage());
    }

    try {
      MarbleSolitaireModel startBottom = new EnglishSolitaireModel(6, 3);
      startBottom.move(5, 3, 7, 3);
      fail("Did not throw exception for invalid to row");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (5, 3) to (7, 3)", error.getMessage());
    }

    try {
      noParams.move(3, 3, 3, 5);
      fail("Did not throw exception for from slot that is not a marble");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 3) to (3, 5)", error.getMessage());
    }

    try {
      noParams.move(3, 0, 3, 2);
      fail("Did not throw an exception for to slot that is not empty");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 0) to (3, 2)", error.getMessage());
    }

    try {
      noParams.move(3, 2, 3, 3);
      fail("Did not throw an exception for move that is not 2 spaces in one direction");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 2) to (3, 3)", error.getMessage());
    }

    try {
      noParams.move(3, 5, 3, 3);
      noParams.move(3, 3, 3, 5);
      fail("Did not throw an exception for move that does not have a marble in between");
    } catch (IllegalArgumentException error) {
      assertEquals("Invalid move from (3, 3) to (3, 5)", error.getMessage());
    }
  }

  @Test
  public void testIsGameOver() {
    assertFalse(noParams.isGameOver());
    assertFalse(armThickness5EmptySlot4by4.isGameOver());
    assertTrue(new EnglishSolitaireModel(1).isGameOver());

    noParams.move(3, 1, 3, 3);
    noParams.move(5, 2, 3, 2);
    noParams.move(4, 0, 4, 2);
    noParams.move(4, 3, 4, 1);
    noParams.move(4, 5, 4, 3);
    noParams.move(6, 4, 4, 4);
    noParams.move(3, 4, 5, 4);
    noParams.move(6, 2, 6, 4);
    noParams.move(6, 4, 4, 4);
    noParams.move(2, 2, 4, 2);
    noParams.move(0, 2, 2, 2);
    noParams.move(1, 4, 3, 4);
    noParams.move(3, 4, 5, 4);
    noParams.move(5, 4, 5, 2);
    noParams.move(5, 2, 3, 2);
    noParams.move(3, 2, 1, 2);
    noParams.move(2, 0, 4, 0);
    noParams.move(4, 0, 4, 2);
    noParams.move(4, 2, 4, 4);
    noParams.move(2, 6, 2, 4);
    noParams.move(2, 3, 2, 5);
    noParams.move(4, 6, 2, 6);
    noParams.move(2, 6, 2, 4);
    noParams.move(0, 4, 0, 2);
    noParams.move(0, 2, 2, 2);
    noParams.move(2, 1, 2, 3);
    noParams.move(2, 3, 2, 5);
    noParams.move(2, 5, 4, 5);
    noParams.move(4, 5, 4, 3);
    noParams.move(4, 3, 2, 3);
    noParams.move(1, 3, 3, 3);
    assertTrue(noParams.isGameOver());
  }
}