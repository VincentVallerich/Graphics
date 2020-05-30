package graphics.extensions;

import javax.swing.JFrame;
import java.awt.Dimension;

/**
 * Author VALLERICH Vincent
 */
@SuppressWarnings ("serial")
public class ShapeMenu extends JFrame{
    ShapesMenuView smview;

    public ShapeMenu(Object model) {
        super("Ajouter une forme");
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        
        this.smview = new ShapesMenuView(model);
        this.smview.setPreferredSize(new Dimension(560, 200));
        this.setLocation(250, 10);

        this.add(this.smview, java.awt.BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}