package graphics.shapes;

import graphics.shapes.attributes.Attributes;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.TreeMap;

public abstract class Shape {
  TreeMap<String, Attributes> attributes = new TreeMap<>();

  public void addAttributes(Attributes a) {
    this.attributes.put(a.getId(), a);
  }

  public Attributes getAttributes(String desc) {
    return this.attributes.get(desc);
  }

  public abstract Point getLoc();

  public abstract void setLoc(Point p);

  public abstract void translate(int x, int y);

  public abstract Rectangle getBounds();

  public abstract void accept(ShapeVisitor v);
}
