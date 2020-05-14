package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class ShapesController extends Controller {
  private Object model;
  private ShapesView sview;
  private Point p = new Point(0, 0);

  public ShapesController(Object newModel) {
    super(newModel);
    this.model = newModel;
  }

  /**
   * @param sview the sview to set
   */
  public void setSview(ShapesView sview) {
    this.sview = sview;
  }

  /**
   * @return the sview
   */
  public ShapesView getSview() {
    return sview;
  }

  @Override
  public void setModel(Object model) {
    this.model = (SCollection) model;
  }

  @Override
  public Object getModel() {
    return this.model;
  }

  public void defaultShapesController() {
    this.model = super.getModel();
  }

  public Shape getTarget() {
    Iterator<Shape> it = ((SCollection) this.model).iterator();
    System.out.println(p);
    while (it.hasNext()) if (it.next().getBounds().contains(p)) return it.next();
    return null;
  }

  public void translateSelected(int x, int y) {
    Iterator<Shape> it = ((SCollection) this.model).iterator();
      while (it.hasNext()) {
        SelectionAttributes sa = ((SelectionAttributes)it.next().getAttributes("SelectionAttributes"));
        if (sa.isSelected()) it.next().translate(x, y);
      }
  }

  public void mousePressed(MouseEvent e) {
    System.out.println("mousePressed");
    //p = e.getPoint();
    //((SelectionAttributes) getTarget().getAttributes("SelectionAttributes")).toggleSelection();
    //this.model.accept(sd);
  }

  public void mouseReleased(MouseEvent e) {
    
  }

  public void mouseClicked(MouseEvent e) {
    p = e.getPoint();
    //if (getTarget() != null) {
      //((SelectionAttributes) getTarget().getAttributes("SelectionAttributes")).toggleSelection();
      System.out.println("mouseClicked");
      Iterator<Shape> it = ((SCollection) this.model).iterator();
      while (it.hasNext()) {
        System.out.println(it.next().toString());
      }
    //}
  }

  public void mouseEntered(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}

  public void mouseMoved(MouseEvent evt) {}

  public void mouseDragged(MouseEvent evt) {
   // System.out.println(((SCollection)this.model).getBounds());
    //((SCollection) this.model).translate(evt.getX(), evt.getY());
    System.out.println("Mouse dragged");
    translateSelected(evt.getX(), evt.getY());
  }

  public void keyTyped(KeyEvent evt) {}

  public void keyPressed(KeyEvent evt) {}

  public void keyReleased(KeyEvent evt) {}
}
