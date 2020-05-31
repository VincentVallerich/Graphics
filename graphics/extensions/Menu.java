package graphics.extensions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import graphics.shapes.SCollection;

/**
 * Author VALLERICH Vincent
 */
public class Menu {
  /**
   * 
   * @return the JMenuBar to the JRame
   */
  public JMenuBar createMenu(Object model) {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");

    JMenuItem addShapeItem = new JMenuItem("Ajouter une forme...");
    addShapeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_DOWN_MASK));
    
    JMenuItem playGameItem = new JMenuItem("Jouer au jeu");
    playGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.VK_CONTROL));

    JMenuItem paint = new JMenuItem("Dessin libre");
    paint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.VK_CONTROL));
    

    /**
     * Implements ActionListener to buttons
     */

    addShapeItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          new ShapeMenu(model);
        }
    });

    playGameItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new Game((SCollection) model);
        }
    });

    paint.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new Paint();
        }
    });

    menu.add(addShapeItem);
    menu.add(paint);
    menu.add(playGameItem);

    menuBar.add(menu);

    return menuBar;
  }
}
