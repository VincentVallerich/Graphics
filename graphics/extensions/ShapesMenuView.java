package graphics.extensions;

import graphics.ui.View;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.util.Enumeration;

/**
 * Author VALLERICH Vincent
 */
@SuppressWarnings ("serial")
public class ShapesMenuView extends View{
    SCollection model;
    Point coordinate = new Point(150,150);
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
    JTextField widthField = new JTextField();
    JTextField heightField = new JTextField();
    JTextField textField = new JTextField();
    ButtonGroup radioGroup = new ButtonGroup();
    /** This i is for avoid the repeating of the call of button 
     *like colors button and "Valider"
     *else the action repeating 3 times
     */
    int i=0;

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
        widthField.setBounds(70, 128-(textFieldHeight/2), textFieldWidth, textFieldHeight); //128 is the y of the label corresponding to textfield 
        heightField.setBounds(240, 128-(textFieldHeight/2), textFieldWidth, textFieldHeight);
        filledButton.setBounds(232,70,buttonWidth,buttonHeight);
        strokedButton.setBounds(98,70,buttonWidth,buttonHeight);
        submitButton.setBounds(50, 155, 460, 30);
        textField.setBounds(410, 12, textFieldWidth, textFieldHeight);
        radioRectangle.setBounds(70, 15, 85, 15);
        radioPolygon.setBounds(310, 15, 85, 15);
        radioCircle.setBounds(170, 15, 70, 15);
        radioText.setBounds(245, 15, 60, 15);
        //#endregion
        
        locX.setEditable(false);
        locY.setEditable(false);
        textField.setEditable(false);

        radioRectangle.setActionCommand("Rectangle");
        radioPolygon.setActionCommand("Polygon");
        radioCircle.setActionCommand("Circle");
        radioText.setActionCommand("Text");

        radioGroup.add(radioRectangle);
        radioGroup.add(radioPolygon);
        radioGroup.add(radioCircle);
        radioGroup.add(radioText);

        textField.setHorizontalAlignment(JTextField.CENTER);
        widthField.setHorizontalAlignment(JTextField.CENTER);
        heightField.setHorizontalAlignment(JTextField.CENTER);
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

        radioRectangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = radioText.isSelected();
                textField.setEditable(editable);
                textField.setText("");
            }
        });

        radioCircle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = radioText.isSelected();
                textField.setEditable(editable);
                textField.setText("");
            }
        });

        radioPolygon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = radioPolygon.isSelected();
                textField.setEditable(editable);
                textField.setText("Nombre de côtés");
            }
        });

        radioText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = radioText.isSelected();
                textField.setEditable(editable);
                textField.setText("Texte");
            }
        });

        strokedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (i%3==0){
                    i+=3;
                    strokedButton.setBackground(createColorPalette());
                }
                requestFocus();
                invalidate();
            }
        });

        filledButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (i%3==0) {
                    i+=3;
                    filledButton.setBackground(createColorPalette());
                }
                requestFocus();
                invalidate();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            String radioSelected=null;

            public void actionPerformed(ActionEvent e) {
                if (i%3==0) {
                    i+=3;
                    boolean emptyWidth = widthField.getText().trim().isEmpty();
                    boolean emptyHeight = widthField.getText().trim().isEmpty();
                    if (emptyHeight || emptyWidth)
                        JOptionPane.showMessageDialog(null, "Longueur et largeur doivent être remplies", "Erreur", JOptionPane.ERROR_MESSAGE);
                    else {
                        for (Enumeration<AbstractButton> buttons = radioGroup.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();
                            if (button.isSelected()) {
                                radioSelected = button.getActionCommand();
                            }
                        }
                        buildShape(radioSelected);
                        refresh(model);
                    }
                }
            }
        });
        //#endregion

        /*To prevent ghost components*/
        this.removeAll();
        add(locX);
        add(locY);
        add(radioRectangle);
        add(radioPolygon);
        add(radioCircle);
        add(radioText);
        add(textField);
        add(filledButton);
        add(submitButton);
        add(strokedButton);
        add(enterCoordinate);
        add(heightField);
        add(widthField);
        

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

    public void buildShape(String shapeSelected) {
        switch(shapeSelected) {
            case "Rectangle" :
                buildRectangle();
                break;
            case "Circle" :
                buildCircle();
                break;
            case "Text" :
                buildText();
                break;
            case "Polygon" :
                buildPolygone();
                break;
            default:
                break;
        }
    }

    private void buildRectangle() {
        Color fillColor = Color.BLACK;
        Color strokeColor = Color.BLACK;
        boolean isFilled = false;
        boolean isStroked = false;

        if (enterCoordinate.isSelected() && !locX.getText().equals("") && !locY.getText().equals(""))
            coordinate = new Point(Integer.parseInt(locX.getText()),Integer.parseInt(locY.getText()));
        else { 
            coordinate.x-=(Integer.parseInt(widthField.getText()))/2;
            coordinate.y-=(Integer.parseInt(heightField.getText()))/2;
        }
        SRectangle r = new SRectangle(coordinate, Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));
        if (strokedButton.getBackground() != new Color(238,238,238)) {
            strokeColor = strokedButton.getBackground();
            isStroked = true;
        }
        if (filledButton.getBackground() != new Color(238,238,238)) {
            fillColor = filledButton.getBackground();
            isFilled = true;
        }
        
        r.addAttributes(new ColorAttributes(isFilled, isStroked, fillColor, strokeColor));
        r.addAttributes(new SelectionAttributes());

        String id = new SelectionAttributes().getID();
        ((SelectionAttributes) r.getAttributes(id)).select();
        this.model.add(r);
	}

	private void buildCircle() {
        Color fillColor = Color.BLACK;
        Color strokeColor = Color.BLACK;
        boolean isFilled = false;
        boolean isStroked = false;

        if (enterCoordinate.isSelected() && !locX.getText().equals("") && !locY.getText().equals(""))
            coordinate = new Point(Integer.parseInt(locX.getText()),Integer.parseInt(locY.getText()));
        else { 
            coordinate.x-=Integer.parseInt(widthField.getText());
            coordinate.y-=Integer.parseInt(heightField.getText());
        }
        SCircle c = new SCircle(coordinate, Integer.parseInt(widthField.getText()));
        if (strokedButton.getBackground() != new Color(238,238,238)) {
            strokeColor = strokedButton.getBackground();
            isStroked = true;
        }
        if (filledButton.getBackground() != new Color(238,238,238)) {
            fillColor = filledButton.getBackground();
            isFilled = true;
        }
        
        c.addAttributes(new ColorAttributes(isFilled, isStroked, fillColor, strokeColor));
        c.addAttributes(new SelectionAttributes());

        String id = new SelectionAttributes().getID();
        ((SelectionAttributes) c.getAttributes(id)).select();
        this.model.add(c);
	}

	private void buildText() {
        Color fillColor = Color.BLACK;
        Color strokeColor = Color.BLACK;
        boolean isFilled = false;
        boolean isStroked = false;

        if (enterCoordinate.isSelected() && !locX.getText().equals("") && !locY.getText().equals(""))
            coordinate = new Point(Integer.parseInt(locX.getText()),Integer.parseInt(locY.getText()));
        else { 
            coordinate.x-=Integer.parseInt(widthField.getText());
            coordinate.y-=Integer.parseInt(heightField.getText());
        }
        SText t = new SText(coordinate, textField.getText());
        if (strokedButton.getBackground() != new Color(238,238,238)) {
            strokeColor = strokedButton.getBackground();
            isStroked = true;
        }
        if (filledButton.getBackground() != new Color(238,238,238)) {
            fillColor = filledButton.getBackground();
            isFilled = true;
        }
        
        t.addAttributes(new ColorAttributes(isFilled, isStroked, fillColor, strokeColor));
        t.addAttributes(new FontAttributes());
        t.addAttributes(new SelectionAttributes());

        String id = new SelectionAttributes().getID();
        ((SelectionAttributes) t.getAttributes(id)).select();
        this.model.add(t);
	}

	private void buildPolygone() {
        System.out.println("Polygone");
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