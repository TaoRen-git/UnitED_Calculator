package united;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Deals with printing the calculation history. 
 * 
 * @author Team02
 * @version 4/26/20
 *
 */
public class PrinterController implements Printable
{
	private final int lineHeight = 15;
	private ArrayList<String> expressionList;

	/**
	 * Creates a Printer Controller.
	 * 
	 * @param historyDisplay - an instance of a History Panel.
	 */
	public PrinterController(HistoryPanel historyDisplay)
	{
		PrinterJob printJob = PrinterJob.getPrinterJob();
		expressionList = historyDisplay.getexpressionList();

		try
		{
			printJob.setPrintable(this);
			boolean shouldPrint = printJob.printDialog();

			if (shouldPrint)
			{
				printJob.print();
			}
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(historyDisplay, "Unable to print!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Prints a graphic, page, in the page format passed.
	 */
	public int print(Graphics g, PageFormat pageFormat, int page)
	{

		Graphics2D g2d;
		if (page == 0)
		{ // Create a graphic2D object
			g2d = (Graphics2D) g;
			g2d.setColor(Color.black);

			// Translate the origin to be (0,0)
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

			String title = "Calculation History";
			int mid = (int) Math.round((pageFormat.getWidth() / 2)) - title.length();
			g2d.drawString(title, mid, 10);

			int curline = 25;
			for (String expression : expressionList)
			{
				g2d.drawString(expression, 0, curline);
				curline += lineHeight;
			}

			return (PAGE_EXISTS);
		} else
			return (NO_SUCH_PAGE);
	}

}