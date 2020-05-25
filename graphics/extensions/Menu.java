package graphics.extensions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Author VALLERICH Vincent
 */
public class Menu {
  Robot keyPressedEmulate = null;

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
    playGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.SHIFT_DOWN_MASK));


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
            /**
             * Emulate g to access to the game
             */
            try {
                keyPressedEmulate = new Robot();
                keyPressedEmulate.setAutoDelay(20);
                keyPressedEmulate.keyPress(KeyEvent.VK_G);
                keyPressedEmulate.keyRelease(KeyEvent.VK_G);
            } catch (AWTException ei) {
              ei.printStackTrace();
            }
        }
    });

    menu.add(addShapeItem);
    menu.addSeparator();
    menu.add(playGameItem);

    menuBar.add(menu);

    return menuBar;
  }
}
