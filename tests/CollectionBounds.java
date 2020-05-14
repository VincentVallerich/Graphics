package tests;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import java.awt.Point;
import java.awt.Rectangle;

public class CollectionBounds {

  public void run() {
    SRectangle r1 = new SRectangle(new Point(5, 6), 7, 8);
    SRectangle r2 = new SRectangle(new Point(9, 10), 11, 12);
    SRectangle r3 = new SRectangle(new Point(13, 14), 15, 16);
    SRectangle r4 = new SRectangle(new Point(17, 18), 19, 20);

    SCollection sc = new SCollection();
    sc.add(r1);
    sc.add(r2);
    sc.add(r3);
    sc.add(r4);

    Rectangle bounds = new Rectangle();
    bounds = sc.getBounds();

    System.out.println("Top left : " + bounds.x + " " + bounds.y);
    System.out.println(
      "Top right : " + (bounds.x + bounds.width) + " " + bounds.y
    );
    System.out.println(
      "Bottom left : " + (bounds.x) + " " + (bounds.y + bounds.height)
    );
    System.out.println(
      "Bottom right : " +
      (bounds.x + bounds.width) +
      " " +
      (bounds.y + bounds.height)
    );
  }
}
