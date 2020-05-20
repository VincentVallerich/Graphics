package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
  private String id = "colorattributes";

  public Boolean filled;
  public Boolean stroked;

  public Color filledColor;
  public Color strokedColor;

  public String getID() {
    return id;
  }

  public ColorAttributes(Boolean f, Boolean s, Color fi, Color st) {
    this.filled = f;
    this.stroked = s;
    this.filledColor = fi;
    this.strokedColor = st;
  }
}
