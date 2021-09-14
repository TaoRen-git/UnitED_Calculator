package united;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates a Panel consisting of a JTextField to display the history of expressions.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class HistoryPanel extends JPanel
{

  private static final long serialVersionUID = 1L;
  private ArrayList<String> expressionList;
  private String expression;
  private JTextArea historyArea;
  private JScrollPane scrollingArea;

  /**
   * Constructs a HistoryPanel.
   */
  public HistoryPanel()
  {
    expression = "";
    expressionList = new ArrayList<>();
    historyArea = new JTextArea();
    historyArea.setEditable(false);
    scrollingArea = new JScrollPane(historyArea);
    scrollingArea.setPreferredSize(new Dimension(200, 450));
    scrollingArea.setAlignmentX(LEFT_ALIGNMENT);
    add(scrollingArea);
  }

  /**
   * Adds another line of text to the history display.
   * 
   * @param text
   *          is the line of text to add.
   */
  public void addText(String newExpression)
  {
    expressionList.add(newExpression);
    expression = "";

    for (String eachExpression : expressionList)
    {
      expression += eachExpression + "\n";
    }

    historyArea.setText(expression);
  }

  /**
   * Adds another line of text to the history display.
   * 
   * @param text
   *          is the line of text to add.
   */
  public String getText()
  {
    return historyArea.getText();
  }

  /**
   * Returns the list of expressions.
   * 
   * @return the list of expressions.
   */
  public ArrayList<String> getexpressionList()
  {
    return expressionList;
  }
}
