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
    menu.setMnemonic('M');

    JMenuItem addShapeItem = new JMenuItem("Ajouter une forme...");
    addShapeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_DOWN_MASK));
    addShapeItem.setMnemonic('A');

    JMenuItem playGameItem = new JMenuItem("Jouer au jeu");
    playGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
    playGameItem.setMnemonic('A');

    JMenuItem paintItem = new JMenuItem("Dessin libre");
    paintItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
    paintItem.setMnemonic('P');

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

    paintItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new Paint();
        }
    });

    menu.add(addShapeItem);
    menu.add(paintItem);
    menu.add(playGameItem);

    menuBar.add(menu);

    return menuBar;
  }
}
