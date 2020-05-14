package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

public class ShapesDraftman implements ShapeVisitor {
  public static final int HANDLER_SIZE = 2;
  private ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
  private Graphics2D g2d;

  public ShapesDraftman(Graphics g) {
    this.g2d = (Graphics2D) g;
  }

  @Override
  public void visitRectangle(SRectangle r) {
    ColorAttributes ca = (ColorAttributes) r.getAttributes("ColorAttributes");
    Rectangle r2d = r.getRect();

    if (ca == null) ca = DEFAULTCOLORATTRIBUTES;

    if (ca.filled) {
      g2d.setColor(ca.filledColor);
      g2d.fillRect(r2d.x, r2d.y, r2d.width, r2d.height);
    } else {
      g2d.setColor(ca.strokedColor);
      g2d.drawRect(r2d.x, r2d.y, r2d.width, r2d.height);
    }

    if (isHandled(r)) paintHandler(r.getBounds());
  }

  @Override
  public void visitCircle(SCircle c) {
    ColorAttributes ca = (ColorAttributes) c.getAttributes("ColorAttributes");
    Rectangle r = c.getBounds();

    if (ca == null) ca = DEFAULTCOLORATTRIBUTES;

    if (ca.filled) {
      g2d.setColor(ca.filledColor);
      g2d.fillOval(r.x, r.y, r.width, r.height);
    } else {
      g2d.setColor(ca.strokedColor);
      g2d.drawOval(r.x, r.y, r.width, r.height);
    }
    if (isHandled(c)) paintHandler(c.getBounds());
  }

  @Override
  public void visitText(SText t) {
    ColorAttributes ca = (ColorAttributes) t.getAttributes("ColorAttributes");
    FontAttributes fa = (FontAttributes) t.getAttributes("FontAttributes");
    Rectangle r = t.getBounds();

    if (ca == null) ca = DEFAULTCOLORATTRIBUTES;

    g2d.setFont(fa.font);
    g2d.setColor(ca.filledColor);
    g2d.fillRect(r.x, r.y - r.getBounds().height, r.width, r.height);
    g2d.setColor(ca.strokedColor);
    g2d.drawString(t.getText(), r.x, r.y);

    if (isHandled(t)) paintHandler(t.getBounds());
  }

  @Override
  public void visitCollection(SCollection co) {
    Iterator<Shape> it = co.iterator();
    while (it.hasNext()) it.next().accept(this);
  }

  public boolean isHandled(Shape s) {
    return ((SelectionAttributes)s.getAttributes("SelectionAttributes")).isSelected();
  }

  public void paintHandler(Rectangle bounds) {
    Rectangle topLeft = new Rectangle(bounds.x-(HANDLER_SIZE/2), bounds.y-(HANDLER_SIZE/2), 0, 0);
    Rectangle bottomRight = new Rectangle(bounds.x + bounds.width, bounds.y + bounds.height, 0, 0);
    topLeft.grow(HANDLER_SIZE, HANDLER_SIZE);
    bottomRight.grow(HANDLER_SIZE, HANDLER_SIZE);
    g2d.drawRect(topLeft.x, topLeft.y, topLeft.width, topLeft.height);
    g2d.drawRect(bottomRight.x, bottomRight.y, bottomRight.width, bottomRight.height);
  }
}
