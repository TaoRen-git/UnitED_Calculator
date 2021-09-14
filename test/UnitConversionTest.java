package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import united.*;

class UnitConversionTest {

	@Test
	void nullUnitTest() {
		UnitConversion test = new UnitConversion();
		assertEquals(15.0, UnitConversion.calculateConvertedUnits(null, null, 10.0, 5.0, true));
		assertEquals(15.0, UnitConversion.calculateConvertedUnits("in", null, 10.0, 5.0, true));
		assertEquals(15.0, UnitConversion.calculateConvertedUnits(null, "gal", 10.0, 5.0, true));
		assertEquals(5.0, UnitConversion.calculateConvertedUnits(null, null, 10.0, 5.0, false));
	}
	
	@Test
	void withUnitsLengthTest() {
		assertEquals(27.587, UnitConversion.calculateConvertedUnits("in", "cm", 12.2, 3.4, false), 0.001);
		assertEquals(16.233, UnitConversion.calculateConvertedUnits("mi", "km", 12.2, 3.4, false), 0.001);
		assertEquals(12196.6, UnitConversion.calculateConvertedUnits("km", "m", 12.2, 3.4, false), 0.001);
		assertEquals(12196.6, UnitConversion.calculateConvertedUnits("m", "mm", 12.2, 3.4, false), 0.001);
		assertEquals(27.587, UnitConversion.calculateConvertedUnits("in", "cm", 12.2, 3.4, false), 0.001);
		assertEquals(16.5, UnitConversion.calculateConvertedUnits("yd", "yd", 12.2, 4.3, true), 0.001);
		assertEquals(16.5, UnitConversion.calculateConvertedUnits("ft", "ft", 12.2, 4.3, true), 0.001);
		assertEquals(22.7, UnitConversion.calculateConvertedUnits("cm", "in", 10.0, 5.0, true));
		assertEquals(15.0, UnitConversion.calculateConvertedUnits("cm", "cm", 10.0, 5.0, true));
		assertEquals(15.0, UnitConversion.calculateConvertedUnits("in", "in", 10.0, 5.0, true));
		assertEquals(150.0, UnitConversion.calculateConvertedUnits("cow", "cow", 100.0, 50.0, true));

	}
	
	@Test
	void withUnitsVolumeTest() {
		assertEquals(31.2, UnitConversion.calculateConvertedUnits("cow", "chicken", 30.2, 1, true));
		assertEquals(1.785, UnitConversion.calculateConvertedUnits("gal", "L", 1.0, 2.0, false), 0.001);
		assertEquals(1.0, UnitConversion.calculateConvertedUnits("L", "L", 2.0, 1.0, false), 0.001);
		assertEquals(1785.41, UnitConversion.calculateConvertedUnits("gal", "mL", 1.0, 2000.0, false), 0.001);
		
		assertEquals(60.0, UnitConversion.volumeConversion("mL", "mL", 60.0), 0.001);
		assertEquals(0.9246, UnitConversion.volumeConversion("mL", "gal", 3500.0), 0.001);
	}
	
	@Test
	void withUnitsMassTest() {
		assertEquals(1225.0, UnitConversion.calculateConvertedUnits("mg", "g", 125.0, 1.1, true));
		assertEquals(16.0, UnitConversion.calculateConvertedUnits("lb", "oz", 2.0, 16.0, false), 0.001);
		assertEquals(16.0, UnitConversion.calculateConvertedUnits("oz", "oz", 32.0, 16.0, false), 0.001);
		assertEquals(1089.721, UnitConversion.calculateConvertedUnits("tons", "kg", 1.2, 1.1, true), 0.001);
		assertEquals(401.10, UnitConversion.calculateConvertedUnits("tons", "lb", 0.2, 1.1, true), 0.001);
		assertEquals(1300.0, UnitConversion.calculateConvertedUnits("kg", "g", 1.2, 100.0, true), 0.001);
		
		assertEquals(82.2, UnitConversion.calculateConvertedUnits("mg", "mg", 34.2, 48.0, true), 0.001);
		assertEquals(1.0, UnitConversion.massConversion("lb", "tons", 2000.0), 0.001);
	}
	
	@Test
	void withUnitsTimeTest() {
		assertEquals(180.0, UnitConversion.calculateConvertedUnits("min", "s", 2.5, 30.0, true));
		assertEquals(81.5, UnitConversion.calculateConvertedUnits("min", "min", 42.3, 39.2, true));
		assertEquals(65.0, UnitConversion.calculateConvertedUnits("min", "hr", 185.0, 2.0, false), 0.001);
		assertEquals(7200.0, UnitConversion.calculateConvertedUnits("hr", "s", 1.0, 3600.0, true));
		
		assertEquals(60.0, UnitConversion.timeConversion("s", "s", 60.0), 0.001);
		assertEquals(1.0, UnitConversion.timeConversion("min", "hr", 60.0), 0.001);
	}
	

}
