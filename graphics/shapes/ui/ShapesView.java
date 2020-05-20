package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;
import java.awt.Graphics;

@SuppressWarnings ("serial")
public class ShapesView extends View {

  public ShapesView(Object model) {
    super(model);
  }

  public Controller defaultController(Object model) {
    return new ShapesController(model);
  }

  public void invalidate() {
    this.paintImmediately(this.getBounds());
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    SCollection model = (SCollection) this.getModel();

    if (model == null) {
      return;
    }
    ShapeDraftman draftman = new ShapeDraftman(g);
    model.accept(draftman);

    this.requestFocus();
  }
}
