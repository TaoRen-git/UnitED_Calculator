package united;

import java.util.Queue;

/**
 * Utility class for determining operators.
 * @author Team02
 * @version 4/28/2020
 */
public class OperatorUtility
{

  public static final String[] OP_BUTTONS = {"+", "\u2212", "\u00d7", "\u00f7"};
  public static final String[] OP_KEYBOARD = {"+", "/", "*", "-"};

  /**
   * Sets the operator to its respective sign.
   * 
   * @param command
   *          is the command to set the operator to.
   */
  public static void setOperator(String command, Queue<Integer> operators)
  {
    Integer operator = 0;
    switch (command)
    {
      case "+":
        operator = DisplayInputField.ADD;
        break;
      case "\u2212":
        operator = DisplayInputField.SUBTRACT;
        break;
      case "-":
        operator = DisplayInputField.SUBTRACT;
        break;
      case "\u00d7":
        operator = DisplayInputField.MULTI;
        break;
      case "*":
        operator = DisplayInputField.MULTI;
        break;
      case "\u00f7":
        operator = DisplayInputField.DIVIDE;
        break;
      case "/":
        operator = DisplayInputField.DIVIDE;
        break;
    }

    if (operator != 0)
    {
      operators.add(operator);
    }
  }

  /**
   * Determines if a given string is an operator.
   * 
   * @param text
   *          is the given string.
   * @param operations
   *          is the list of valid operators.
   * @return true is the string is an operator.
   */
  public static boolean isOperator(String text, String[] operations)
  {
    for (int i = 0; i < operations.length; i++)
    {
      if (text.equals(operations[i]))
      {
        return true;
      }
    }
    return false;
  }

}
