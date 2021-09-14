package testing;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import united.CalculateUtility;
import united.DisplayInputField;

/**
 * Test Cases for CalculateUtility Class.
 * 
 * @author Vivian Dang
 *
 */
class CalculateUtilityTest
{

  @Test
  void testConstructor()
  {
    new CalculateUtility();
  }

  @Test
  void testCalculateNoConversionAdd()
  {
    double result = CalculateUtility.calculate(Double.valueOf(5), Double.valueOf(5),
        Integer.valueOf(DisplayInputField.ADD), "ft", "ft");
    assertEquals(10.0, result, 0.0001);
  }

  @Test
  void testCalculateNoConversionSub()
  {
    double result = CalculateUtility.calculate(Double.valueOf(28), Double.valueOf(30),
        Integer.valueOf(DisplayInputField.SUBTRACT), "hr", "hr");
    assertEquals(-2, result, 0.0001);
  }

  @Test
  void testCalculateInvalidOperator()
  {
    double result = CalculateUtility.calculate(Double.valueOf(5), Double.valueOf(5),
        Integer.valueOf(5), "ft", "ft");
    assertEquals(0, result, 0.0001);
  }

  @Test
  void testCalculateConversionAdd()
  {
    double result = CalculateUtility.calculate(Double.valueOf(28), Double.valueOf(6),
        Integer.valueOf(DisplayInputField.ADD), "in", "ft");
    assertEquals(100, result, 0.0001);

    result = CalculateUtility.calculate(Double.valueOf(2), Double.valueOf(60),
        Integer.valueOf(DisplayInputField.ADD), "hr", "s");
    assertEquals(7260, result, 0.0001);

    result = CalculateUtility.calculate(Double.valueOf(1), Double.valueOf(1),
        Integer.valueOf(DisplayInputField.ADD), "mg", "oz");
    assertEquals(28350.5, result, 0.0001);

    result = CalculateUtility.calculate(Double.valueOf(10), Double.valueOf(1),
        Integer.valueOf(DisplayInputField.ADD), "mL", "L");
    assertEquals(1010, result, 0.0001);
  }

  @Test
  void testCalculateConversionSub()
  {
    double result = CalculateUtility.calculate(Double.valueOf(72), Double.valueOf(8),
        Integer.valueOf(DisplayInputField.SUBTRACT), "m", "yd");
    assertEquals(70.7402, result, 0.0001);

    result = CalculateUtility.calculate(Double.valueOf(2), Double.valueOf(60),
        Integer.valueOf(DisplayInputField.SUBTRACT), "min", "s");
    assertEquals(60, result, 0.0001);

    result = CalculateUtility.calculate(Double.valueOf(1), Double.valueOf(16),
        Integer.valueOf(DisplayInputField.SUBTRACT), "lb", "oz");
    assertEquals(0, result, 0.0001);

    result = CalculateUtility.calculate(Double.valueOf(10), Double.valueOf(1),
        Integer.valueOf(DisplayInputField.SUBTRACT), "L", "gal");
    assertEquals(6.21458, result, 0.0001);
  }

  @Test
  void testCalculateConversionMulti()
  {
    double result = CalculateUtility.calculate(Double.valueOf(2), Double.valueOf(8),
        Integer.valueOf(DisplayInputField.MULTI), "m", "yd");
    assertEquals(16, result, 0.0001);

  }

  @Test
  void testCalculateConversionDivide()
  {
    double result = CalculateUtility.calculate(Double.valueOf(6), Double.valueOf(3),
        Integer.valueOf(DisplayInputField.DIVIDE), "m", "yd");
    assertEquals(2, result, 0.0001);

  }

}
