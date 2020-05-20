package tests;

import graphics.shapes.SCollection;
import graphics.shapes.ui.ShapesView;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class RunTests extends JFrame {
  ShapesView sview;
  SCollection model;

  public static int getRandom() {
    return (int) (Math.random() * 20);
  }

  public static Rectangle expectedBounds(int x, int y, int width, int height) {
    Rectangle expected = new Rectangle();

    expected.x = x;
    expected.y = y;
    expected.width = width;
    expected.height = height;

    return expected;
  }

  public static Rectangle expectedBounds(int x, int y, int radius) {
    Rectangle expected = new Rectangle();

    expected.x = x - radius;
    expected.y = y - radius;
    expected.width = 2 * radius;
    expected.height = 2 * radius;

    return expected;
  }

  public static void compareBounds(Rectangle result, Rectangle expected) {
    if (result.equals(expected)) print("Sucess", result.getLocation()); else {
      print("Failed for point at loc", result.getLocation());
      print("Expected", expected.getLocation());
    }
  }

  public static void print(String s, Point p) {
    System.out.println(s + " " + p.x + " " + p.y);
  }

  @Override
  public boolean equals(Object obj) {
    return (
      (obj instanceof Rectangle) &&
      ((Rectangle) obj).getLocation().equals(this.getLocation())
    );
  }
}
