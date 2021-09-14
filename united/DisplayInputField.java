package united;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * DisplayInputField encapsulates interactions between the display and input fields.
 * 
 * @author Team02
 * @version 4/15/2020
 */
public class DisplayInputField extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;
  public static final int ADD = 1;
  public static final int SUBTRACT = 2;
  public static final int MULTI = 3;
  public static final int DIVIDE = 4;
  public static final int INVERT = 5;
  private DisplayField display;
  private InputField input;
  private String currentExpression;
  private Queue<Double> operands;
  private Queue<Integer> operators;
  private Queue<String> units;
  private boolean invalidCalculation;
  private UnitUtility uu;
  private HistoryPanel history;

  /**
   * Constructs a panel of display and input fields.
   */
  public DisplayInputField(HistoryPanel history)
  {
    super();
    setLayout(new GridLayout(2, 1, 40, 20));
    display = new DisplayField();

    uu = new UnitUtility();
    input = new InputField(uu);

    currentExpression = "";
    operands = new LinkedList<>();
    operators = new LinkedList<>();
    units = new LinkedList<>();
    invalidCalculation = false;

    this.history = history;

    add(display);
    add(input);
  }

  /**
   * Implement buttons that will respond to their respective clicks.
   * 
   * @param event
   *          is the type of click.
   */
  @Override
  public void actionPerformed(ActionEvent event)
  {
    String command = event.getActionCommand();

    cancelCommand(command);
    resetCommand(command);
    signButtonCommand(command);
    numericKeyPadCommand(command);
    operatorCommand(command);
    equalsCommand(command);
    inputChangeButtons(command);
    backspaceCommand(command);
  }

  /**
   * Performs actions on exponent and invert buttons.
   * 
   * @param command
   *          is the command for either exponent or invert buttons.
   */
  public void inputChangeButtons(String command)
  {
    if (command.equals("1/x"))
    {
      if (input.getText().equals(""))
      {
        JOptionPane.showMessageDialog(this, "Please put a base number into the input field first.");
      }
      else
      {
        input.setText("1/" + input.getText());
      }
    }

    if (command.equals("<html>X<sup>Y</sup></html>"))
    {

      if (input.getText().equals(""))
      {
        JOptionPane.showMessageDialog(this, "Please put a base number into the input field first.");
      }
      else
      {
        String response = JOptionPane.showInputDialog(this, "What is the exponent?", "1");

        if (response == null)
        {
          response = "1";
        }
        else
        {
          for (int i = 0; i < response.length(); i++)
          {
            if (!Character.isDigit(response.charAt(i)))
            {

              JOptionPane.showMessageDialog(this, "Input value must be an integer", null,
                  JOptionPane.ERROR_MESSAGE);

              response = JOptionPane.showInputDialog(this, "What is the exponent?",
                  JOptionPane.INFORMATION_MESSAGE);
            }
          }
        }

        Long exponent = Long.parseLong(response);
        Long result = Long.parseLong(input.getText());
        Long base = Long.parseLong(input.getText());

        for (int i = 1; i < exponent; i++)
        {
          result *= base;
        }

        if (result <= 0)
        {
          JOptionPane.showMessageDialog(this,
              "Value cannot be represented in our application. Exponent value is too high. Choose another value.",
              null, JOptionPane.ERROR_MESSAGE);
          input.setText(String.valueOf(base));
        }
        else
        {
          {
            input.setText(String.valueOf(result));
          }
        }
      }
    }
  }

  /**
   * Changes the color of the display field.
   * 
   * @param color
   *          is the color to change to.
   */
  public void setDisplayColor(Color color)
  {
    display.getDisplay().setBackground(color);
  }

  /**
   * Returns the current color of the display field.
   * 
   * @return the current color of the display field.
   */
  public Color getCurrentColor()
  {
    return display.getDisplay().getBackground();
  }

  /**
   * Makes the digits in the input text field negative or positive.
   * 
   * @param command
   *          is command when the sign button is pressed.
   */
  public void signButtonCommand(String command)
  {
    if (input.getText().length() != 0 && command.equals("\u00b1"))
    {
      if (input.getText().indexOf("-") == -1)
      {
        input.setText("-" + input.getText());
      }
      else
      {
        input.setText(input.getText().substring(1, input.getText().length()));
      }
    }
  }

  /**
   * Displays numeric digits on the input text field when the respective button is pressed.
   * 
   * @param command
   *          is the command when one of the numeric digits is pressed.
   */
  public void numericKeyPadCommand(String command)
  {
    for (int i = 0; i < 10; i++)
    {
      if (command.equals(String.format("%d", i)))
      {
        input.setText(input.getText() + command);
      }
    }
  }

  /**
   * Clears the input field when the cancel button is pressed.
   * 
   * @param command
   *          is the command when "C" is pressed.
   */
  public void cancelCommand(String command)
  {
    if (command.equals("C"))
    {
      input.resetInput();
    }
  }

  /**
   * Resets all display and input fields.
   * 
   * @param command
   *          is the command when "R" is pressed.
   */
  public void resetCommand(String command)
  {
    if (command.equals("R"))
    {
      input.resetInput();
      operands = new LinkedList<>();
      operators = new LinkedList<>();
      units = new LinkedList<>();
      currentExpression = "";
      display.resetUnitList();
      display.unlockResult();
      display.setText("");
    }
  }

  /**
   * Displays the input with an operator when an operator button is pressed.
   * 
   * @param command
   *          is the command when one of the valid operators are pressed.
   */
  public void operatorCommand(String command)
  {
    if (OperatorUtility.isOperator(command, OperatorUtility.OP_BUTTONS))
    {
      String displayContent = input.getAllInput();
      // If not digit input, check if input is an expression.
      if (!input.hasValidDigitInput())
      {
        ExpressionParser parser = new ExpressionParser(input.getText());
        parser.parse();
        if (!parser.isValidExpression())
        {
          return;
        }
        parser.evaluateExpression();
        if (parser.isInvalidCalculation())
        {
          return;
        }
        units.add(parser.getResultUnit());
        operands.add(parser.getResultOperand());
        displayContent = parser.getFinalResult();

      }
      else
      {
        units.add(input.getUnit());
        if (!input.getText().contains("/"))
        {
          operands.add(Double.parseDouble(input.getText()));
        }
        else
        {
          operands.add(parseFraction());
        }
      }

      OperatorUtility.setOperator(command, operators);
      currentExpression += input.getAllInput() + " " + command + " ";

      // Running Calculations
      if (operands.size() > 1)
      {
        displayValidCalculations();
        if (!invalidCalculation)
        {
          display.setText(display.getText() + " " + display.getUnit() + " " + command);
        }
        invalidCalculation = false;
      }
      else
      {
        display.setText(displayContent + " " + command);
      }
      input.resetInput();
    }

  }

  /**
   * Performs actions on the backspace command. Deletes the most recent digit.
   * @param command is the backspace command.
   */
  public void backspaceCommand(String command)
  {
    if (command.equals("\u232B") && input.getText().length() > 0)
    {
      input.setText(input.getText().substring(0, input.getText().length() - 1));
    }
  }

  /**
   * Calculates the expression when the equals button is pressed.
   * 
   * @param command
   *          is the command when "=" is pressed.
   */
  public void equalsCommand(String command)
  {
    if (command.equals("="))
    {
      ExpressionParser parser = new ExpressionParser(input.getText());
      if (!input.hasValidDigitInput())
      {
        parser.parse();
        if (!parser.isValidExpression())
        {
          JOptionPane.showMessageDialog(null,
              "Expression cannnot be evaluated. Please enter a valid expression.",
              "Invalid Expression", JOptionPane.OK_OPTION);
          return;
        }
        parser.evaluateExpression();
        if (parser.isInvalidCalculation())
        {
          return;
        }
        units.add(parser.getResultUnit());
        operands.add(parser.getResultOperand());
      }
      else
      {
        units.add(input.getUnit());

        if (!input.getText().contains("/"))
        {
          operands.add(Double.parseDouble(input.getText()));
        }
        else
        {
          operands.add(parseFraction());
        }
        currentExpression += input.getAllInput();
      }

      if (operands.size() == 2)
      {
        displayValidCalculations();

        if (!invalidCalculation)
        {
          operands = new LinkedList<>();
          operators = new LinkedList<>();
          units = new LinkedList<>();
          currentExpression += " = " + display.getAllDisplay();
        }
      }
      else
      {
        display.setText(parser.getFinalResult());
        currentExpression += input.getAllInput() + " = " + parser.getFinalResult();
        operands = new LinkedList<>();
        operators = new LinkedList<>();
        units = new LinkedList<>();
      }

      input.resetInput();
      history.addText(currentExpression);
      currentExpression = "";
      display.lockResult();
    }
  }

  /* EQUALS AND OPERATOR HELPER METHODS, PUBLIC FOR TESTING PURPOSES */

  /**
   * Parses a string representation of a fraction into a Double.
   * 
   * @return a Double representation of a fraction.
   */
  public Double parseFraction()
  {
    String firstNum = "";
    int div = 0;
    String secondNum = "";
    for (int i = 0; i < input.getText().length(); i++)
    {
      if (input.getText().charAt(i) == '/')
      {
        div++;
      }
      else
      {

        if (div == 0)
        {
          firstNum += input.getText().charAt(i);
        }
        else
        {
          secondNum += input.getText().charAt(i);

        }
      }

    }

    Double value = Double.parseDouble(firstNum) / Double.parseDouble(secondNum);

    Math.round(value);
    return value;
  }

  /**
   * Returns the units in the unit drop down menu associated with the display field.
   * 
   * @return a String representation of the units.
   */
  public String getDisplayUnits()
  {
    return display.getUnit();
  }

  /**
   * Returns the Display JTextField.
   * 
   * @return the Display JTextField.
   */
  public JTextField getDisplay()
  {
    return display.getDisplay();
  }

  /**
   * Displays valid calculations with its appropriate units. Displays an error message if a
   * calculation cannot be performed.
   */
  private void displayValidCalculations()
  {
    Double leftOperand = operands.remove();
    Double rightOperand = operands.remove();
    Integer operator = operators.remove();
    String unit1 = units.remove();
    String unit2 = units.remove();
    if (uu.canPerformUnitCalculation(unit1, unit2, operator))
    {
      double result = CalculateUtility.calculate(leftOperand, rightOperand, operator, unit1, unit2);
      String formattedUnits = uu.getFormattedUnit(unit1, unit2, operator);
      String displayContents;
      if (Math.round(result * 1e5) / 1e5 % 1 == 0)
      {
        int intVal = (int) Math.round(result);
        displayContents = String.format("%d %s", intVal, formattedUnits);
      }
      else
      {
        displayContents = String.format("%.2f %s", result, formattedUnits);
      }
      operands.add(result);
      units.add(formattedUnits);
      if (operator == MULTI || operator == DIVIDE)
      {
        display.setUnit(formattedUnits);
      }
      display.setText(displayContents);
    }
    else
    {
      JOptionPane.showMessageDialog(null,
          "Error: " + unit1 + " and " + unit2
              + " are incompatible unit types. Please enter different units.",
          "Incompatible Unit Types", JOptionPane.OK_OPTION);
      operands.add(leftOperand);
      units.add(unit1);
      operators.add(operator);
      invalidCalculation = true;
    }
  }

  /**
   * Returns the queue of operators.
   * 
   * @return the queue of operators.
   */
  public Queue<Integer> getOperators()
  {
    return operators;
  }

  /**
   * Returns the current expression that is being evaluated.
   * 
   * @return the current expression that is being evaluated.
   */
  public String getCurrExpression()
  {
    return currentExpression;
  }

  /**
   * Sets the current expression to a given String.
   * 
   * @param value
   *          is the String to set the current expression to.
   */
  public void setCurrentExpression(String value)
  {
    currentExpression = value;
  }

  /**
   * Returns the current text in the input field.
   * 
   * @return the current text in the input field.
   */
  public String getInputFieldText()
  {
    return input.getText();
  }

  /**
   * Sets the text in the input field to a given text.
   * 
   * @param text
   *          is the text to be set in the input field.
   */
  public void setInputText(String text)
  {
    input.setText(text);
  }

  /**
   * Returns the queue of units.
   * 
   * @return the queue of units.
   */
  public Queue<String> getUnits()
  {
    return units;
  }

  /**
   * Sets the units in the unit drop down menu associated with the input field.
   * 
   * @param unit
   *          is the unit to be set.
   */
  public void setUnits(String unit)
  {
    input.setUnits(unit);
  }

  /**
   * Returns the operands.
   * 
   * @return the operands.
   */
  public Queue<Double> getOperands()
  {
    return operands;
  }

}
