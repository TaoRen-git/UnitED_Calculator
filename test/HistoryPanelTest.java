package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import united.HistoryDisplay;
import united.HistoryPanel;

class HistoryPanelTest
{

  private HistoryPanel testHistory = new HistoryPanel();
  private String testExpression = "";
  private ArrayList<String> testList = new ArrayList<>();

  public void setup(String newExpression)
  {
    testHistory = new HistoryPanel();
    testExpression = newExpression;
  }

  @Test
  void singleNumberTest()
  {
    setup("123");
    testHistory.addText(testExpression);
    testExpression += "\n";

    assertEquals(testExpression, testHistory.getText());
  }

  @Test
  void singleUnitTest()
  {
    setup("ft");
    testHistory.addText(testExpression);
    testExpression += "\n";

    assertEquals(testExpression, testHistory.getText());
  }

  @Test
  void singleUnitedNumberTest()
  {
    setup("45 gal");
    testHistory.addText(testExpression);
    testExpression += "\n";
    assertEquals(testExpression, testHistory.getText());
  }
  
  @Test
  void singleUnunitedCalculationTest()
  {
    setup("97 + 56 = 153");
    testHistory.addText(testExpression);
    testExpression += "\n";
    assertEquals(testExpression, testHistory.getText());
  }
  
  @Test
  void singleUnitedCalculationTest()
  {
    setup("34 mm + 17 mm = 51 mm");
    testHistory.addText(testExpression);
    testExpression += "\n";
    assertEquals(testExpression, testHistory.getText());
  }
  
  @Test
  void multipleUnunitedCalculationTest()
  {
	testHistory = new HistoryPanel();
	testList = new ArrayList<>();
	
	testList.add("97 + 56 = 153");
	testList.add("234 x 18 = 4212");
	testList.add("1642 - 7390 = -5748");

	for (String test : testList)
	{
		testHistory.addText(test);
		testExpression += test + "\n";
	}

	assertEquals(testExpression, testHistory.getText());
    assertEquals(testList, testHistory.getexpressionList());

  }
  
  @Test
  void multipleUnitedCalculationTest()
  {
	testHistory = new HistoryPanel();
	testList = new ArrayList<>();
	
	testList.add("22 ft + 77 ft = 99 ft");
	testList.add("234 mm - 181 mm = 53 mm");
	testList.add("1111 gal - 6789 gal = -5678 gal");

	for (String test : testList)
	{
		testHistory.addText(test);
		testExpression += test + "\n";
	}

	assertEquals(testExpression, testHistory.getText());
    assertEquals(testList, testHistory.getexpressionList());

  }

}
