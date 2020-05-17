package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.extensions.Game;
import graphics.shapes.SCollection;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {

	public int state=0;
	public Shape s=null;
	public Point loc=null;
	public Boolean shift=false;
	Point prec=null;	
	
	public ShapesController(Object newModel) {
		super(newModel);
	}
	
	public Shape getTarget(MouseEvent e)
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		while(iterator.hasNext())
		{
			shape=iterator.next();
			if(shape.getBounds().contains(e.getPoint()))
			{
				return shape;
			}
		}
		return null;
	}
	
	public Boolean shiftDown() 
	{
		return shift;
	}
	
	public void unselectAll()
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new SelectionAttributes().getID();
		while(iterator.hasNext())
		{
			shape=iterator.next();
			((SelectionAttributes)shape.getAttributes(id)).unselected();
		}
	}
	
	public void translateSelected(int x,int y)
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new SelectionAttributes().getID();
		
		while(iterator.hasNext())
		{
			shape=iterator.next();
			if(((SelectionAttributes)shape.getAttributes(id)).isSelected())
			{
				shape.translate(x, y);
			}
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
		if(this.state==0)
		{
			s=this.getTarget(e);
			
			this.state=1;
			
			String id=new SelectionAttributes().getID();
			
			if(s==null || !((SelectionAttributes)s.getAttributes(id)).isSelected())
			{
				this.state=2;
			}
			
			if(s!=null && ((SelectionAttributes)s.getAttributes(id)).isSelected())
			{
				this.state=0;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(this.state==2)
		{
			this.state=0;
		}
		this.prec=null;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		this.s=getTarget(e);
		this.state=3;
		
		if(this.s==null)
		{
			if(!this.shiftDown())
			{
				this.unselectAll();
			}
		}
		else
		{
			if(!this.shiftDown())
			{
				this.unselectAll();
			}
			String id=new SelectionAttributes().getID();
			
			((SelectionAttributes)s.getAttributes(id)).toggleSelection();
		}
		this.state=0;

		this.getView().invalidate();
	}
	
	public void keyPressed(KeyEvent evt)
	{
		if(evt.getModifiersEx()!=0)
		{
			this.shift=true;
		}
		
		if(this.state==0 && evt.getKeyCode()==71)
		{
			gameModel();
			end=false;
		}
		
		if(this.onGame && evt.getKeyCode()==38 && !this.end)
		{
			this.up();
			this.check();
		}
		
		if(this.onGame && evt.getKeyCode()==40  && !this.end)
		{
			this.down();
			this.check();
		}
		
		if(this.onGame && evt.getKeyCode()==39  && !this.end)
		{
			this.right();
			this.check();
		}
		
		if(this.onGame && evt.getKeyCode()==37  && !this.end)
		{
			this.left();
			this.check();
		}
	}
	
	public void keyReleased(KeyEvent evt)
	{
		this.shift=false;
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		if(this.state==0)
		{
			Point now=evt.getPoint();
			
			if (this.prec==null)
			{
				this.prec=now;
			}
			else
			{
				int dx=now.x-this.prec.x;
				int dy=now.y-this.prec.y;
				this.prec=now;
				this.translateSelected(dx,dy);
				this.getView().invalidate();
			}
		}
	}
	
	//-----------------------Extension faite par Ayoub El Moutaouakkil----------------------------//
	
	public SCollection saveModel=(SCollection) this.getModel();
	public Boolean onGame=false;
	public Boolean end=false;
	public int score=0;
	public int highScore=0;
	
	public void gameModel()
	{
		if(onGame==false)
		{
			onGame=true;
			SCollection m=new SCollection();
			m.addAttributes(new SelectionAttributes());
			
			new Game(m);
			
			this.setModel(m);
			this.getView().setModel(m);
			
			
		}
		
		else
		{
			this.setModel(this.saveModel);
			this.getView().setModel(this.saveModel);
			onGame=false;
		}
		this.getView().invalidate();
	}
	
	
	public void up()
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new ColorAttributes(true,true,Color.blue,Color.blue).getID();
		while(iterator.hasNext())
		{
			shape=iterator.next();
			if(((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red) && shape.getBounds().y>0)
			{
				shape.setLoc(new Point(shape.getLoc().x,shape.getLoc().y-4));
			}
			if(!((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red))
			{
				if(shape.getBounds().x+shape.getBounds().width > 0)
				{
					shape.setLoc(new Point(shape.getLoc().x-3,shape.getLoc().y));
				}
				else
				{
					shape.setLoc(new Point(500,shape.getLoc().y));
				}
			}
			this.getView().invalidate();
		}
	}
	
	public void down()
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new ColorAttributes(true,true,Color.blue,Color.blue).getID();
		while(iterator.hasNext())
		{
			shape=iterator.next();
			if(((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red) && shape.getBounds().y+shape.getBounds().height<300)
			{
				shape.setLoc(new Point(shape.getLoc().x,shape.getLoc().y+4));
			}
			if(!((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red))
			{
				
				if(shape.getBounds().x+shape.getBounds().width > 0)
				{
					shape.setLoc(new Point(shape.getLoc().x-3,shape.getLoc().y));
				}
				else
				{
					shape.setLoc(new Point(500,shape.getLoc().y));
				}
			}
			this.getView().invalidate();
		}
	}
	
	public void right()
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new ColorAttributes(true,true,Color.blue,Color.blue).getID();
		while(iterator.hasNext())
		{
			shape=iterator.next();
			if(((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red) && shape.getBounds().x+shape.getBounds().width<300)
			{
				shape.setLoc(new Point(shape.getLoc().x+4,shape.getLoc().y));
			}
			if(!((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red))
			{
				if(shape.getBounds().x+shape.getBounds().width > 0)
				{
					shape.setLoc(new Point(shape.getLoc().x-3,shape.getLoc().y));
				}
				else
				{
					shape.setLoc(new Point(500,shape.getLoc().y));
				}
			}
			this.getView().invalidate();
		}
	}
	
	public void left()
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new ColorAttributes(true,true,Color.blue,Color.blue).getID();
		while(iterator.hasNext())
		{
			shape=iterator.next();
			if(((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red) && shape.getBounds().x>0 )
			{
				shape.setLoc(new Point(shape.getLoc().x-2,shape.getLoc().y));
			}
			if(!((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red))
			{
				if(shape.getBounds().x+shape.getBounds().width > 0)
				{
					shape.setLoc(new Point(shape.getLoc().x-3,shape.getLoc().y));
				}
				else
				{
					shape.setLoc(new Point(500,shape.getLoc().y));
				}
			}
			this.getView().invalidate();
		}
	}
	
	public void check()
	{
		Shape shape=null;
		SCollection model=(SCollection) this.getModel();
		Iterator<Shape>iterator=model.iterator();
		String id=new ColorAttributes(true,true,Color.blue,Color.blue).getID();
		Rectangle r=null;
		while(r==null)
		{
			while(iterator.hasNext())
			{
				shape=iterator.next();
				if(((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red))
				{
					r=shape.getBounds();
				}
			}
		}
		
		if(r!=null)
		{
			iterator=model.iterator();
			while(iterator.hasNext())
			{
				
				shape=iterator.next();
				if(!((ColorAttributes)shape.getAttributes(id)).filledColor.equals(Color.red))
				{
					if(shape.getBounds().contains(new Point(r.x,r.y)) ||
							shape.getBounds().contains(r.x,r.y+r.height) ||
							shape.getBounds().contains(r.x+r.width,r.y) ||
							shape.getBounds().contains(r.x+r.width,r.y+r.height))
					{
						end=true;
						
						SCollection m2=new SCollection();
						m2.addAttributes(new SelectionAttributes());
						
						SText t= new SText(new Point(100,100),"Game Over");
						t.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.RED));
						t.addAttributes(new FontAttributes());
						t.addAttributes(new SelectionAttributes());
						m2.add(t);
						
						SText t2= new SText(new Point(100,150),"Your score: "+String.valueOf(score/2));
						t2.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.RED));
						t2.addAttributes(new FontAttributes());
						t2.addAttributes(new SelectionAttributes());
						m2.add(t2);
						
						SText t3= new SText(new Point(100,200),"High score: "+String.valueOf(highScore/2));
						t3.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.RED));
						t3.addAttributes(new FontAttributes());
						t3.addAttributes(new SelectionAttributes());
						m2.add(t3);
						this.setModel(m2);
						this.getView().setModel(m2);
						this.getView().invalidate();
						score=0;
						
					}
					
					else
					{
						if(shape.getBounds().x+shape.getBounds().width<=r.x+3 && shape.getBounds().x+shape.getBounds().width>=r.x-3)
						{
							
							score+=1;
							if (score > highScore)
							{
								highScore=score;
							}
						}
					}
				}
			}
		}
	}
}
