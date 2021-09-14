package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import united.InputField;
import united.UnitUtility;

class InputFieldTest
{

	private UnitUtility uu;

	@Test
	void textTest()
	{
		uu = new UnitUtility();
		InputField inp = new InputField(uu);

		assertTrue(inp.getText().equals(""));
		inp.setText("purple");
		assertTrue(inp.getText().equals("purple"));

	}

	@Test
	void unitsTest()
	{
		uu = new UnitUtility();
		InputField inp = new InputField(uu);

		inp.setUnits("in");
		assertTrue(inp.getUnit().equals("in"));
	}

	@Test
	void allTest()
	{
		uu = new UnitUtility();
		InputField inp = new InputField(uu);

		inp.setText("43.5");
		inp.setUnits("in");
		assertTrue(inp.getAllInput().equals("43.5 in"));

		inp.resetInput();
		assertTrue(inp.getAllInput().equals(" "));
	}

	@Test
	void hasValidTest()
	{
		uu = new UnitUtility();
		InputField inp = new InputField(uu);

		assertFalse(inp.hasValidDigitInput());
		inp.setText("52.9");
		inp.setUnits("gal");
		assertFalse(inp.hasValidDigitInput());
		inp.setText("-27");
		assertTrue(inp.hasValidDigitInput());
		inp.setText("1/4");
		assertTrue(inp.hasValidDigitInput());

	}

	@Test
	void testGetUnitLength()
	{

		uu = new UnitUtility();
		InputField inp = new InputField(uu);
		inp.setUnits("puppiesLength");

		assertEquals("puppiesLength", inp.getUnit());

	}

	@Test
	void testGetUnitTime()
	{

		uu = new UnitUtility();
		InputField inp = new InputField(uu);
		inp.setUnits("puppiesTime");

		assertEquals("puppiesTime", inp.getUnit());

	}

	@Test
	void testGetUnitMass()
	{

		uu = new UnitUtility();
		InputField inp = new InputField(uu);
		inp.setUnits("puppiesMass");

		assertEquals("puppiesMass", inp.getUnit());

	}

	@Test
	void testGetUnitVolume()
	{

		uu = new UnitUtility();
		InputField inp = new InputField(uu);
		inp.setUnits("puppiesVolume");

		assertEquals("puppiesVolume", inp.getUnit());

	}

	@Test
	void testGetUnitPressingXMeasureTypeWindow()
	{

		uu = new UnitUtility();
		InputField inp = new InputField(uu);
		inp.setUnits("pressX");

		assertEquals("pressX", inp.getUnit());

	}

	@Test
	void testingGetUnitPressingXConversionWindow()
	{

		uu = new UnitUtility();
		InputField inp = new InputField(uu);
		inp.setUnits("pressX");

		assertEquals("pressX", inp.getUnit());

	}

}
