package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements graphics.shapes.ShapeVisitor {

	public ColorAttributes DEFAULTCOLORATTRIBUTES=new ColorAttributes(true,true,Color.blue,Color.blue);
	private Graphics2D g2;
	
	public ShapeDraftman(Graphics g)
	{
		this.g2=(Graphics2D) g;
	}
	
	public void visitRectangle(SRectangle sr) {
		
		String idc=new ColorAttributes(true, false, Color.white, Color.white).getID();
		String ids=new SelectionAttributes().getID();
		
		ColorAttributes ca=(ColorAttributes) sr.getAttributes(idc);
		SelectionAttributes sa=(SelectionAttributes) sr.getAttributes(ids);
		
		Rectangle r=sr.getRect();
		
		if(ca==null) ca=this.DEFAULTCOLORATTRIBUTES;

		if(ca.stroked)
		{
			this.g2.setColor(ca.strokedColor);
			this.g2.drawRect(r.x,r.y,r.width,r.height);
		}
			
		if(ca.filled)
		{
			this.g2.setColor(ca.filledColor);
			this.g2.fillRect(r.x,r.y,r.width,r.height);
		}
		
		if(sa.isSelected())
		{
			this.drawHandler(r);
		}
	}
	
	public void visitCircle(SCircle sc) {
		String idc=new ColorAttributes(true, false, Color.white, Color.white).getID();
		String ids=new SelectionAttributes().getID();
		
		ColorAttributes ca=(ColorAttributes) sc.getAttributes(idc);
		SelectionAttributes sa=(SelectionAttributes) sc.getAttributes(ids);
		
		if(ca==null) ca=this.DEFAULTCOLORATTRIBUTES;
		
		Rectangle r=sc.getBounds();

		if(ca.stroked)
		{
			this.g2.setColor(ca.strokedColor);
			this.g2.drawOval(r.x, r.y, r.width, r.height);
		}
			
		if(ca.filled)
		{
			this.g2.setColor(ca.filledColor);
			this.g2.fillOval(r.x, r.y, r.width, r.height);
		}
		
		if(sa.isSelected())
		{
			this.drawHandler(r);
		}
	}
	
	public void visitText(SText st) {
		
		String idc=new ColorAttributes(true, false, Color.white, Color.white).getID();
		String idf=new FontAttributes().getID();
		String ids=new SelectionAttributes().getID();
		
		FontAttributes fa=(FontAttributes)st.getAttributes(idf);
		ColorAttributes ca=(ColorAttributes) st.getAttributes(idc);
		SelectionAttributes sa=(SelectionAttributes) st.getAttributes(ids);
		
		Rectangle r=st.getBounds();
		
		this.g2.setColor(ca.filledColor);
		this.g2.fillRect(r.x,r.y,r.width,r.height);
		
		fa.fontColor=ca.strokedColor;
		this.g2.setColor(fa.fontColor);
		this.g2.setFont(fa.font);
		this.g2.drawString(st.getText(),st.getLoc().x,st.getLoc().y);
		
		if(sa.isSelected())
		{
			this.drawHandler(r);
		}
	}
	
	public void visitCollection(SCollection s) {
		
		Iterator<Shape> iterator=s.iterator();
		String ids=new SelectionAttributes().getID();
		SelectionAttributes sa=(SelectionAttributes) s.getAttributes(ids);
		
		while(iterator.hasNext())
		{
			Shape nextIterator=iterator.next();
			nextIterator.accept(this);
		}
		
		if(sa.isSelected())
		{
			this.drawHandler(s.getBounds());
		}
	}
	
	public void drawHandler(Rectangle bounds)
	{
		Rectangle top=new Rectangle(bounds.x-2,bounds.y-2,2,2);
		Rectangle bot=new Rectangle(bounds.x+bounds.width,bounds.y+bounds.height,2,2);
		this.g2.setColor(Color.black);
		this.g2.drawRect(top.x,top.y,top.width,top.height);
		this.g2.drawRect(bot.x,bot.y,bot.width,bot.height);
	}
}
