package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {

	private Rectangle rec;
	
	public SRectangle(Point p,int width,int height)
	{
		this.rec=new Rectangle(p.x,p.y,width,height);
	}
	
	public Rectangle getRect()
	{
		return this.rec;
	}
	
	public Point getLoc()
	{
		return new Point(this.rec.x,this.rec.y);
	}
	
	public void setLoc(Point p)
	{
		this.rec.setLocation(p);
	}
	
	public void translate(int dx,int dy)
	{
		this.rec.translate(dx, dy);	
	}
	
	public Rectangle getBounds()
	{
		return this.rec;
	}
	
	public void accept(ShapeVisitor v)
	{
		v.visitRectangle(this);
	}
}
