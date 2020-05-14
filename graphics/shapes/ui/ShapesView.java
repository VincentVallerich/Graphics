package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;
import java.awt.Graphics;

public class ShapesView extends View {

  public ShapesView(Object model) {
    super(model);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    SCollection model = (SCollection) this.getModel();
    if (model == null) return;

    ShapesDraftman draftman = new ShapesDraftman(g);
    model.accept(draftman);
  }

  @Override
  public Controller defaultController(Object model) {
    return new ShapesController(this.getModel());
  }
}
