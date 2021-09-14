package united;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Creates a Panel of Operation, Clear, Reset, and Equal Buttons.
 * 
 * @author Team02
 * @version 4/3/2020
 */
public class ButtonPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;
	private JButton[] controlButtons;

	/**
	 * Constructs a panel of buttons that responds to an ActionListener.
	 * 
	 * @param actionListener is the ActionListener.
	 */
	public ButtonPanel(ActionListener actionListener)
	{
		super();
		this.actionListener = actionListener;
		controlButtons = new JButton[4];
		setupLayout();
	}

	/**
	 * Setup the layout of the panel of buttons.
	 */
	private void setupLayout()
	{
		setLayout(new GridLayout(5, 1, 10, 10));
		add(createRow1());
		add(createRow2());
		add(createRow3());
		add(createRow4());
		add(createRow5());

		changeButtonColor(Color.YELLOW);

		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
	}

	/**
	 * Creates the first row in the panel of buttons.
	 * 
	 * @return a formatted JPanel of the first row of buttons.
	 */
	private JPanel createRow1()
	{
		JPanel row1 = new JPanel();
		row1.setLayout(new GridLayout(1, 5, 10, 10));

		JButton plusMinus = createButton("\u00b1");
		controlButtons[0] = plusMinus;
		row1.add(plusMinus);

		JButton cancelButton = createButton("C");
		controlButtons[1] = cancelButton;
		row1.add(cancelButton);

		JButton resetButton = createButton("R");
		controlButtons[2] = resetButton;
		row1.add(resetButton);

		row1.add(createButton("+"));

		row1.add(createButton("<html>X<sup>Y</sup></html>"));

		return row1;
	}

	/**
	 * Creates the second row in the panel of buttons.
	 * 
	 * @return a formatted JPanel of the second row of buttons.
	 */
	private JPanel createRow2()
	{
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(1, 5, 10, 10));

		for (int i = 1; i < 4; i++)
		{
			row2.add(createButton(String.format("%d", i)));
		}

		row2.add(createButton("\u2212"));

		row2.add(createButton("1/x"));

		return row2;
	}

	/**
	 * Creates the third row in the panel of buttons.
	 * 
	 * @return a formatted JPanel of the third row of buttons.
	 */
	private JPanel createRow3()
	{
		JPanel row3 = new JPanel();
		row3.setLayout(new GridLayout(1, 5, 10, 10));

		for (int i = 4; i < 7; i++)
		{
			row3.add(createButton(String.format("%d", i)));
		}

		row3.add(createButton("\u00d7"));

		row3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 83));

		return row3;
	}

	/**
	 * Creates the fourth row in the panel of buttons.
	 * 
	 * @return a formatted JPanel of the fourth row of buttons.
	 */
	private JPanel createRow4()
	{
		JPanel row4 = new JPanel();
		row4.setLayout(new GridLayout(1, 5, 10, 10));

		for (int i = 7; i < 10; i++)
		{
			row4.add(createButton(String.format("%d", i)));
		}

		row4.add(createButton("\u00f7"));

		row4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 83));

		return row4;
	}

	/**
	 * Creates the fifth row in the panel of buttons.
	 * 
	 * @return a formatted JPanel of the fifth row of buttons.
	 */
	private JPanel createRow5()
	{
		JPanel row5 = new JPanel();
		row5.setLayout(new GridLayout(1, 2, 10, 10));

		JPanel equalsBackspace = new JPanel();
		equalsBackspace.setLayout(new GridLayout(1, 3, 10, 10));

		JButton backspace = createButton("\u232B");
		controlButtons[3] = backspace;
		equalsBackspace.add(backspace);

		equalsBackspace.add(createButton("="));

		row5.add(createButton("0"));

		row5.add(equalsBackspace);
		row5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 83));

		return row5;
	}

	/**
	 * Creates individual buttons based on the given symbol.
	 * 
	 * @param symbol is the text that will appear on the button.
	 */
	private JButton createButton(String symbol)
	{
		JButton button = new JButton(symbol);
		Font font = new Font("DejaVu Sans", Font.BOLD, 20);
		button.setFont(font);
		button.setBackground(Color.LIGHT_GRAY);
		button.setPreferredSize(new Dimension(75, 75));
		button.addActionListener(actionListener);
		return button;
	}

	/**
	 * Changes the color of the control buttons.
	 * 
	 * @param color is the new color of the control buttons.
	 */
	public void changeButtonColor(Color color)
	{
		for (int i = 0; i < controlButtons.length; i++)
		{
			controlButtons[i].setForeground(color);
		}
	}

	/**
	 * Returns the current color of the buttons.
	 * 
	 * @return the current color of the buttons. 
	 */
	public Color getCurrentColor()
	{
		return controlButtons[0].getForeground();
	}

}
