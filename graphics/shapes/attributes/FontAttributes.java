package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class FontAttributes extends Attributes {

	private String id="fontAttribute";
	public Font font;
	public Color fontColor;
	
	public String getID()
	{
		return id;
	}
	
	public Rectangle getBounds(String s)
	{
		BufferedImage off_Image = new BufferedImage(100, 50,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = off_Image.createGraphics();
		FontMetrics m=g2.getFontMetrics(this.font);
		
		return new Rectangle(0,0,m.stringWidth(s),m.getHeight());
	}
	
	public FontAttributes()
	{
		this.font=new Font("Arial",0,18);
	}
}
