import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw03TypeChecks {

  /**
   * Given main method to test the compilation of code.
   *
   * @param args the arguments taken in by the main method
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helper(new EnglishSolitaireModel(), rd, ap);
    helper(new EnglishSolitaireModel(3, 3), rd, ap);
  }

  /**
   * Helper method to test the compilation of code.
   *
   * @param model the model to be played
   * @param rd    the input to be read
   * @param ap    the output to be written
   */
  private static void helper(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model,
      Readable rd, Appendable ap) {
    new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(model,
        new cs3500.marblesolitaire.view.MarbleSolitaireTextView(model, ap), rd);
  }

}
