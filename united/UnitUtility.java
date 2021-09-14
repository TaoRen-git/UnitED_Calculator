package united;

import java.util.HashMap;

import java.util.Map;

/**
 * Creates a Unit Utility Object.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class UnitUtility
{

  private final String[] powers = {"\u2070", "\u2071", "\u00b2", "\u00b3", "\u2074", "\u2075",
      "\u2076", "\u2077", "\u2078", "\u2079"};
  private static Map<String, String> unitTypes = new HashMap<>();
  private static Map<String, Double> length = new HashMap<>();
  private static Map<String, Double> mass = new HashMap<>();
  private static Map<String, Double> time = new HashMap<>();
  private static Map<String, Double> volume = new HashMap<>();

  /**
   * Constructs a UnitUtility object.
   */
  public UnitUtility()
  {
    unitTypes.put("ft", "Length");
    length.put("ft", 304.8);

    unitTypes.put("gal", "Volume");
    volume.put("gal", 3785.41);

    unitTypes.put("hr", "Time");
    time.put("hr", 3600.0);

    unitTypes.put("in", "Length");
    length.put("in", 25.4);

    unitTypes.put("L", "Volume");
    volume.put("L", 1000.0);

    unitTypes.put("m", "Length");
    length.put("m", 1000.0);

    unitTypes.put("mi", "Length");
    length.put("mi", 1609244.0);

    unitTypes.put("min", "Time");
    time.put("min", 60.0);

    unitTypes.put("oz", "Mass");
    mass.put("oz", 28349.5);

    unitTypes.put("lb", "Mass");
    mass.put("lb", 453592.0);

    unitTypes.put("s", "Time");
    time.put("s", 1.0);

    unitTypes.put("g", "Mass");
    mass.put("g", 1000.0);

    unitTypes.put("m" + "\u00b2", "Area");

    unitTypes.put("ft" + "\u00b2", "Area");

    unitTypes.put("cm", "Length");
    length.put("cm", 10.0);

    unitTypes.put("mm", "Length");
    length.put("mm", 1.0);

    unitTypes.put("km", "Length");
    length.put("km", 1000000.0);

    unitTypes.put("yd", "Length");
    length.put("yd", 914.4);

    unitTypes.put("mL", "Volume");
    volume.put("mL", 1.0);

    unitTypes.put("tons", "Mass");
    mass.put("tons", 907184700.0);

    unitTypes.put("kg", "Mass");
    mass.put("kg", 1000000.0);

    unitTypes.put("mg", "Mass");
    mass.put("mg", 1.0);

    unitTypes.put("m/s", "Velocity");

    unitTypes.put("mi/hr", "Velocity");
  }

  /**
   * Determines if two units are of the same unit type but are different units, which means that
   * they need to be converted.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @return true if the two units need to be converted.
   */
  public boolean needConversion(String unit1, String unit2)
  {
    return !(unit1.equals(unit2)) && unitTypes.get(unit1).equals(unitTypes.get(unit2));
  }

  /**
   * Return the smaller unit between two units of the same type.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @return
   */
  public String getSmallerUnit(String unit1, String unit2)
  {
    String finalUnits = "";
    Double unit1Rank = 0.0;
    Double unit2Rank = 0.0;
    String unitType = unitTypes.get(unit1);
    switch (unitType)
    {
      case "Length":
        unit1Rank = length.get(unit1);
        unit2Rank = length.get(unit2);
        break;
      case "Volume":
        unit1Rank = volume.get(unit1);
        unit2Rank = volume.get(unit2);
        break;
      case "Mass":
        unit1Rank = mass.get(unit1);
        unit2Rank = mass.get(unit2);
        break;
      case "Time":
        unit1Rank = time.get(unit1);
        unit2Rank = time.get(unit2);
        break;
    }

    if (unit1Rank > unit2Rank)
    {
      finalUnits = unit2;
    }
    else
    {
      finalUnits = unit1;
    }

    return finalUnits;
  }

  /**
   * Determines if a calculation can be performed on two given units.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @param operator
   *          is the type of calculation that will be done. Each operation has its own rules.
   * @return true if a calculation can be performed on two given units.
   */
  public boolean canPerformUnitCalculation(String unit1, String unit2, Integer operator)
  {
    if (unit1.length() <= 0 && unit2.length() <= 0)
    {
      return true;
    }
    if (unit1.length() <= 0 || unit2.length() <= 0)
    {
      return false;
    }

    String type1 = unitTypes.get(unit1);
    String type2 = unitTypes.get(unit2);
    boolean result = false;
    switch (operator)
    {
      case DisplayInputField.ADD:
        if (unit1.equals(unit2))
        {
          result = true;
        }
        else if (type1 == null || type2 == null)
        {
          result = false;
        }
        else
        {
          result = type1.equals(type2);
        }
        break;
      case DisplayInputField.SUBTRACT:
        if (unit1.equals(unit2))
        {
          result = true;
        }
        else if (type1 == null || type2 == null)
        {
          result = false;
        }
        else
        {
          result = type1.equals(type2);
        }
        break;
      case DisplayInputField.MULTI:
        result = true;
        break;
      case DisplayInputField.DIVIDE:
        result = true;
        break;
    }
    return result;
  }

  /**
   * Encapsulation of formats for all types of calculated units, given the operator.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @param operator
   *          is the type of operator.
   * @return a formatted String according to the rules of the operator.
   */
  public String getFormattedUnit(String unit1, String unit2, Integer operator)
  {
    String result = "";
    switch (operator)
    {
      case DisplayInputField.ADD:
        result = formatLikeUnits(unit1, unit2);
        break;
      case DisplayInputField.SUBTRACT:
        result = formatLikeUnits(unit1, unit2);
        break;
      case DisplayInputField.MULTI:
        result = formatMultiplicationUnits(unit1, unit2);
        break;
      case DisplayInputField.DIVIDE:
        result = formatDivisionUnits(unit1, unit2);
        break;
    }
    return result;
  }

  /**
   * Returns a unit's type.
   * 
   * @param unit
   *          is the unit.
   * @return the type of the unit.
   */
  public String getUnitType(String unit)
  {
    return unitTypes.get(unit);
  }

  /**
   * Returns a power unit's base unit.
   * 
   * @param unit
   *          is the power unit.
   * @return the base unit of the power unit.
   */
  public String getBaseUnit(String powerUnit)
  {
    for (int i = 0; i < powers.length; i++)
    {
      if (powerUnit.indexOf(powers[i]) != -1)
      {
        return powerUnit.substring(0, powerUnit.length() - 1);
      }
    }
    return powerUnit;
  }

  /**
   * Returns the map of all units to their type.
   * @return the map of all units to their type.
   */
  public static Map<String, String> getUnitTypes()
  {
    return unitTypes;
  }

  /**
   * Puts a new unit with its associative type into the map.
   * @param unit is the new unit.
   * @param type is the type.
   */
  public static void setUnitType(String unit, String type)
  {
    unitTypes.put(unit, type);
  }

  /**
   * Returns a map of all of the units of type time with their respective conversion factor.
   * @return a map of all of the units of type time with their respective conversion factor.
   */
  public static Map<String, Double> getUnitTypesTime()
  {
    return time;
  }

  /**
   * Returns a map of all of the units of type length with their respective conversion factor.
   * @return a map of all of the units of type length with their respective conversion factor.
   */
  public static Map<String, Double> getUnitTypesLength()
  {
    return length;
  }

  /**
   * Returns a map of all of the units of type mass with their respective conversion factor.
   * @return a map of all of the units of type mass with their respective conversion factor.
   */
  public static Map<String, Double> getUnitTypesMass()
  {
    return mass;
  }

  /**
   * Returns a map of all of the units of type volume with their respective conversion factor.
   * @return a map of all of the units of type volume with their respective conversion factor.
   */
  public static Map<String, Double> getUnitTypesVolume()
  {
    return volume;
  }

  /**
   * Formats the compound unit String for when a multiplication calculation is performed.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @return a formatted String according to the multiplication rule.
   */
  private String formatMultiplicationUnits(String unit1, String unit2)
  {
    if (unit1.length() <= 0 || unit2.length() <= 0)
    {
      if (unit1.length() <= 0) {
        return unit2;
      }
      return unit1;
    }

    String result = unit1 + "-" + unit2;

    if (getBaseUnit(unit1).equals(getBaseUnit(unit2)))
    {
      String baseUnit = getBaseUnit(unit1);
      result = baseUnit + powers[2];
      for (int j = 0; j < powers.length - 1; j++)
      {
        for (int k = 0; k < powers.length - 1; k++)
        {
          if (unit1.indexOf(powers[j]) != -1 || unit2.indexOf(powers[k]) != -1)
          {
            if (j > k)
            {
              return baseUnit + powers[j + 1];
            }
            else
            {
              return baseUnit + powers[k + 1];
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Returns a formatted compound unit for when division calculations are performed.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @return a formatted compound unit with "/" conjunction.
   */
  private String formatDivisionUnits(String unit1, String unit2)
  {
    if (unit1.length() <= 0 || unit2.length() <= 0 || unit1.equals(unit2))
      return "";
    return unit1 + "/" + unit2;
  }

  /**
   * Formats the units for like units when performing addition or subtraction calculations.
   * 
   * @param unit1
   *          is the first unit.
   * @param unit2
   *          is the second unit.
   * @return a formatted String for like units.
   */
  private String formatLikeUnits(String unit1, String unit2)
  {
    if (unit1.equals(unit2))
      return unit1;

    return getSmallerUnit(unit1, unit2);
  }

}
