package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape {
  private int radius;
  private Point loc = new Point(0, 0);

  public SCircle(Point p, int radius) {
    this.loc = p;
    this.radius = radius;
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
    this.loc.x += x;
    this.loc.y += y;
  }

  @Override
  public Rectangle getBounds() {
    Rectangle bounds = new Rectangle();

    bounds.x = getLoc().x - this.radius;
    bounds.y = getLoc().y - this.radius;
    bounds.width = bounds.height = this.radius * 2;

    return bounds;
  }

  @Override
  public void accept(ShapeVisitor v) {
    v.visitCircle(this);
  }
}
