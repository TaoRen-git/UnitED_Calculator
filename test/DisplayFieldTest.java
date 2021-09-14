package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import united.*;

class DisplayFieldTest {

	@Test
	void getAndSetTextTest() {
		DisplayField df = new DisplayField();
		
		df.setText("15 ft");
		assertTrue("15".equals(df.getText()));
		
		assertTrue("15".equals(df.getDisplay().getText()));
		
		df.setText("21");
		assertTrue("21".equals(df.getText()));
		
		df.setText("12 gal *");
		assertTrue("12 *".equals(df.getText()));
		assertTrue("12 * gal".equals(df.getAllDisplay()));
		
		df.setText("");
		assertTrue("".equals(df.getText()));
		
		df.setText(null);
		assertTrue("".equals(df.getText()));
	}
	
	@Test
	void setListTest() {
		DisplayField df = new DisplayField();
		
		df.resetUnitList();
		
		df.setList("chickens");
		df.setList("min");
		df.setList("g");
		df.setList("m" + "\u00b2");
		df.setList("m/s");
	}
	
	@Test
	void unitTest() {
		DisplayField df = new DisplayField();
		
		df.setUnit("cow");
		assertTrue("cow".equals(df.getUnit()));
	}
	
	@Test
	void recalculateTest() {
		DisplayField df = new DisplayField();
		df.setText("15 ft");
		
		df.lockResult();
		df.changeSelectedItem("in");
		
		df.unlockResult();
		
		df.setText("15");
		df.setUnit("");
		df.lockResult();
		df.changeSelectedItem("");
		assertEquals(15.0, df.getResult());
		df.unlockResult();
		
		df.setText("22 L");
		df.setUnit("L");
		df.lockResult();
		df.changeSelectedItem("mL");
		assertEquals(22.0, df.getResult());
		df.unlockResult();
		
		df.setText("8 min");
		df.setUnit("min");
		df.lockResult();
		df.changeSelectedItem("hr");
		assertEquals(8.0, df.getResult());
		df.unlockResult();
		
		df.setText("724 g");
		df.setUnit("g");
		df.lockResult();
		df.changeSelectedItem("mg");
		assertEquals(724.0, df.getResult());
		df.unlockResult();
		


	}

}
