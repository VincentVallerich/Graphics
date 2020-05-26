package graphics.extensions;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import graphics.shapes.ui.ShapeDraftman;
import java.awt.event.MouseMotionListener;
import graphics.shapes.SCircle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class Paint extends JFrame implements MouseMotionListener {
	private int x = 0, y=0;
	public Color co;
	public JButton Colors;
	public JColorChooser JColors;
	
	public Paint() {
		
		setSize(1200,720);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.addMouseMotionListener(this);
		Colors = new JButton("Select a color");
		Colors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				co = JColorChooser.showDialog(null,"Select a color",Color.BLACK);
			}
		});
		add(Colors,BorderLayout.NORTH);
		setVisible(true);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics g) {
		Point p = new Point(x,y);
		ShapeDraftman sd = new ShapeDraftman(g);
		SCircle sc = new SCircle(p,4);
		sc.addAttributes(new ColorAttributes(true,true,co,co));
		sc.addAttributes(new SelectionAttributes());
		sd.visitCircle(sc);
	}
	
}
