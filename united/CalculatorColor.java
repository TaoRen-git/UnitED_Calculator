package united;

import java.awt.Color;

/**
 * Utility Class that stores valid color schemes.
 * 
 * @author Team02
 *
 */
public class CalculatorColor
{

  public static final String[] COLOR_OPTIONS = {"White", "Gray", "Dark Gray", "Black", "Red",
      "Pink", "Orange", "Yellow", "Green", "Magenta", "Cyan", "Blue"};

  /**
   * Converts a string representation of color to a Color object.
   * 
   * @param str
   *          is the string representation of the color.
   * @return a Color representation of the string.
   */
  public static Color stringToColor(String str, Color defaultColor)
  {
    if (str == null)
    {
      return defaultColor;
    }

    Color color = defaultColor;
    switch (str)
    {
      case "White":
        color = Color.WHITE;
        break;
      case "Gray":
        color = Color.GRAY;
        break;
      case "Dark Gray":
        color = Color.DARK_GRAY;
        break;
      case "Black":
        color = Color.BLACK;
        break;
      case "Red":
        color = Color.RED;
        break;
      case "Pink":
        color = Color.PINK;
        break;
      case "Orange":
        color = Color.ORANGE;
        break;
      case "Yellow":
        color = Color.YELLOW;
        break;
      case "Green":
        color = Color.GREEN;
        break;
      case "Magenta":
        color = Color.MAGENTA;
        break;
      case "Cyan":
        color = Color.CYAN;
        break;
      case "Blue":
        color = Color.BLUE;
        break;

    }
    return color;
  }

}
