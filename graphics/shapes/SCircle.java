package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape{

	private int radius;
	private Point loc;
	
	public SCircle(Point p,int r)
	{
		this.radius=r;
		this.loc=p;
	}
	
	public int getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(int r)
	{
		this.radius=r;
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
		return new Rectangle(this.loc.x-this.radius,this.loc.y-this.radius,2*this.radius,2*this.radius);
	}
	
	public void accept(ShapeVisitor v)
	{
		v.visitCircle(this);
	}
}
