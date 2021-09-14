package united;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.*;

/**
 * Creates a DisplayField.
 * 
 * @author Team02
 * @version 4/28/2020
 */
public class DisplayField extends JPanel
{
  private UnitUtility uu;
  private JComboBox<String> calculatedUnitMenu;
  private JTextField display;

  private double result;
  private String resultUnit;
  private boolean complete;

  public static final String[] UNIT_LIST = {"", "mm", "cm", "in", "ft", "yd", "m", "km", "mi", "s",
      "min", "hr", "mL", "L", "gal", "mg", "g", "oz", "lb", "kg", "tons", "m/s", "mi/hr",
      "m" + "\u00b2", "ft" + "\u00b2", "in" + "\u00b2", "in" + "\u00b3", "ft" + "\u00b3",
      "m" + "\u00b3"};

  private static final long serialVersionUID = 1L;

  /**
   * Constructs a Display JTextField.
   */
  public DisplayField()
  {
    super();
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setupInput();
    setupUnitMenu();

    add(display);
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(calculatedUnitMenu);

    complete = false;
    uu = new UnitUtility();
  }

  /**
   * Creates the unit drop down menu.
   */
  private void setupUnitMenu()
  {
    calculatedUnitMenu = new JComboBox<String>(UNIT_LIST);
    calculatedUnitMenu.setPreferredSize(new Dimension(100, 45));
    calculatedUnitMenu.setEditable(false);

    calculatedUnitMenu.addItemListener(new ItemListener()
    {
      @Override
      public void itemStateChanged(ItemEvent arg0)
      {
        if (complete && getUnit() != null)
        {
          if (resultUnit.length() > 0)
            recalculate();
        }
      }
    });
  }

  /**
   * Recalculate converts the number in the display to a different unit when a new unit is selected
   * from the unit menu.
   */
  private void recalculate()
  {
    String unit = uu.getUnitType(getUnit());

    double converted = result;
    if (unit == null)
    {
      return;
    }
    // Check what type the unit is and convert it accordingly
    else if (unit.equals("Length"))
    {
      converted = UnitConversion.lengthConversion(resultUnit, getUnit(), result);
    }
    else if (unit.equals("Volume"))
    {
      converted = UnitConversion.volumeConversion(resultUnit, getUnit(), result);
    }
    else if (unit.equals("Time"))
    {
      converted = UnitConversion.timeConversion(resultUnit, getUnit(), result);
    }
    else
    {
      converted = UnitConversion.massConversion(resultUnit, getUnit(), result);
    }
    setText(String.valueOf(converted));
  }

  /**
   * Creates the display text field.
   */
  private void setupInput()
  {
    display = new JTextField();
    display.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
    display.setBackground(Color.PINK);
    display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    display.setText("");
    display.setEditable(false);

  }

  /**
   * Sets the text of the display field. Also takes the unit out of the text and sets it in the unit
   * drop down.
   * 
   * @param text
   *          to set to the display field.
   */
  public void setText(String text)
  {
    if (text == null || text.length() < 1)
    {
      display.setText(text);
    }
    else
    {
      String[] split = text.split(" ");
      display.setText(split[0]);
      if (split.length >= 2)
      {
        setList(split[1]);
        if (split.length == 3)
        {
          display.setText(split[0] + " " + split[2]);
        }
      }
    }
  }

  /**
   * Returns the text of the display field.
   * 
   * @return text in the display field
   */
  public String getText()
  {
    return display.getText();
  }

  /**
   * GetDisplay method returns the display JTextField.
   * 
   * @return display
   */
  public JTextField getDisplay()
  {
    return display;
  }

  /**
   * Checks the parameter unit for its type and sets the list of resulting units to all like units.
   * 
   * @param unit
   *          To check type and set list of
   */
  public void setList(String unit)
  {
    String type = this.uu.getUnitType(unit);
    if (type == null)
    {
      setUnit(unit);
      return;
    }
    calculatedUnitMenu.removeAllItems();
    // Add the custom unit to the list first
    if (((DefaultComboBoxModel<String>) calculatedUnitMenu.getModel()).getIndexOf(unit) < 0)
    {
      calculatedUnitMenu.addItem(unit);
    }

    if (type.equals("Length"))
    {
      Map<String, Double> length = UnitUtility.getUnitTypesLength();
      for (Map.Entry<String, Double> entry : length.entrySet())
      {
        if (((DefaultComboBoxModel<String>) calculatedUnitMenu.getModel())
            .getIndexOf(entry.getKey()) < 0)
        {
          calculatedUnitMenu.addItem(entry.getKey());
        }
      }

    }
    else if (type.equals("Mass"))
    {

      Map<String, Double> mass = UnitUtility.getUnitTypesMass();
      for (Map.Entry<String, Double> entry : mass.entrySet())
      {
        if (((DefaultComboBoxModel<String>) calculatedUnitMenu.getModel())
            .getIndexOf(entry.getKey()) < 0)
        {
          calculatedUnitMenu.addItem(entry.getKey());
        }
      }
    }
    else if (type.equals("Time"))
    {

      Map<String, Double> time = UnitUtility.getUnitTypesTime();
      for (Map.Entry<String, Double> entry : time.entrySet())
      {
        if (((DefaultComboBoxModel<String>) calculatedUnitMenu.getModel())
            .getIndexOf(entry.getKey()) < 0)
        {
          calculatedUnitMenu.addItem(entry.getKey());
        }
      }
    }
    else if (type.equals("Volume"))
    {

      Map<String, Double> volume = UnitUtility.getUnitTypesVolume();
      for (Map.Entry<String, Double> entry : volume.entrySet())
      {
        if (((DefaultComboBoxModel<String>) calculatedUnitMenu.getModel())
            .getIndexOf(entry.getKey()) < 0)
        {
          calculatedUnitMenu.addItem(entry.getKey());
        }
      }
    }
    else if (type.equals("Area"))
    {
      calculatedUnitMenu.addItem("m" + "\u00b2");
      calculatedUnitMenu.addItem("ft" + "\u00b2");
      calculatedUnitMenu.addItem("in" + "\u00b2");
    }
    else
    {
      calculatedUnitMenu.addItem("m/s");
      calculatedUnitMenu.addItem("mi/hr");
    }

  }

  /**
   * Resets the result unit list to have all units.
   */
  public void resetUnitList()
  {
    calculatedUnitMenu.removeAllItems();
    for (int i = 0; i < UNIT_LIST.length; i++)
    {
      calculatedUnitMenu.addItem(UNIT_LIST[i]);
    }
  }

  /**
   * Returns a String of the user's selected unit from the drop down menu.
   * 
   * @return the user's input from drop down menu.
   */
  public String getUnit()
  {
    return (String) calculatedUnitMenu.getSelectedItem();
  }

  /**
   * Locks the result number and unit for use while converting units.
   */
  public void lockResult()
  {
    complete = true;
    result = Double.parseDouble(getText());
    resultUnit = getUnit();
  }

  /**
   * Unlocks the result number and unit to allow for new calculations.
   */
  public void unlockResult()
  {
    complete = false;
  }

  /**
   * Gets all the text from the display field.
   * 
   * @return All text in display.
   */
  public String getAllDisplay()
  {
    return display.getText() + " " + calculatedUnitMenu.getSelectedItem();
  }

  /**
   * SetUnit removes all items from the unit list and adds the parameter item to the unit list.
   * 
   * @param item
   *          to add to unit list.
   */
  public void setUnit(String item)
  {
    calculatedUnitMenu.removeAllItems();
    calculatedUnitMenu.addItem(item);
  }
  
  /* PUBLIC METHODS FOR TESTING PURPOSES */
  
  /**
   * Testing method to change the selected item, firing the listener
   * on the JComboBox, calculatedUnitMenu.
   * 
   * @param text To change the selected unit to.
   */
  public void changeSelectedItem(String text) {
	resetUnitList();
	calculatedUnitMenu.setSelectedItem(text);  
  }
  
  /**
   * Testing method to get the locked result.
   * 
   * @return result that is locked.
   */
  public double getResult() {
	  return this.result;
  }

}
