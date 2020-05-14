package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {
  private Rectangle rect = new Rectangle();
  private Point loc = new Point(0,0);

  public SRectangle(Point p, int width, int height) {
    this.rect.x = p.x;
    this.rect.y = p.y;
    this.rect.width = width;
    this.rect.height = height;
  }

  /**
   * @return the rect
   */
  public Rectangle getRect() {
    return rect;
  }

  @Override
  public Point getLoc() {
    return loc;
  }

  /**
   * @param the Point p
   */
  public void setLoc(Point p) {
    this.rect.x = p.x;
    this.rect.y = p.y;
  }

  public void translate(int x, int y) {
    this.loc.x += x;
    this.loc.y += y;
  }

  /**
   * @return th rect as bounds
   */
  public Rectangle getBounds() {
    return this.rect;
  }

  public void accept(ShapeVisitor v) {
    v.visitRectangle(this);
  }
}
