package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import united.HistoryPanel;
import united.DisplayInputField;

/**
 * Test Cases for DisplayInputField Class.
 * 
 * @author Team02
 *
 */
class DisplayInputFieldTest
{
	private DisplayInputField field1;

	@BeforeEach
	void initialize()
	{
		field1 = new DisplayInputField(new HistoryPanel());
	}

	@Test
	void testSignButtonToNeg()
	{
		field1.setInputText("3");

		field1.signButtonCommand("\u00b1");

		assertEquals("-3", field1.getInputFieldText());
	}

	@Test
	void testSignButtonToPos()
	{

		field1.setInputText("-3");

		field1.signButtonCommand("\u00b1");

		assertEquals("3", field1.getInputFieldText());

	}

	@Test
	void testSignButtonNothingInInput()
	{

		field1.setInputText("");
		field1.signButtonCommand("\u00b1");

		assertEquals("", field1.getInputFieldText());

	}

	@Test
	void testSignButtonIncorrectParameter()
	{

		field1.setInputText("3");
		field1.signButtonCommand("");

		assertEquals("3", field1.getInputFieldText());
	}

	@Test
	void testSignButtonWrongParamAndNoInput()
	{

		field1.setInputText("");
		field1.signButtonCommand("");

		assertEquals("", field1.getInputFieldText());

	}

	@Test
	void testCancelCommandWrongParam()
	{

		field1.setInputText("3+5+10");
		field1.cancelCommand("R");

		assertEquals("3+5+10", field1.getInputFieldText());

	}

	@Test
	void testCancelCammandCorrectParam()
	{

		field1.setInputText("3+5+10");
		field1.cancelCommand("C");

		assertEquals("", field1.getInputFieldText());

	}

	@Test
	void resetCommandCorrectParam()
	{

		field1.setInputText("3");
		field1.setCurrentExpression("15");
		field1.resetCommand("R");

		assertEquals("", field1.getInputFieldText());
		assertEquals("", field1.getCurrExpression());

	}

	@Test
	void resetCommanWrongParam()
	{

		field1.setInputText("3");
		field1.setCurrentExpression("15");
		field1.resetCommand("");

		assertEquals("3", field1.getInputFieldText());
		assertEquals("15", field1.getCurrExpression());

	}

	@Test
	void testNumericKeyPad()
	{
		String num = "0123456789";
		for (int i = 0; i < 10; i++)
		{
			String digit = num.charAt(i) + "";
			field1.numericKeyPadCommand(digit);
		}
		assertEquals(num, field1.getInputFieldText());
	}

	@Test
	void testSetDisplayColor()
	{
		field1.setDisplayColor(Color.BLUE);
		assertEquals(Color.BLUE, field1.getDisplay().getBackground());
	}

	@Test
	void testOperatorCommand()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		assertEquals("ft", field1.getUnits().peek());
		assertEquals(Double.valueOf(5), field1.getOperands().peek());
		assertEquals("5 ft + ", field1.getCurrExpression());
		assertEquals("5 +", field1.getDisplay().getText());
		assertEquals("", field1.getInputFieldText());
	}

	@Test
	void testOperatorWrongCommand()
	{
		field1.operatorCommand("%");
		assertEquals(0, field1.getUnits().size());
		assertEquals(0, field1.getOperands().size());
	}

	@Test
	void testOperatorWrongInput()
	{
		field1.setInputText("cool");
		field1.operatorCommand("+");
		assertEquals(0, field1.getUnits().size());
		assertEquals(0, field1.getOperands().size());
	}

	@Test
	void testOperatorCommandRunningCalc()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		field1.setInputText("6");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		assertEquals("ft", field1.getUnits().peek());
		assertEquals(Double.valueOf(11), field1.getOperands().peek());
		assertEquals("5 ft + 6 ft + ", field1.getCurrExpression());
		assertEquals("11 +", field1.getDisplay().getText());
		assertEquals("", field1.getInputFieldText());
	}

	@Test
	void testOperatorCommandInvalidRunningCalc()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		field1.setInputText("6");
		field1.setUnits("hr");
		field1.operatorCommand("-");
		assertEquals(1, field1.getOperands().size());
		assertEquals(1, field1.getOperators().size());
		assertEquals(1, field1.getUnits().size());
	}

	@Test
	void testOperatorCommandInvalidCalculation()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		field1.setInputText("6");
		field1.setUnits("hr");
		field1.equalsCommand("+");
		assertEquals("5 +", field1.getDisplay().getText());
	}

	@Test
	void testEqualsCommand()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		field1.setInputText("6");
		field1.setUnits("ft");
		field1.equalsCommand("=");
		assertEquals("11", field1.getDisplay().getText());
		assertEquals("ft", field1.getDisplayUnits());
	}

	@Test
	void testEqualsInvalidCommand()
	{
		field1.equalsCommand("%");
		assertEquals("", field1.getDisplay().getText());
	}

	@Test
	void testEqualsCommandInvalidInput()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		field1.setInputText("cool");
		field1.setUnits("ft");
		field1.equalsCommand("=");
		assertEquals("5 +", field1.getDisplay().getText());
	}

	@Test
	void testEqualsCommandInvalidCalculation()
	{
		field1.setInputText("5");
		field1.setUnits("ft");
		field1.operatorCommand("+");
		field1.setInputText("6");
		field1.setUnits("hr");
		assertEquals("5 +", field1.getDisplay().getText());
	}

	@Test
	void testEqualsCommandDecimalResult()
	{
		field1.setInputText("1");
		field1.setUnits("mg");
		field1.operatorCommand("+");
		field1.setInputText("1");
		field1.setUnits("oz");
		field1.equalsCommand("=");
		assertEquals("28350.50", field1.getDisplay().getText());
		assertEquals("mg", field1.getDisplayUnits());
	}

	@Test
	void testOperatorCommandWithValidExpression()
	{
		field1.setInputText("3 ft + 4 ft + 7 ft");
		field1.operatorCommand("+");
		assertEquals("14 +", field1.getDisplay().getText());
	}

	@Test
	void testOperatorCommandWithInvalidExpression()
	{
		field1.setInputText("3 ft + 4 min + 5 ft");
		field1.operatorCommand("+");
		assertEquals("", field1.getDisplay().getText());
	}

	@Test
	void testEqualsCommandWithValidExpression()
	{
		field1.setInputText("3 ft * 4 ft * 2 ft");
		field1.equalsCommand("=");
		assertEquals("24", field1.getDisplay().getText());
		assertEquals("ft\u00b3", field1.getDisplayUnits());
	}
	
	@Test
	void testEqualsCommandDivExpression()
	{
		field1.setInputText("12 ft / 2 ft");
		field1.equalsCommand("=");
		assertEquals("6", field1.getDisplay().getText());
		assertEquals("", field1.getDisplayUnits());
	}
	
	

	@Test
	void testEqualsCommandWithInvalidExpression()
	{
		field1.setInputText("5 m - 6 s");
		field1.equalsCommand("=");
		assertEquals("", field1.getDisplay().getText());
	}

	@Test
	void testInputChangeButtonsInvert()
	{

		field1.setInputText("27");
		field1.inputChangeButtons("1/x");
		assertEquals("1/27", field1.getInputFieldText());

	}

	@Test
	void testInputChangeButtonIntegerPower()
	{

		field1.setInputText("2");
		field1.inputChangeButtons("<html>X<sup>Y</sup></html>");
		assertEquals("2", field1.getInputFieldText());

	}

	@Test
	void testInputChangeButtonIntegerPowerNoBase()
	{
		field1.setInputText("");
		field1.inputChangeButtons("<html>X<sup>Y</sup></html>");
	}

	@Test
	void testInputChangeButtonInvertNoBase()
	{
		field1.setInputText("");
		field1.inputChangeButtons("1/x");
	}

	@Test
	void testExponentTooBig()
	{
		field1.setInputText("2");
		field1.inputChangeButtons("<html>X<sup>Y</sup></html>");

	}

	@Test
	void testExponentCancelButton()
	{

		field1.setInputText("3");
		field1.inputChangeButtons("<html>X<sup>Y</sup></html>");

		assertEquals("3", field1.getInputFieldText());

	}

	@Test
	void testInvalidInputForExponenet()
	{

		field1.setInputText("3");
		field1.inputChangeButtons("<html>X<sup>Y</sup></html>");

	}

	@Test
	void testCurrentColor()
	{

		assertEquals(new Color(255, 175, 175), field1.getCurrentColor());

	}

	@Test
	void testBackSpaceCommand()
	{

		field1.setInputText("2");
		field1.backspaceCommand("\u232B");

		assertEquals("", field1.getInputFieldText());
	}

	@Test
	void testIntegerPowerInvalidInput()
	{

		field1.setInputText("2");
		field1.inputChangeButtons("<html>X<sup>Y</sup></html>");

		assertEquals("2", field1.getInputFieldText());

	}

	@Test
	void testParseFraction()
	{

		field1.setInputText("12/2");
		field1.parseFraction();

		assertEquals("12/2", field1.getInputFieldText());

	}
	

}
