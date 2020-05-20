package graphics.extensions;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;*/

public class Menu {

  public JMenuBar createMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem addShapeItem = new JMenuItem("Ajouter une forme...");
    JMenuItem playGameItem = new JMenuItem("Jouer au jeu");

    menu.add(addShapeItem);
    menu.addSeparator();
    menu.add(playGameItem);

    menuBar.add(menu);
    return menuBar;
  }
  /*
  public void colorMenu() {
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
