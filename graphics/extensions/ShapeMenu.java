package graphics.extensions;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;

@SuppressWarnings ("serial")
public class ShapeMenu extends JFrame{
    ShapesMenuView smview;

    public ShapeMenu(Object model) {
        super("Ajouter une forme");
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        
        this.smview = new ShapesMenuView(model);
        this.smview.setPreferredSize(new Dimension(430, 200));
        this.setLocation(250, 10);
        
        JButton close = new JButton("Quitter");
        close.setBounds(300, 155, 90, 30);
        this.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(this.smview, java.awt.BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}