package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import united.DisplayInputField;
import united.ExpressionParser;

class ExpressionParserTest
{

  @Test
  void testParseSimpleExpressionAddNoUnits()
  {
    String s = "5 + 6 ";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperands().size());
    assertEquals(Double.valueOf(5), e.getOperands().remove());
    assertEquals(Double.valueOf(6), e.getOperands().remove());
    assertEquals(1, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.ADD), e.getOperators().remove());
    assertEquals(0, e.getUnits().size());
  }

  @Test
  void testParseSimpleExpressionSubNoUnits()
  {
    String s = "5 " + "-" + " 6 ";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperands().size());
    assertEquals(Double.valueOf(5), e.getOperands().remove());
    assertEquals(Double.valueOf(6), e.getOperands().remove());
    assertEquals(1, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.SUBTRACT), e.getOperators().remove());
    assertEquals(0, e.getUnits().size());
  }

  @Test
  void testParseSimpleExpressionMultiplyUnits()
  {
    String s = "8 ft * 7 m ";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperands().size());
    assertEquals(Double.valueOf(8), e.getOperands().remove());
    assertEquals(Double.valueOf(7), e.getOperands().remove());
    assertEquals(1, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.MULTI), e.getOperators().remove());
    assertEquals(2, e.getUnits().size());
    assertEquals("ft", e.getUnits().remove());
    assertEquals("m", e.getUnits().remove());
  }

  @Test
  void testParseMultipleDigitExpressionDivideUnits()
  {
    String s = "1000 oz / 26 mg";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperands().size());
    assertEquals(Double.valueOf(1000), e.getOperands().remove());
    assertEquals(Double.valueOf(26), e.getOperands().remove());
    assertEquals(1, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.DIVIDE), e.getOperators().remove());
    assertEquals(2, e.getUnits().size());
    assertEquals("oz", e.getUnits().remove());
    assertEquals("mg", e.getUnits().remove());
  }

  @Test
  void testParseMultipleOperandsNoUntis()
  {
    String s = "10 + 5 * 8 - 6";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(4, e.getOperands().size());
    assertEquals(Double.valueOf(10), e.getOperands().remove());
    assertEquals(Double.valueOf(5), e.getOperands().remove());
    assertEquals(Double.valueOf(8), e.getOperands().remove());
    assertEquals(Double.valueOf(6), e.getOperands().remove());
    assertEquals(3, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.ADD), e.getOperators().remove());
    assertEquals(Integer.valueOf(DisplayInputField.MULTI), e.getOperators().remove());
    assertEquals(Integer.valueOf(DisplayInputField.SUBTRACT), e.getOperators().remove());
    assertEquals(0, e.getUnits().size());
  }

  @Test
  void testParseNegativeNumbers()
  {
    String s = "-55 + 8";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperands().size());
    assertEquals(Double.valueOf(-55), e.getOperands().remove());
    assertEquals(Double.valueOf(8), e.getOperands().remove());
    assertEquals(1, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.ADD), e.getOperators().remove());
    assertEquals(0, e.getUnits().size());
  }

  @Test
  void testParseNegativeNumbersWithMinus()
  {
    String s = "-55 - -8";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperands().size());
    assertEquals(Double.valueOf(-55), e.getOperands().remove());
    assertEquals(Double.valueOf(-8), e.getOperands().remove());
    assertEquals(1, e.getOperators().size());
    assertEquals(Integer.valueOf(DisplayInputField.SUBTRACT), e.getOperators().remove());
    assertEquals(0, e.getUnits().size());
  }

  @Test
  void testInvalidExpressionTooManyOperators()
  {
    String s = "5 +- 5";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertFalse(e.isValidExpression());
  }

  @Test
  void testInvalidExpressionNoDigits()
  {
    String s = "-";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertFalse(e.isValidExpression());
  }

  @Test
  void testInvalidExpressionJustDigits()
  {
    String s = "5552";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertFalse(e.isValidExpression());
  }

  @Test
  void testInvalidExpressionInvalidOperator()
  {
    String s = "55 % 2";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertFalse(e.isValidExpression());
  }

  @Test
  void testInvalidExpressionValidUnits()
  {
    String s = "55 ft - 2 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertTrue(e.isValidExpression());
  }

  @Test
  void testInvalidExpressionOneUnit()
  {
    String s = "55 ft - 2 ";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertFalse(e.isValidExpression());
  }

  @Test
  void testInvalidExpressionValid()
  {
    String s = "55 ft - 2 m";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertTrue(e.isValidExpression());
  }

  @Test
  void testSimpleAddEvaluateExpressionWithUnits()
  {
    String s = "5 ft + 6 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("11 ft", result);
  }

  @Test
  void testRunningCalcAddEvaluateExpressionWithUnits()
  {
    String s = "5 ft + 6 ft + 2 ft - 7 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("6 ft", result);
  }

  @Test
  void testRunningCalcMultiplyEvaluateExpressionWithUnits()
  {
    String s = "5 ft * 6 ft * 2 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("60 ft" + "\u00b3", result);
  }

  @Test
  void testEvaluateExpressionUnitConversion()
  {
    String s = "100 oz - 5 mg";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("2834945 mg", result);
  }

  @Test
  void testEvaluateExpressionInvalidUnitTypes()
  {
    String s = "100 mg - 5 m";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("0 ", result);
  }

  @Test
  void testEvaluateExpressionWithDecimals()
  {
    String s = "1 mg + 1 oz";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("28350.50 mg", result);
  }

  @Test
  void testRunningCalcEvaluateExpressionDivide()
  {
    String s = "8 mi / 2 hr / 2 hr";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    String result = e.getFinalResult();
    assertEquals("2 mi/hr/hr", result);
  }

  @Test
  void testEvaluateExpressionInvalidString()
  {
    String s = "8 mi / 2 hr";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    assertEquals("mi/hr", e.getResultUnit());
  }

  @Test
  void testEvaluateExpressionNoUnits()
  {
    String s = "5 * 5 * 5";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    assertEquals(Double.valueOf(125), e.getResultOperand());
  }

  @Test
  void testNoWhiteSpaceEvalute()
  {
    String s = "3*3*3";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertEquals(2, e.getOperators().size());
    assertEquals(3, e.getOperands().size());
    e.evaluateExpression();
    assertEquals(Double.valueOf(27), e.getResultOperand());
  }

  @Test
  void testInvalidCalculation()
  {
    String s = "3 ft + 3 min";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    assertTrue(e.isInvalidCalculation());
  }

  @Test
  void testFractions()
  {
    String s = "1/2 ft + 2 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    assertEquals(Double.valueOf(2.50), e.getResultOperand());
  }

  @Test
  void testFractionsWithDivision()
  {
    String s = "3 m / 1/4 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    assertEquals(Double.valueOf(12), e.getResultOperand());
  }

  @Test
  void testPowers()
  {
    String s = "2^5 ft + 2 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    e.evaluateExpression();
    assertEquals(Double.valueOf(34), e.getResultOperand());
  }
  
  @Test
  void testInvalidPowers()
  {
    String s = "2^ ft + 3 ft";
    ExpressionParser e = new ExpressionParser(s);
    e.parse();
    assertFalse(e.isValidExpression());
  }
}
