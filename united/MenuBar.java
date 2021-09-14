package united;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Creates a Menu Bar.
 * 
 * @author Team02
 */
public class MenuBar extends JMenuBar
{
  private static final long serialVersionUID = 1L;
  private ActionListener actionListener;

  /** 
   * Constructs a Menu Bar.
   * @param actionListener 
   */
  public MenuBar(ActionListener actionListener)
  {
    super();
    this.actionListener = actionListener;
    
    // File Menu
    JMenu fileMenu = new JMenu("File");
    fileMenu.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
    add(fileMenu);
    addMenuItem(fileMenu, "Print");

    // Help Menu
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
    add(helpMenu);
    addMenuItem(helpMenu, "About");
    addMenuItem(helpMenu, "Help");

    // Settings Menu
    JMenu settingMenu = new JMenu("Settings");
    settingMenu.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
    JMenu colorMenu = new JMenu("Change Color Scheme");
    addMenuItem(colorMenu, "Change Display Field Color");
    addMenuItem(colorMenu, "Change Button Color");
    addMenuItem(settingMenu, "Configure Logo");
    settingMenu.add(colorMenu);
    add(settingMenu);
  }

  /**
   * Adds MenuItems to Menus.
   * 
   * @param menu
   *          is the Menu that the item will be added to.
   * @param name
   *          is the name of the MenuItem.
   */
  private void addMenuItem(JMenu menu, String name)
  {
    JMenuItem item = new JMenuItem(name);
    menu.add(item);
    item.addActionListener(actionListener);
    item.setActionCommand(name);
  }
}
