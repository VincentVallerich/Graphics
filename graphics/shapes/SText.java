package graphics.shapes;


import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape{

	private String text;
	private Point loc;
	
	public SText(Point p,String s)
	{
		this.loc=p;
		this.text=s;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public void setText(String t)
	{
		this.text=t;
	}
	
	public Point getLoc()
	{
		return this.loc;
	}
	
	public void setLoc(Point p)
	{
		this.loc=p;
	}
	
	public void translate(int dx,int dy)
	{
		this.loc.translate(dx, dy);
	}
	
	public Rectangle getBounds()
	{
		String id=new FontAttributes().getID();
		Rectangle r1=((FontAttributes) this.getAttributes(id)).getBounds(this.getText());
		return new Rectangle(this.getLoc().x,this.getLoc().y-r1.height,r1.width,r1.height);
	}
	
	public void accept(ShapeVisitor v)
	{
		v.visitText(this);
	}
}
