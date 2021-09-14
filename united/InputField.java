package united;

import java.util.Map;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Creates an InputField Panel.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class InputField extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static final String[] UNIT_LIST =
	{ "", "mm", "cm", "in", "ft", "yd", "m", "km", "mi", "s", "min", "hr", "mL", "L", "gal", "mg", "g", "oz", "lb",
			"kg", "tons", "m/s", "mi/hr", "m" + "\u00b2", "ft" + "\u00b2" };
	private JComboBox<String> unitMenu;
	private JTextField input;
	private String measureType = "";
	private String conversion = "";

	/**
	 * Creates a InputField.
	 * 
	 * @param uu - an instance of a UnitUtility
	 */
	public InputField(UnitUtility uu)
	{
		super();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setupInput();
		setupUnitMenu();

		add(input);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(unitMenu);
	}

	/**
	 * Creates the unit drop down menu.
	 */
	private void setupUnitMenu()
	{
		unitMenu = new JComboBox<String>(UNIT_LIST);
		unitMenu.setPreferredSize(new Dimension(100, 45));
		unitMenu.setEditable(true);
	}

	/**
	 * Creates the input text field.
	 */
	private void setupInput()
	{
		input = new JTextField();
		input.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		input.setBackground(Color.WHITE);
		input.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		input.setHorizontalAlignment(SwingConstants.RIGHT);
		input.setText("");
	}

	/**
	 * Determines if the user's input in the text field is valid. User may only
	 * enter positive or negative digits.
	 * 
	 * @return true if the user's input in the text field is valid.
	 */
	public boolean hasValidDigitInput()
	{
		if (input.getText().length() <= 0)
		{
			return false;

		}

		boolean result = false;

		for (int i = 0; i < input.getText().length(); i++)
		{
			if (Character.isDigit(input.getText().charAt(i)) || input.getText().charAt(i) == '-'
					|| input.getText().charAt(i) == '/')
			{
				result = true;

			} else
			{
				return false;
			}
		}

		return result;
	}

	/**
	 * Resets all input components.
	 */
	public void resetInput()
	{
		input.setText("");
		unitMenu.setSelectedItem("");
	}

	/**
	 * Returns a String of the user's entire input, which may or may not consist of
	 * units.
	 * 
	 * @return the user's input from the text field and drop down menu.
	 */
	public String getAllInput()
	{
		return input.getText() + " " + unitMenu.getSelectedItem();
	}

	/**
	 * Returns a String of the user's selected unit from the drop down menu.
	 * 
	 * @return the user's input from drop down menu.
	 */
	public String getUnit()
	{
		String unit = (String) unitMenu.getSelectedItem();

		String[] unitTypes =
		{ "Time", "Length", "Mass", "Volume" };
		Map<String, String> defaultUnits = UnitUtility.getUnitTypes();

		if (!defaultUnits.containsKey(unit) && unit.length() > 0)
		{
			String type = "";

			measureType = (String) JOptionPane.showInputDialog(this, "Select unit measure type.", "Custom Units",
					JOptionPane.PLAIN_MESSAGE, null, unitTypes, "Time");
			if (measureType == null)
			{
				measureType = "";
			} else
			{

				if (measureType.equals("Time"))
				{
					type = "seconds";
					UnitUtility.getUnitTypes().put(unit, "Time");
				}

				if (measureType.equals("Length"))
				{
					type = "millimeters";
					UnitUtility.setUnitType(unit, "Length");
				}

				if (measureType.equals("Mass"))
				{
					type = "milligrams";
					UnitUtility.setUnitType(unit, "Mass");
				}
				if (measureType.equals("Volume"))
				{
					type = "milliliters";
					UnitUtility.setUnitType(unit, "Volume");
				}

				conversion = (String) JOptionPane.showInputDialog(this,
						"Type in a conversion with new units to " + type, JOptionPane.INFORMATION_MESSAGE);

				if (conversion == null)
				{
					conversion = "";
				} else
				{

					switch (measureType)
					{
					case "Time":
						UnitUtility.getUnitTypesTime().put(unit, Double.parseDouble(conversion));
						break;
					case "Length":
						UnitUtility.getUnitTypesLength().put(unit, Double.parseDouble(conversion));
						break;
					case "Mass":
						UnitUtility.getUnitTypesMass().put(unit, Double.parseDouble(conversion));
						break;
					case "Volume":
						UnitUtility.getUnitTypesVolume().put(unit, Double.parseDouble(conversion));
						break;
					}
				}
			}
		}

		return unit;
	}

	/**
	 * Sets the text of the input text field.
	 * 
	 * @param text is the text that the input text field will display.
	 */
	public void setText(String text)
	{
		input.setText(text);
	}

	/**
	 * Returns the text of the input text field.
	 * 
	 * @return the text of the input text field.
	 */
	public String getText()
	{
		return input.getText();
	}

	/**
	 * Sets the unit to the parameter.
	 * 
	 * @param unit set to
	 */
	public void setUnits(String unit)
	{
		unitMenu.setSelectedItem(unit);
	}

}
