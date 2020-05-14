package graphics.shapes;

import graphics.shapes.attributes.FontAttributes;
import java.awt.Point;
import java.awt.Rectangle;

public class SText extends Shape {
  private String text = "";
  private Point loc = new Point(0, 0);

  public SText(Point p, String text) {
    this.loc = p;
    this.text = text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the text
   */
  public String getText() {
    return this.text;
  }

  @Override
  public Point getLoc() {
    return this.loc;
  }

  @Override
  public void setLoc(Point p) {
    this.loc = p;
  }

  @Override
  public void translate(int x, int y) {
    getLoc().x += x;
    getLoc().y += x;
  }

  @Override
  public Rectangle getBounds() {
    FontAttributes fa = (FontAttributes) this.getAttributes("FontAttributes");
    Rectangle textBounds = fa.getBounds(this.text);
    Rectangle bounds = new Rectangle();

    bounds.x = this.loc.x;
    bounds.y = this.loc.y;
    bounds.width = textBounds.width;
    bounds.height = textBounds.height;

    return bounds;
  }

  @Override
  public void accept(ShapeVisitor v) {
    v.visitText(this);
  }
}
