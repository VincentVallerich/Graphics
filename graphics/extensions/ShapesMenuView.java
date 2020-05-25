package graphics.extensions;

import graphics.ui.View;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings ("serial")
public class ShapesMenuView extends View{
    SCollection model;
    public ShapesMenuView(Object model) {
        super(model);
        this.model = (SCollection)model;
    }
    
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        int textFieldWidth=100;
        int textFieldHeight=22;
        int buttonWidth=110;
        int buttonHeight=30;

        JButton strokedButton = new JButton("Contour");
        JButton filledButton = new JButton("Remplissage");
        JButton submitButton = new JButton("Valider");
        JCheckBox enterCoordinate = new JCheckBox("Définir coordonnées :");
        JTextField sideNumber = new JTextField();
        JTextField locX = new JTextField();
        JTextField locY = new JTextField();

        sideNumber.setBounds(120, 10, textFieldWidth, textFieldHeight);
        enterCoordinate.setBounds(12, 40, 150, 21);
        strokedButton.setBounds(12,100,buttonWidth,buttonHeight);
        filledButton.setBounds(138,100,buttonWidth,buttonHeight);
        submitButton.setBounds(12, 155, 215, 30);
        locX.setBounds(179, 40, textFieldWidth, textFieldHeight);
        locY.setBounds(299, 40, textFieldWidth, textFieldHeight);

        locX.setEditable(false);
        locY.setEditable(false);

        sideNumber.setHorizontalAlignment(JTextField.CENTER);

        /*Toggle fields for enter coordinate */
        enterCoordinate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            boolean editable = enterCoordinate.isSelected();
            locX.setEditable(editable);
            locY.setEditable(editable);
            invalidate();
            }
        });

        strokedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            strokedButton.setBackground(createColorPalette());
            requestFocus();
            invalidate();
            }
        });

        filledButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            filledButton.setBackground(createColorPalette());
            requestFocus();
            invalidate();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addAttributes(new SelectionAttributes());
            
                SRectangle r = new SRectangle(new Point(50, 50), 40, 30);
                r.addAttributes(new ColorAttributes(true, false, Color.ORANGE, Color.BLACK));
                r.addAttributes(new SelectionAttributes());
                model.add(r);
                refresh(model);
            }
        });

        /*To prevent ghost components*/
        this.removeAll();

        add(sideNumber);
        add(filledButton);
        add(submitButton);
        add(strokedButton);
        add(enterCoordinate);
        add(locX);
        add(locY);
        
        g.drawString("Nombre de cotés : ", 12, 25);
        g.drawString("Couleur :", 12, 89);

        this.requestFocus();
    }

    public void refresh(Object model) {
        ShapesController c = new ShapesController(model);
        c.setView(new ShapesView(model));
        c.getView().invalidate();
    }

    public Color createColorPalette() {
        JFrame window = new JFrame();
        window.setSize(600, 350);
        window.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        window.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 380);
        window.add(panel);

        return JColorChooser.showDialog(window, "Choisissez une couleur", Color.BLACK);
    }
}