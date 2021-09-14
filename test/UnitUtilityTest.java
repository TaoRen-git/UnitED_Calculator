package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import united.*;

class UnitUtilityTest
{

  private UnitUtility utility;

  @BeforeEach
  void initialize()
  {
    utility = new UnitUtility();
  }

  @Test
  void testNeedConversion()
  {
    assertTrue(utility.needConversion("ft", "m"));
    assertTrue(utility.needConversion("hr", "min"));
    assertTrue(utility.needConversion("gal", "L"));
    assertTrue(utility.needConversion("g", "kg"));

    assertFalse(utility.needConversion("ft", "ft"));
    assertFalse(utility.needConversion("hr", "ft"));
    assertFalse(utility.needConversion("g", "g"));
    assertFalse(utility.needConversion("hr", "m"));

  }

  @Test
  void testGetSmallerUnit()
  {
    assertEquals("ft", utility.getSmallerUnit("ft", "m"));
    assertEquals("min", utility.getSmallerUnit("min", "hr"));
    assertEquals("mg", utility.getSmallerUnit("mg", "tons"));
    assertEquals("mL", utility.getSmallerUnit("L", "mL"));
  }

  @Test
  void testCanPerformCalculation()
  {
    assertTrue(utility.canPerformUnitCalculation("", "", DisplayInputField.ADD)); // No Units

    // One without Units
    assertFalse(utility.canPerformUnitCalculation("", "ft", DisplayInputField.SUBTRACT));
    assertFalse(utility.canPerformUnitCalculation("m", "", DisplayInputField.SUBTRACT));

    // Multiplication
    assertTrue(utility.canPerformUnitCalculation("hr", "m", DisplayInputField.MULTI));
    assertTrue(
        utility.canPerformUnitCalculation("custom", "another custom", DisplayInputField.MULTI));

    // Division
    assertTrue(utility.canPerformUnitCalculation("min", "lbs", DisplayInputField.DIVIDE));
    assertTrue(
        utility.canPerformUnitCalculation("custom", "another custom", DisplayInputField.DIVIDE));

    // Addition
    assertTrue(utility.canPerformUnitCalculation("purple", "purple", DisplayInputField.ADD));
    assertTrue(utility.canPerformUnitCalculation("m", "m", DisplayInputField.ADD));
    assertFalse(utility.canPerformUnitCalculation("custom", "gal", DisplayInputField.ADD));
    assertFalse(utility.canPerformUnitCalculation("in", "custom", DisplayInputField.ADD));
    assertFalse(utility.canPerformUnitCalculation("s", "mL", DisplayInputField.ADD));
    assertTrue(utility.canPerformUnitCalculation("hr", "min", DisplayInputField.ADD));

    // Subtraction
    assertTrue(utility.canPerformUnitCalculation("purple", "purple", DisplayInputField.SUBTRACT));
    assertTrue(utility.canPerformUnitCalculation("min", "min", DisplayInputField.SUBTRACT));
    assertFalse(utility.canPerformUnitCalculation("color", "gal", DisplayInputField.SUBTRACT));
    assertFalse(utility.canPerformUnitCalculation("in", "color", DisplayInputField.SUBTRACT));
    assertFalse(utility.canPerformUnitCalculation("kg", "hr", DisplayInputField.SUBTRACT));
    assertTrue(utility.canPerformUnitCalculation("mg", "tons", DisplayInputField.SUBTRACT));

    assertFalse(utility.canPerformUnitCalculation("mg", "tons", 5));
  }

  @Test
  void testGetUnitType()
  {
    assertEquals("Length", utility.getUnitType("m"));
    assertEquals("Time", utility.getUnitType("min"));
    assertEquals("Volume", utility.getUnitType("mL"));
    assertEquals("Mass", utility.getUnitType("mg"));
  }

  @Test
  void testgetFormattedUnits()
  {
    // Multiplication
    assertEquals("", utility.getFormattedUnit("", "", DisplayInputField.MULTI));
    assertEquals("in\u00b2", utility.getFormattedUnit("in", "in", DisplayInputField.MULTI));
    assertEquals("mi\u00b2", utility.getFormattedUnit("mi", "mi", DisplayInputField.MULTI));
    assertEquals("min\u00b2", utility.getFormattedUnit("min", "min", DisplayInputField.MULTI));
    assertEquals("mL\u00b2", utility.getFormattedUnit("mL", "mL", DisplayInputField.MULTI));
    assertEquals("gal\u00b3",
        utility.getFormattedUnit("gal", "gal" + "\u00b2", DisplayInputField.MULTI));
    assertEquals("m\u00b3", utility.getFormattedUnit("m" + "\u00b2", "m", DisplayInputField.MULTI));
    assertEquals("ft-lb", utility.getFormattedUnit("ft", "lb", DisplayInputField.MULTI));
    assertEquals("ft", utility.getFormattedUnit("ft", "", DisplayInputField.MULTI));
    assertEquals("ft", utility.getFormattedUnit("", "ft", DisplayInputField.MULTI));
    assertEquals("min\u00b2", utility.getFormattedUnit("min", "min", DisplayInputField.MULTI));

    // Division
    assertEquals("", utility.getFormattedUnit("", "", DisplayInputField.DIVIDE));
    assertEquals("", utility.getFormattedUnit("mi", "mi", DisplayInputField.DIVIDE));
    assertEquals("mi/hr", utility.getFormattedUnit("mi", "hr", DisplayInputField.DIVIDE));
    assertEquals("", utility.getFormattedUnit("hr", "", DisplayInputField.DIVIDE));
    
    // Addition and Subtraction
    assertEquals("m", utility.getFormattedUnit("m", "m", DisplayInputField.ADD));
    assertEquals("in", utility.getFormattedUnit("in", "mi", DisplayInputField.SUBTRACT));
    assertEquals("m", utility.getFormattedUnit("m", "m", DisplayInputField.SUBTRACT));
    
    assertEquals("", utility.getFormattedUnit("", "", Integer.valueOf(5)));
    

  }

  @Test
  void testGetBaseUnit()
  {
    UnitUtility testBaseUnit = new UnitUtility();
    String[] powers = {"\u2070", "\u2071", "\u00b2", "\u00b3", "\u2074", "\u2075", "\u2076",
        "\u2077", "\u2078", "\u2079"};
    String[] units = {"ft", "cm", "mi/hr", "L", "gal", "oz", "mi", "m/s", "hr", "g"};

    for (String unit : units)
    {
      for (String power : powers)
      {
        assertEquals(unit, testBaseUnit.getBaseUnit(unit + power));
      }
    }
  }
  
  @Test
  void testGetMaps()
  {
	  UnitUtility testBaseUnit = new UnitUtility();
	  
	  assertEquals(null, UnitUtility.getUnitTypes().get("not in map"));
	  assertEquals(null, UnitUtility.getUnitTypesLength().get("not in map"));
	  assertEquals(null, UnitUtility.getUnitTypesMass().get("not in map"));
	  assertEquals(null, UnitUtility.getUnitTypesVolume().get("not in map"));
	  assertEquals(null, UnitUtility.getUnitTypesTime().get("not in map"));
	  
	  UnitUtility.setUnitType("centicats", "Length");
  }

}
