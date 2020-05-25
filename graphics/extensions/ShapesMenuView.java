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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings ("serial")
public class ShapesMenuView extends View{
    SCollection model;
    String[] listOfShape = {"Rectangle","Cercle","Texte","Polygone"};

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

        //#region create component
        JButton strokedButton = new JButton("Contour");
        JButton filledButton = new JButton("Remplissage");
        JButton submitButton = new JButton("Valider");
        JCheckBox enterCoordinate = new JCheckBox("Définir coordonnées :");
        JComboBox<String> shapeSelector = new JComboBox<String>(listOfShape);
        JTextField locX = new JTextField();
        JTextField locY = new JTextField();
        JTextField width = new JTextField();
        JTextField height = new JTextField();
        //#endregion

        //#region setBounds
        enterCoordinate.setBounds(12, 40, 150, 21);
        locX.setBounds(179, 40, textFieldWidth, textFieldHeight);
        locY.setBounds(299, 40, textFieldWidth, textFieldHeight);
        width.setBounds(70, 128-(textFieldHeight/2), textFieldWidth, textFieldHeight); //128 is the y of the label corresponding to textfield 
        height.setBounds(240, 128-(textFieldHeight/2), textFieldWidth, textFieldHeight);
        shapeSelector.setBounds(120, 10, textFieldWidth, textFieldHeight);
        filledButton.setBounds(232,70,buttonWidth,buttonHeight);
        strokedButton.setBounds(98,70,buttonWidth,buttonHeight);
        submitButton.setBounds(12, 155, 215, 30);
        //#endregion
        
        locX.setEditable(false);
        locY.setEditable(false);

        width.setHorizontalAlignment(JTextField.CENTER);
        height.setHorizontalAlignment(JTextField.CENTER);
        locX.setHorizontalAlignment(JTextField.CENTER);
        locY.setHorizontalAlignment(JTextField.CENTER);

        //#region ActionEvent
        /*Toggle fields to editable for enter coordinate */
        enterCoordinate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            boolean editable = enterCoordinate.isSelected();
            locX.setEditable(editable);
            locY.setEditable(editable);
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
                // model.addAttributes(new SelectionAttributes());
            
                // SRectangle r = new SRectangle(new Point(50, 50), 40, 30);
                // r.addAttributes(new ColorAttributes(true, false, Color.ORANGE, Color.BLACK));
                // r.addAttributes(new SelectionAttributes());
                // model.add(r);
                buildShape();
                refresh(model);
            }
        });
        //#endregion

        /*To prevent ghost components*/
        this.removeAll();
        
        add(shapeSelector);
        validate();
        add(locX);
        add(locY);
        add(filledButton);
        add(submitButton);
        add(strokedButton);
        add(enterCoordinate);
        add(height);
        add(width);
        

        g.drawString("Nombre de cotés : ", 12, 25);
        g.drawString("Couleur :", 12, 89);
        g.drawString("Largeur", 12, 128);
        g.drawString("Hauteur", 190, 128);

        this.requestFocus();
    }

    public void refresh(Object model) {
        ShapesController c = new ShapesController(model);
        c.setView(new ShapesView(model));
        c.getView().invalidate();
    }

    public void buildShape() {

        //SRectangle shape = new SRectangle(p, width, height);
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