package graphics.extensions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Author VALLERICH Vincent
 */
public class Menu {

  /**
   * 
   * @return the JMenuBar to the JRame
   */
  public JMenuBar createMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem addShapeItem = new JMenuItem("Ajouter une forme...");
    JMenuItem playGameItem = new JMenuItem("Jouer au jeu");

    menu.add(addShapeItem);
    menu.addSeparator();
    menu.add(playGameItem);

    /**
     * Implements ActionListener to buttons
     */
    playGameItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Robot keyPressedEmulate;
          try {
            /**
             * Emulate ctrl+g to access to the game
             */
            keyPressedEmulate = new Robot();
            keyPressedEmulate.keyPress(17);
            keyPressedEmulate.keyPress(71);
          } catch (AWTException e1) {
            e1.printStackTrace();
          }
        }
    });

    menuBar.add(menu);
    return menuBar;
  }
  
  /*public void colorMenu() {
    Display display = new Display();
    Shell shell = new Shell(display);

    ColorDialog dlg = new ColorDialog(shell);
    RGB rgb = dlg.open();
    if (rgb != null) {
      Color color = new Color(shell.getDisplay(), rgb);

      System.out.println(color.getRGB());

      color.dispose();
    }

    display.dispose();
  }*/
}
