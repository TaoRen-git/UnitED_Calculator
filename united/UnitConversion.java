package united;

/**
 * Utility Class that performs conversions between units.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class UnitConversion
{

  /**
   * Adds or subtracts the left and right operand depending the converted unit.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @param leftOperand
   *          is the left operand that corresponds with the first unit.
   * @param rightOperand
   *          is the right operand that corresponds with the second unit.
   * @param add
   *          is true if the user wants to add, false if wants to subtract.
   * @return the sum or subtracted value after unit conversions.
   */
  public static double calculateConvertedUnits(String unit1, String unit2, double leftOperand,
      double rightOperand, boolean add)
  {
    UnitUtility utility = new UnitUtility();
    String type1 = utility.getUnitType(unit1);
    String type2 = utility.getUnitType(unit2);

    if (type1 == null || type2 == null)
    {
      if (add)
      {
        return leftOperand + rightOperand;
      }
      else
      {
        return leftOperand - rightOperand;
      }
    }

    String smallerUnit = utility.getSmallerUnit(unit1, unit2);
    String biggerUnit;
    double valueToBeConverted;
    boolean convertedLeftOperand = false;

    if (smallerUnit.equals(unit1))
    {
      valueToBeConverted = rightOperand;
      biggerUnit = unit2;
    }
    else
    {
      valueToBeConverted = leftOperand;
      biggerUnit = unit1;
      convertedLeftOperand = true;
    }

    double convertedValue = 0;

    switch (type1)
    {
      case "Length":
        convertedValue = lengthConversion(biggerUnit, smallerUnit, valueToBeConverted);
        break;
      case "Volume":
        convertedValue = volumeConversion(biggerUnit, smallerUnit, valueToBeConverted);
        break;
      case "Mass":
        convertedValue = massConversion(biggerUnit, smallerUnit, valueToBeConverted);
        break;
      case "Time":
        convertedValue = timeConversion(biggerUnit, smallerUnit, valueToBeConverted);
        break;
    }

    double result;
    if (add)
    {
      if (convertedLeftOperand)
      {
        result = convertedValue + rightOperand;
      }
      else
      {
        result = leftOperand + convertedValue;
      }
    }
    else
    {
      if (convertedLeftOperand)
      {
        result = convertedValue - rightOperand;
      }
      else
      {
        result = leftOperand - convertedValue;
      }
    }
    return result;
  }

  /**
   * Performs time conversions by converting the given operand to the smaller unit.
   * 
   * @param biggerUnit
   *          is the bigger unit.
   * @param smallerUnit
   *          is the smaller unit.
   * @param operand
   *          is the operand to be converted.
   * @return a converted operand value to the smaller unit.
   */
  public static double timeConversion(String biggerUnit, String smallerUnit, double operand)
  {
    double converted;
    converted = convertToSec(operand, biggerUnit);
    converted = secToX(converted, smallerUnit);

    return converted;
  }

  /**
   * Performs mass conversions by converting the given operand to the smaller unit.
   * 
   * @param biggerUnit
   *          is the bigger unit.
   * @param smallerUnit
   *          is the smaller unit.
   * @param operand
   *          is the operand to be converted.
   * @return a converted operand value to the smaller unit.
   */
  public static double lengthConversion(String biggerUnit, String smallerUnit, double operand)
  {
    double converted;
    converted = convertToMM(operand, biggerUnit);
    converted = mmToX(converted, smallerUnit);

    return converted;
  }

  /**
   * Performs mass conversions by converting the given operand to the smaller unit.
   * 
   * @param biggerUnit
   *          is the bigger unit.
   * @param smallerUnit
   *          is the smaller unit.
   * @param operand
   *          is the operand to be converted.
   * @return a converted operand value to the smaller unit.
   */
  public static double massConversion(String biggerUnit, String smallerUnit, double operand)
  {
    double converted;
    converted = convertToMG(operand, biggerUnit);
    converted = mgToX(converted, smallerUnit);
    return converted;
  }

  /**
   * Performs volume conversions by converting the given operand to the smaller unit.
   * 
   * @param biggerUnit
   *          is the bigger unit.
   * @param smallerUnit
   *          is the smaller unit.
   * @param operand
   *          is the operand to be converted.
   * @return a converted operand value to the smaller unit.
   */
  public static double volumeConversion(String biggerUnit, String smallerUnit, double operand)
  {
    double converted;
    converted = convertToML(operand, biggerUnit);
    converted = mlToX(converted, smallerUnit);
    return converted;
  }

  /* PRIVATE HELPER METHODS */

  /**
   * Converts all length units to milligrams as the smallest unit.
   * 
   * @param x
   *          is the length value.
   * @param unit
   *          is given bigger unit.
   * @return x converted into mg.
   */
  private static double convertToMG(double x, String unit)
  {
      return UnitUtility.getUnitTypesMass().get(unit) * x;
  }

  /**
   * Converts milligrams to the desire length unit value.
   * 
   * @param d
   *          is the milligram value.
   * @param unit
   *          is the desired unit.
   * @return milligrams converted into the desire units.
   */
  private static double mgToX(double d, String unit)
  {
      return d / UnitUtility.getUnitTypesMass().get(unit);
  }

  /**
   * Converts any time unit to seconds.
   * 
   * @param x
   *          is the time value.
   * @param unit
   *          is the unit type of x.
   * @return x converted into seconds.
   */
  private static double convertToSec(double x, String unit)
  {
      return UnitUtility.getUnitTypesTime().get(unit) * x;

  }

  /**
   * Converts seconds into the desired time unit.
   * 
   * @param d
   *          is the second value.
   * @param unit
   *          is the desired unit.
   * @return seconds converted to the desired unit.
   */
  private static double secToX(double d, String unit)
  {
      return d / UnitUtility.getUnitTypesTime().get(unit);

  }

  /**
   * Converts any volume unit to mL.
   * 
   * @param x
   *          is the volume value.
   * @param unit
   *          is the unit type of x.
   * @return x converted into mL.
   */
  private static double convertToML(double x, String unit)
  {
      return UnitUtility.getUnitTypesVolume().get(unit) * x;

  }

  /**
   * Converts mL into the desired time unit.
   * 
   * @param d
   *          is the mL value.
   * @param unit
   *          is the desired unit.
   * @return mL converted to the desired unit.
   */
  private static double mlToX(double d, String unit)
  {
      return d / UnitUtility.getUnitTypesVolume().get(unit);

  }

  /**
   * Converts all length units to millimeter as the smallest unit.
   * 
   * @param x
   *          is the length value.
   * @param unit
   *          is given bigger unit.
   * @return x converted into mm.
   */
  private static double convertToMM(double x, String unit)
  {
      return UnitUtility.getUnitTypesLength().get(unit) * x;

  }

  /**
   * Converts millimeter to the desire length unit value.
   * 
   * @param d
   *          is the millimeter value.
   * @param unit
   *          is the desired unit.
   * @return millimeter converted into the desire units.
   */
  private static double mmToX(double d, String unit)
  {
      return d / UnitUtility.getUnitTypesLength().get(unit);
  }
}
