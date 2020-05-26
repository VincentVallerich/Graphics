package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Function;

public class SCurve extends Shape {
	
	public Point loc;
	public Function<Double,Double> fx;
	public Function<Double,Double> fy;
	public Double time;

	public SCurve(Point pt, Function<Double,Double> x, Function<Double,Double> y, Double t) {
		this.loc = pt;
		this.fx = x;
		this.fy = y;
		this.time = t;
	}
	
	@Override
	public Point getLoc()
	{
		return loc;
	}

	@Override
	public void setLoc(Point pt) {
		loc.setLocation(pt);
	}

	@Override
	public void translate(int dx, int dy) {
		loc.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitCurve(this);
	}
}
