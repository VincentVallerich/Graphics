package graphics.shapes;



import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class SPolygon extends Shape {
	
	public Point loc;
	Polygon poly;

	public SPolygon (int xpts[], int ypts[], int npts) {
		this.loc = new Point(xpts[0], ypts[0]);
		this.poly = new Polygon(xpts, ypts, npts);
	}
	
	public Polygon getPolygon() {
		return this.poly;
	}
	
	public Point getLoc() {
		return this.loc;
	}

	public void setLoc(Point p) {
		this.loc.setLocation(p);
	}

	public void translate(int dx, int dy) {
		this.loc.translate(dx, dy);
		this.poly.translate(dx, dy);
	}

	public Rectangle getBounds() {
		return this.poly.getBounds();
	}

	public void accept(ShapeVisitor v) {
		v.visitPolygon(this);
	}
}
