package united;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Slide out panel that displays a history of calculations.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class HistoryDisplay extends JFrame
{

  private static final long serialVersionUID = 1L;
  private JLabel label;
  private JPanel panel;
  private JTextArea history;
  private JScrollPane scrollingArea;
  private ArrayList<String> expressionList;
  private String expression;
  private Timer timer;
  private ActionListener actionListener;
  private HistoryPanel historyArea;

  /**
   * Creates a history display.
   * 
   * @param historyArea
   *          is the panel consisting of the text field to display the history expressions.
   * @param timer
   *          is timer used to animate the history display.
   * @param actionListener
   *          is the Action Listener.
   */
  public HistoryDisplay(HistoryPanel historyArea, Timer timer, ActionListener actionListener)
  {
    setLayout(new BorderLayout());
    setSize(300, 525);
    setUndecorated(true);
    this.timer = timer;
    this.actionListener = actionListener;
    this.historyArea = historyArea;
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 250, 0));

    JButton button = createCloseButton();
    panel.add(button);
    button.setAlignmentX(RIGHT_ALIGNMENT);
    add(panel, BorderLayout.EAST);

    add(historyArea, BorderLayout.CENTER);

  }

  /**
   * Creates the button to close the history display.
   * 
   * @return the close button.
   */
  private JButton createCloseButton()
  {
    JButton closeButton = new JButton("<");
    closeButton.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
    closeButton.setBackground(Color.LIGHT_GRAY);
    closeButton.setPreferredSize(new Dimension(50, 50));
    closeButton.addActionListener(actionListener);
    return closeButton;
  }

}
