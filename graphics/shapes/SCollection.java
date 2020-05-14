package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {
  private ArrayList<Shape> list = new ArrayList<Shape>();
  private ShapeVisitor v;
  private Point loc = new Point(0, 0);

  public Iterator<Shape> iterator() {
    return this.list.iterator();
  }

  public void add(Shape s) {
    this.list.add(s);
  }

  @Override
  public Point getLoc() {
    Rectangle bounds;
    bounds = getBounds();
    return new Point(bounds.x, bounds.y);
  }

  @Override
  public void setLoc(Point p) {
    this.loc = p;
  }

  @Override
  public void translate(int x, int y) {
    Iterator<Shape> it = iterator();
    while (it.hasNext()) {
      it.next().translate(x, y);
      it.next().accept(v);
    }
  }

  @Override
  public Rectangle getBounds() {
    Iterator<Shape> it = this.iterator();
    Rectangle collectionBounds = new Rectangle();
    Rectangle shapeBounds = new Rectangle();
    int min_x = 9999, min_y = 9999, max_x = 0, max_y = 0;
    int maxShapeX, maxShapeY;

    while (it.hasNext()) {
      shapeBounds = it.next().getBounds();

      maxShapeX = shapeBounds.x + shapeBounds.width;
      maxShapeY = shapeBounds.y + shapeBounds.height;
      if (shapeBounds.x < min_x) min_x = shapeBounds.x;
      if (shapeBounds.y < min_y) min_y = shapeBounds.y;
      if (maxShapeX > max_x) max_x = maxShapeX;
      if (maxShapeY > max_y) max_y = maxShapeY;
    }

    collectionBounds.x = min_x;
    collectionBounds.y = min_y;
    collectionBounds.width = max_x - min_x;
    collectionBounds.height = max_y - min_y;

    return collectionBounds;
  }

  @Override
  public void accept(ShapeVisitor v) {
    v.visitCollection(this);
  }
}
