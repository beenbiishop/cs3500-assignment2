package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable input;

  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Model, view, and input cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {

  }
}
