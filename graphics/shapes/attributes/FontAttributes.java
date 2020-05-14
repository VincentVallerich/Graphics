package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class FontAttributes extends Attributes {
  private Font DEFAULTFONT = new Font("Arial", Font.PLAIN, 14);
  public Font font = DEFAULTFONT;

  private Color DEFAULTCOLOR = Color.BLACK;
  public Color fontColor = DEFAULTCOLOR;

  @Override
  public String getId() {
    return "FontAttributes";
  }

  public Rectangle getBounds(String str) {
    FontRenderContext frc = new FontRenderContext(null, false, false);
    Rectangle2D bounds = font.getStringBounds(str, frc);

    return bounds.getBounds();
  }
}
