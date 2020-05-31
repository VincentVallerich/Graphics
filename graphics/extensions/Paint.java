package graphics.extensions;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

@SuppressWarnings ("serial")
public class Paint extends JFrame implements MouseMotionListener{
	private int x = -10, y = -10;
	private Color co;
	private JButton colors;
	private JButton validate;
	private JColorChooser jColors;
	private JTextField thickness;
	private String t = "1";
	
	public Paint() {
		setSize(1200, 720);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel jp = new JPanel();
		thickness = new JTextField(t,10);
		colors = new JButton("Choisissez une couleur");
		validate = new JButton("Valider");
		colors.setBounds(0, 0, 60, 40);
		thickness.setBounds(5,5,80,100);
		jp.addMouseMotionListener(this);
		colors.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				co = JColorChooser.showDialog(null,"Select a color",Color.BLACK);
			}
		});
		thickness.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				t = thickness.getText();
			}
		});
		validate.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				t = thickness.getText();
			}
		});
		jp.add(colors);
		jp.add(thickness);
		jp.add(validate);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.addMouseMotionListener(this);
		c.add(jp,BorderLayout.NORTH);
		this.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		repaint();
	}

	@Override
	public void mouseMoved( MouseEvent e) {
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
