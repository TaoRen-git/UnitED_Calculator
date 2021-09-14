package united;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * Used to parse full expressions.
 * 
 * @author Team02
 * @version 4/21/2020
 */
public class ExpressionParser
{
  private String unit;
  private Double operand;
  private Queue<Double> operands;
  private Queue<Integer> operators;
  private Queue<String> units;
  private String text;
  private boolean invalidCalculation;
  private boolean validExpression;

  /**
   * Default Constructor
   * 
   * @param input
   *          - current input string.
   */
  public ExpressionParser(String input)
  {
    text = input;
    unit = "";
    operand = 0.0;
    operands = new LinkedList<>();
    operators = new LinkedList<>();
    units = new LinkedList<>();
    invalidCalculation = false;
    validExpression = true;
  }

  /**
   * Parses a full expression.
   */
  public void parse()
  {
    for (int i = 0; i < text.length(); i++)
    {

      if (Character.isDigit(text.charAt(i)) || text.charAt(i) == '-')
      {
        String number = "";
        while (i < text.length() && (Character.isDigit(text.charAt(i)) || text.charAt(i) == '-'
            || text.charAt(i) == '/' || text.charAt(i) == '^'))
        {
          number += text.charAt(i);
          i++;
        }
        if (number.contains("/"))
        {
          number = parseFraction(number);
        }
        if (number.contains("^"))
        {
          number = parsePower(number);
        }
        i--;
        if (!number.equals("-"))
        {
          operands.add(Double.parseDouble(number));
        }
        else
        {
          OperatorUtility.setOperator(number, operators);
        }
      }
      else if (Character.isLetter(text.charAt(i)))
      {
        String unit = "";
        while (i < text.length() && Character.isLetter(text.charAt(i)))
        {
          unit += text.charAt(i);
          i++;
        }
        i--;
        units.add(unit);
      }
      else if (OperatorUtility.isOperator(text.substring(i, i + 1), OperatorUtility.OP_KEYBOARD))
      {
        OperatorUtility.setOperator(text.substring(i, i + 1), operators);
      }

    }

  }

  /**
   * Determines if an expression is valid or invalid.
   * 
   * @return returns true if expression is valid.
   */
  public boolean isValidExpression()
  {
    return (units.size() == 0 || units.size() == operands.size()) && operators.size() != 0
        && operators.size() + 1 == operands.size() && validExpression;
  }

  /**
   * Parses a fraction string.
   * 
   * @param num
   *          - the current fraction string.
   * @return the parsed number as a string.
   */
  public String parseFraction(String num)
  {
    String firstNum = "";
    int div = 0;
    String secondNum = "";
    for (int i = 0; i < num.length(); i++)
    {
      if (num.charAt(i) == '/')
      {
        div++;
      }
      else
      {

        if (div == 0)
        {
          firstNum += num.charAt(i);
        }
        else
        {
          secondNum += num.charAt(i);

        }
      }

    }

    Double value = Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
    Math.round(value);
    return String.format("%f", value);
  }

  /**
   * Parses an integer power expression.
   * 
   * @param num
   *          - the current integer power expression being parsed.
   * @return the parsed number.
   */
  public String parsePower(String num)
  {
    String[] split = num.split("\\^");
    if (split.length != 2)
    {
      validExpression = false;
      return "0";
    }
    Double base = Double.valueOf(split[0]);
    Double power = Double.valueOf(split[1]);
    Double result = base;
    for (int i = 1; i < power; i++)
    {
      result *= base;
    }

    return String.format("%f", result);
  }

  /**
   * Evaluates the current expression.
   */
  public void evaluateExpression()
  {

    Stack<Double> operandStack = new Stack<>();
    Stack<String> unitStack = new Stack<>();
    List<Double> operandList = new ArrayList<>(operands);
    List<String> unitList = new ArrayList<>(units);
    for (int i = operands.size() - 1; i >= 0; i--)
    {
      operandStack.push(operandList.get(i));
    }
    if (unitList.size() > 1)
    {
      for (int i = operands.size() - 1; i >= 0; i--)
      {
        unitStack.push(unitList.get(i));
      }
    }
    while (operandStack.size() > 1)
    {
      Double leftOperand = operandStack.pop();
      Double rightOperand = operandStack.pop();
      String unit1 = "";
      String unit2 = "";
      if (unitStack.size() > 1)
      {
        unit1 = unitStack.pop();
        unit2 = unitStack.pop();
      }
      Integer operator = operators.remove();
      UnitUtility utility = new UnitUtility();

      if (utility.canPerformUnitCalculation(unit1, unit2, operator))
      {
        double result = CalculateUtility.calculate(leftOperand, rightOperand, operator, unit1,
            unit2);
        String formattedUnits = utility.getFormattedUnit(unit1, unit2, operator);
        operandStack.push(result);
        unitStack.push(formattedUnits);
      }
      else
      {
        JOptionPane.showMessageDialog(null,
            "Error: " + unit1 + " and " + unit2
                + " are incompatible unit types. Please enter an expression with valid units.",
            "Incompatible Unit Types", JOptionPane.OK_OPTION);
        invalidCalculation = true;
        return;
      }
    }
    operand = operandStack.pop();
    unit = unitStack.pop();
  }

  /**
   * Returns the operand that results from calculations.
   * 
   * @return the result operand.
   */
  public Double getResultOperand()
  {
    return operand;
  }

  /**
   * Returns the unit that results from calculations.
   * 
   * @return the result unit.
   */
  public String getResultUnit()
  {
    return unit;
  }

  /**
   * Returns a boolean that determines if a calculation was invalid.
   * 
   * @return a boolean that determines if a calculation was invalid.
   */
  public boolean isInvalidCalculation()
  {
    return invalidCalculation;
  }

  /**
   * Returns a formatted String of the result operand and result unit.
   * 
   * @return a formatted String of the results.
   */
  public String getFinalResult()
  {
    String displayContents;
    if (Math.round(operand * 1e5) / 1e5 % 1 == 0)
    {
      int intVal = (int) Math.round(operand);
      displayContents = String.format("%d %s", intVal, unit);
    }
    else
    {
      displayContents = String.format("%.2f %s", operand, unit);
    }
    return displayContents;
  }

  /* HELPER METHODS USED FOR TESTING */

  /**
   * Returns the queue of operands.
   * 
   * @return the queue of operands.
   */
  public Queue<Double> getOperands()
  {
    return operands;
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
   * Returns the queue of operators.
   * 
   * @return the queue of operators.
   */
  public Queue<Integer> getOperators()
  {
    return operators;
  }

}
