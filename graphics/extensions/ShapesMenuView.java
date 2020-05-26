package graphics.extensions;

import graphics.ui.View;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings ("serial")
public class ShapesMenuView extends View{
    SCollection model;
    JButton strokedButton = new JButton("Contour");
    JButton filledButton = new JButton("Remplissage");
    JButton submitButton = new JButton("Valider");
    JCheckBox enterCoordinate = new JCheckBox("Définir coordonnées :");
    JRadioButton radioRectangle = new JRadioButton("Rectangle",true);
    JRadioButton radioPolygon = new JRadioButton("Polygone");
    JRadioButton radioCircle = new JRadioButton("Cercle");
    JRadioButton radioText = new JRadioButton("Texte");
    JTextField locX = new JTextField();
    JTextField locY = new JTextField();
    JTextField width = new JTextField();
    JTextField height = new JTextField();
    JTextField polygonSides = new JTextField("Nombre de côtés");
    ButtonGroup radioGroup = new ButtonGroup();

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

        //#region setBounds
        enterCoordinate.setBounds(12, 40, 150, 21);
        locX.setBounds(179, 40, textFieldWidth, textFieldHeight);
        locY.setBounds(299, 40, textFieldWidth, textFieldHeight);
        width.setBounds(70, 128-(textFieldHeight/2), textFieldWidth, textFieldHeight); //128 is the y of the label corresponding to textfield 
        height.setBounds(240, 128-(textFieldHeight/2), textFieldWidth, textFieldHeight);
        filledButton.setBounds(232,70,buttonWidth,buttonHeight);
        strokedButton.setBounds(98,70,buttonWidth,buttonHeight);
        submitButton.setBounds(12, 155, 215, 30);
        polygonSides.setBounds(410, 12, textFieldWidth, textFieldHeight);
        radioRectangle.setBounds(70, 15, 85, 15);
        radioPolygon.setBounds(310, 15, 85, 15);
        radioCircle.setBounds(170, 15, 70, 15);
        radioText.setBounds(245, 15, 60, 15);
        //#endregion
        
        locX.setEditable(false);
        locY.setEditable(false);
        polygonSides.setEditable(false);

        radioGroup.add(radioRectangle);
        radioGroup.add(radioPolygon);
        radioGroup.add(radioCircle);
        radioGroup.add(radioText);

        polygonSides.setHorizontalAlignment(JTextField.CENTER);
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

        radioPolygon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = radioPolygon.isSelected();
                polygonSides.setEditable(editable);
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
        add(radioRectangle);
        add(radioPolygon);
        add(radioCircle);
        add(radioText);
        add(polygonSides);
        add(locX);
        add(locY);
        add(filledButton);
        add(submitButton);
        add(strokedButton);
        add(enterCoordinate);
        add(height);
        add(width);
        

        g.drawString("Forme : ", 12, 25);
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
        JRadioButton next = (JRadioButton) radioGroup.getElements().nextElement();
        if(next.isSelected());
            System.out.println(next.getText());
        switch(radioGroup.getElements().nextElement().getText()) {
            case "Rectangle" :
                buildRectangle();
                break;
            case "Cerle" :
                buildCircle();
                break;
            case "Texte" :
                buildText();
                break;
            case "Polygone" :
                buildPolygone();
                break;
            default:
                break;
        }
    }

    private void buildRectangle() {
	}

	private void buildCircle() {
	}

	private void buildText() {
	}

	private void buildPolygone() {
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