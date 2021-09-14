package united;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Creates a panel with a button that opens up the history display.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class HistoryOpenButton extends JPanel
{

  private static final long serialVersionUID = 1L;
  private ActionListener actionListener;

  /**
   * Constructs a panel with a button.
   * 
   * @param actionListener
   *          will be implemented to respond to mouse clicks.
   */
  public HistoryOpenButton(ActionListener actionListener)
  {
    super();
    this.actionListener = actionListener;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JButton button = createButton();
    add(button);
    setBorder(BorderFactory.createEmptyBorder(300, 0, 250, 0));
  }
  
  /**
   * Returns a button with a > symbol.
   * 
   * @return a button to open up the history display.
   */
  private JButton createButton()
  {
    JButton button = new JButton(">");
    Font font = new Font("DejaVu Sans", Font.BOLD, 20);
    button.setFont(font);
    button.setBackground(Color.LIGHT_GRAY);
    button.setPreferredSize(new Dimension(50, 50));
    button.addActionListener(actionListener);
    return button;
  }
}
