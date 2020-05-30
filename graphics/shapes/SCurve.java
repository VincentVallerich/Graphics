package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Function;

/**
 * Extension by CRESSEIN Martin
 */
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
		Double x_max = findMax(fx, 0.0, time);
		Double x_min = findMin(fx, 0.0, time);
		Double y_max = findMax(fy, 0.0, time);
		Double y_min = findMin(fy, 0.0, time);
		
		Rectangle rect = new Rectangle(loc.x+x_min.intValue(), loc.y+y_min.intValue(), x_max.intValue()-x_min.intValue(), y_max.intValue()-y_min.intValue());
		return rect;
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitCurve(this);
	}
	
	private Double findMax(Function<Double,Double> f, Double lb, Double ub) {
		Double step = 0.1;
		Double a = lb;
		Double max = f.apply(a);
		Double f_m;
		while (a < ub) {
			a+=step;
			f_m = f.apply(a);
			if (f_m>max) {
				max = f_m;
			}
		}
		
		return max;
	}

	private Double findMin(Function<Double,Double> f, Double lb, Double ub) {
		Double step = 0.1;
		Double a = lb;
		Double min = f.apply(a);
		Double f_m;
		while (a < ub) {
			a+=step;
			f_m = f.apply(a);
			if (f_m<min) {
				min = f_m;
			}
		}
		
		return min;
	}

}
