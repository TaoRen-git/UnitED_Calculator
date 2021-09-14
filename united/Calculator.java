package united;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

/**
 * Calculator JFrame class that encapsulates the components of a calculator.
 * 
 * @author Team02
 * @version 4/25/2020
 */
public class Calculator extends JFrame implements ActionListener, ComponentListener
{
  private static final long serialVersionUID = 1L;
  private DisplayInputField displayInput;
  private ButtonPanel buttons;
  private HistoryOpenButton historyOpen;

  private MenuBar menu;
  private JLabel imageLabel;
  private Desktop desktop;
  private URI uri;
  private Timer timer;
  private PrinterController printer;
  private HistoryPanel historyPanel;
  private HistoryDisplay historyDisplay;
  private boolean openDisplay;

  /**
   * Creates a Calculator.
   * 
   * @throws URISyntaxException
   */
  public Calculator()
  {
    super();

    historyPanel = new HistoryPanel();
    timerSetup();
    displayInput = new DisplayInputField(historyPanel);
    buttons = new ButtonPanel(displayInput);
    historyDisplay = new HistoryDisplay(historyPanel, timer, this);
    historyOpen = new HistoryOpenButton(this);
    menu = new MenuBar(this);
    desktop = Desktop.getDesktop();
    addComponentListener(this);
    setupLayout();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Sets up the timer to run the slide out animation for the history display.
   */
  private void timerSetup()
  {
    timer = new Timer(8, new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        Point frameLoc = getLocation();
        Point historyLoc = historyDisplay.getLocation();

        Point newLoc = new Point(historyLoc);
        if (!openDisplay)
        {
          historyOpen.setVisible(false);
          newLoc.x -= 50;
          historyDisplay.setLocation(newLoc);
          historyDisplay.setVisible(false);
          if (newLoc.equals(frameLoc))
          {
            timer.stop();
            historyOpen.setVisible(true);
            historyDisplay.setLocation(newLoc);
          }
        }
        else
        {
          historyOpen.setVisible(true);
          newLoc.x += 50;
          historyDisplay.setLocation(newLoc);
          if (newLoc.equals(new Point(getLocation().x + 500, getLocation().y)))
          {
            timer.stop();
            historyOpen.setVisible(false);
            historyDisplay.setLocation(newLoc);
            historyDisplay.setVisible(true);
          }
        }
      }

    });
  }

  /**
   * Creates the main panel.
   * 
   * @return a JPanel with the main panel components.
   */
  private JPanel createMainPanel()
  {
    JPanel main = new JPanel();
    main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
    main.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
    main.add(displayInput);
    main.add(buttons);
    return main;
  }

  /**
   * Sets up the Calculator JFrame's layout.
   */
  private void setupLayout()
  {
    setLayout(new BorderLayout());
    getContentPane().add(historyOpen, BorderLayout.EAST);
    getContentPane().add(createMainPanel(), BorderLayout.CENTER);
    setupLogo();
    getContentPane().add(imageLabel, BorderLayout.NORTH);
    setTitle("unitEd");
    setSize(500, 700);
    setResizable(false);
    setJMenuBar(menu);
  }

  /**
   * Sets up the default logo configuration.
   */
  private void setupLogo()
  {
    ImageIcon icon = new ImageIcon(getClass().getResource("unitED_Logo.png"));
    imageLabel = new JLabel("", resizeImage(icon), JLabel.LEFT);
    imageLabel.setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 0));
  }

  /**
   * Performs actions for buttons and menu bar options.
   * 
   * @param event
   *          is the type of action that needs to be performed.
   */
  @Override
  public void actionPerformed(ActionEvent event)
  {
    String command = event.getActionCommand();

    if (command.equals("Print"))
    {
      printer = new PrinterController(historyPanel);
    }

    if (command.equals("Change Button Color"))
    {
      buttons.changeButtonColor(getUserColorChoice(buttons.getCurrentColor()));
    }

    if (command.equals("Change Display Field Color"))
    {
      displayInput.setDisplayColor(getUserColorChoice(displayInput.getCurrentColor()));
    }

    if (command.equals("Configure Logo"))
    {
      configureLogo();
    }

    if (command.equals("Help"))
    {
      try
      {
        uri = new URI("http://w3stu.cs.jmu.edu/harbicrb/");
      }
      catch (URISyntaxException e)
      {
        e.printStackTrace();
      }
      try
      {
        desktop.browse(uri);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    if (command.equals("About"))
    {
      JFrame about = new JFrame("unitED v1.0.0");
      ImageIcon icon = new ImageIcon("unitED_Logo.png", "Logo");
      JLabel aboutImage = new JLabel("", resizeImage(icon), JLabel.CENTER);

      about.setLayout(new BorderLayout());
      about.setSize(500, 300);
      about.setLocation(400, 75);
      JLabel textArea = new JLabel(
          "<html>unitED<br>SagaciousMedia<br>version 1.0.0<br><br><br>"
              + "Vivian Dang<br>Will Madison<br>Lexie Pearce<br>Tao Ren<br>Riley Harbick<html>",
          SwingConstants.CENTER);
      about.add(textArea);
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      about.add(panel, BorderLayout.EAST);
      about.add(aboutImage, BorderLayout.NORTH);
      about.setVisible(true);
    }

    if (command.equals(">"))
    {
      historyDisplay.setLocation(getLocation());
      timer.start();
      openDisplay = true;
    }

    if (command.contentEquals("<"))
    {
      timer.restart();
      openDisplay = false;
    }
  }

  /**
   * Resizes a given image according to fit onto the calculator.
   * 
   * @param image
   *          is the image that needs to be resized.
   * @return a new ImageIcon that has been resized.
   */
  private ImageIcon resizeImage(ImageIcon image)
  {
    Image scaledImage = image.getImage().getScaledInstance(210, 70, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }

  /**
   * Configures a new logo based on a file chosen by the user.
   */
  private void configureLogo()
  {
    File userDirectory = new File(System.getProperty("user.dir"));
    JFileChooser fileChooser = new JFileChooser(userDirectory, FileSystemView.getFileSystemView());
    fileChooser.showOpenDialog(null);
    File file = fileChooser.getSelectedFile();
    BufferedImage fileImage = null;
    if (file != null)
    {
      try
      {
        fileImage = ImageIO.read(file);
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null, "Unable to Open Image File.", "File Error",
            JOptionPane.OK_OPTION);
      }

      if (fileImage == null)
      {
        JOptionPane.showMessageDialog(null, "Unable to Open Image File.", "File Error",
            JOptionPane.OK_OPTION);
      }
      else
      {
        ImageIcon image = new ImageIcon(fileImage, "Logo");
        imageLabel.setIcon(resizeImage(image));
      }
    }
  }

  /**
   * Pops up a JOptionPane for the user to choose a Color for either the display field or the
   * buttons.
   * 
   * @param defaultColor
   *          is the default color that will show if the user presses cancels the selection process.
   * @return the color that the user has chosen.
   */
  private Color getUserColorChoice(Color defaultColor)
  {
    String colorStr = (String) JOptionPane.showInputDialog(this, "Please Select a Color:",
        "Color Selector", JOptionPane.PLAIN_MESSAGE, null, CalculatorColor.COLOR_OPTIONS, "");
    return CalculatorColor.stringToColor(colorStr, defaultColor);
  }

  /**
   * Needed implementation because of the ComponentListener. Performs actions on a hidden component.
   * 
   * @param e
   *          is the component.
   */
  @Override
  public void componentHidden(ComponentEvent e)
  {

  }

  /**
   * Needed implementation because of the ComponentListener. Moves the history display when the
   * calculator moves.
   * 
   * @param e
   *          is the Calculator component.
   */
  @Override
  public void componentMoved(ComponentEvent e)
  {
    if (openDisplay)
    {
      historyDisplay.setLocation(new Point(getLocation().x + 500, getLocation().y));
    }
    else
    {
      historyDisplay.setLocation(getLocation());
    }

  }

  /**
   * Needed implementation because of the ComponentListener. Performs actions on resized components.
   * 
   * @param e
   *          is the component.
   */
  @Override
  public void componentResized(ComponentEvent e)
  {
  }

  /**
   * Needed implementation because of the ComponentListener. Performs actions on shown components.
   * 
   * @param e
   *          is the component.
   */
  @Override
  public void componentShown(ComponentEvent e)
  {
  }

}
