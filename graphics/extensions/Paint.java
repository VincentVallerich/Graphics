package graphics.extensions;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
import java.awt.GridLayout;

public class Paint extends JFrame implements MouseMotionListener,ActionListener {
	private int x = 0, y = 0;
	public Color co;
	public JButton colors;
	public JColorChooser jColors;
	public JTextField thickness;
	public String t = "1";
	public Paint() {
		setSize(1200, 720);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1,2));
		colors = new JButton("Select a color");
		thickness = new JTextField(t,10);
		colors.addActionListener(this);
		thickness.addActionListener(this);
		jp.add(colors);
		jp.add(thickness);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.addMouseMotionListener(this);
		c.add(jp,BorderLayout.NORTH);
		this.setVisible(true);
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		x=e.getX();
		y=e.getY();
		repaint();
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
	}
	
	public void actionPerformed(final ActionEvent e) {
		co = JColorChooser.showDialog(null,"Select a color",Color.BLACK);
		t = thickness.getText();
	}

	public void paint(final Graphics g) {
		final Point p = new Point(x+15,y+45);
		final ShapeDraftman sd = new ShapeDraftman(g);
		final SCircle sc = new SCircle(p,(int)Integer.parseInt(t));
		sc.addAttributes(new ColorAttributes(true,true,co,co));
		sc.addAttributes(new SelectionAttributes());
		sd.visitCircle(sc);
	}
	
}
