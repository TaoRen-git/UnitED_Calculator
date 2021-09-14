package united;

/**
 * Utility Class to calculate operations.
 * 
 * @author Team02
 *
 */
public class CalculateUtility
{

  /**
   * Returns the calculated value between two operands given its units and operator.
   * 
   * @param leftOperand
   *          is the left operand.
   * @param rightOperand
   *          is the right operand.
   * @param operator
   *          is the operator that determines the type of calculation.
   * @param unit1
   *          is the left operand's units.
   * @param unit2
   *          is the right operand's units.
   * @return a calculated value.
   */
  public static double calculate(Double leftOperand, Double rightOperand, Integer operator,
      String unit1, String unit2)
  {
    UnitUtility utility = new UnitUtility();
    double result = 0;
    switch (operator)
    {
      case DisplayInputField.ADD:
        if (utility.needConversion(unit1, unit2))
        {
          result = UnitConversion.calculateConvertedUnits(unit1, unit2, leftOperand, rightOperand,
              true);
        }
        else
        {
          result = leftOperand + rightOperand;
        }
        break;
      case DisplayInputField.SUBTRACT:
        if (utility.needConversion(unit1, unit2))
        {
          result = UnitConversion.calculateConvertedUnits(unit1, unit2, leftOperand, rightOperand,
              false);
        }
        else
        {
          result = leftOperand - rightOperand;
        }
        break;
      case DisplayInputField.MULTI:
        result = leftOperand * rightOperand;
        break;
      case DisplayInputField.DIVIDE:
        result = leftOperand / rightOperand;
        break;
    }
    return result;
  }

}
