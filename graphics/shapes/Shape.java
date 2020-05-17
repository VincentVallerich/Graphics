package graphics.shapes;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.TreeMap;
import graphics.shapes.attributes.Attributes;

public abstract class Shape {

	private TreeMap<String,Attributes> attributes=new TreeMap<>();
	
	public void addAttributes(Attributes a)
	{
		attributes.put(a.getID(),a);
	}
	
	public Attributes getAttributes(String s)
	{
		return this.attributes.get(s);
	}
	
	public abstract Point getLoc();
	
	public abstract void setLoc(Point p);
	
	public abstract void translate(int dx,int dy);
	
	public abstract Rectangle getBounds();
	
	public abstract void accept(ShapeVisitor v);

	
	
	
	
}
