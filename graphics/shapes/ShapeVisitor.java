package graphics.shapes;

public abstract interface ShapeVisitor{
	public abstract void visitRectangle(SRectangle s);
	public abstract void visitCircle(SCircle s);
	public abstract void visitText(SText s);
	public abstract void visitCollection(SCollection s);
}
