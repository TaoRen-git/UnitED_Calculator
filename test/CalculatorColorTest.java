package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import united.CalculatorColor;

class CalculatorColorTest
{

  @Test
  void testConstructor()
  {
    new CalculatorColor();
  }

  @Test
  void testStringToColor()
  {
    assertEquals(Color.WHITE, CalculatorColor.stringToColor("White", Color.WHITE));
    assertEquals(Color.GRAY, CalculatorColor.stringToColor("Gray", Color.WHITE));
    assertEquals(Color.DARK_GRAY, CalculatorColor.stringToColor("Dark Gray", Color.WHITE));
    assertEquals(Color.BLACK, CalculatorColor.stringToColor("Black", Color.WHITE));
    assertEquals(Color.RED, CalculatorColor.stringToColor("Red", Color.WHITE));
    assertEquals(Color.PINK, CalculatorColor.stringToColor("Pink", Color.WHITE));
    assertEquals(Color.ORANGE, CalculatorColor.stringToColor("Orange", Color.WHITE));
    assertEquals(Color.YELLOW, CalculatorColor.stringToColor("Yellow", Color.WHITE));
    assertEquals(Color.GREEN, CalculatorColor.stringToColor("Green", Color.WHITE));
    assertEquals(Color.MAGENTA, CalculatorColor.stringToColor("Magenta", Color.WHITE));
    assertEquals(Color.CYAN, CalculatorColor.stringToColor("Cyan", Color.WHITE));
    assertEquals(Color.BLUE, CalculatorColor.stringToColor("Blue", Color.WHITE));
    assertEquals(Color.WHITE, CalculatorColor.stringToColor("Error", Color.WHITE));
    assertEquals(Color.WHITE, CalculatorColor.stringToColor(null, Color.WHITE));
  }

}
