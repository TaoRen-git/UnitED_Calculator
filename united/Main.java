package united;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/**
 * The application for running the program.
 * 
 * @author Team02
 * @version 4/9/2020
 */
public class Main implements Runnable
{
  
  /**
   * The entry point of the application (which is executed in the main thread of execution).
   *
   * @param args
   *          The command-line arguments
   * @throws InvocationTargetException
   */
  
  public static void main(String[] args) throws InterruptedException, InvocationTargetException
  {
    SwingUtilities.invokeAndWait(new Main());
  }

  /**
   * The code that is executed in the event dispatch thread.
   */
  public void run()
  {
    Calculator window = new Calculator();
    window.setVisible(true);
  }

}
