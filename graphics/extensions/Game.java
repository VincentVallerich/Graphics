package graphics.extensions;

import java.awt.Color;
import java.awt.Point;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

/**
 * Extension by EL MOUTAOUAKKIL Ayoub
 */
public class Game {

	public Game(SCollection m )
	{
		SRectangle r1 = new SRectangle(new Point(0,150),20,30);
		r1.addAttributes(new ColorAttributes(true,false,Color.red,Color.red));
		r1.addAttributes(new SelectionAttributes());
		m.add(r1);
		
		SRectangle r2 = new SRectangle(new Point(100,0),20,145);
		r2.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r2.addAttributes(new SelectionAttributes());
		m.add(r2);
		
		SRectangle r3 = new SRectangle(new Point(100,185),20,125);
		r3.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r3.addAttributes(new SelectionAttributes());
		m.add(r3);
		
		SRectangle r4 = new SRectangle(new Point(200,0),20,105);
		r4.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r4.addAttributes(new SelectionAttributes());
		m.add(r4);
		
		SRectangle r5 = new SRectangle(new Point(200,145),20,225);
		r5.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r5.addAttributes(new SelectionAttributes());
		m.add(r5);
		
		SRectangle r6 = new SRectangle(new Point(300,0),20,145);
		r6.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r6.addAttributes(new SelectionAttributes());
		m.add(r6);
		
		SRectangle r7 = new SRectangle(new Point(300,185),20,125);
		r7.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r7.addAttributes(new SelectionAttributes());
		m.add(r7);
		
		SRectangle r8 = new SRectangle(new Point(400,0),20,200);
		r8.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r8.addAttributes(new SelectionAttributes());
		m.add(r8);
		
		SRectangle r9 = new SRectangle(new Point(400,240),20,250);
		r9.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r9.addAttributes(new SelectionAttributes());
		m.add(r9);
		
		SRectangle r10 = new SRectangle(new Point(500,0),20,145);
		r10.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r10.addAttributes(new SelectionAttributes());
		m.add(r10);
		
		SRectangle r11 = new SRectangle(new Point(500,185),20,125);
		r11.addAttributes(new ColorAttributes(true,false,Color.green,Color.green));
		r11.addAttributes(new SelectionAttributes());
		m.add(r11);
	}
}
