package core;

import com.borland.silktest.jtf.Desktop;
import org.junit.Before;
import com.borland.silktest.jtf.BaseState;
import com.microfocus.silktest.jtf.swing.JButton;
import com.microfocus.silktest.jtf.swing.JEditorPane;
import com.microfocus.silktest.jtf.swing.JLabel;
import com.microfocus.silktest.jtf.swing.JMenuItem;
import com.microfocus.silktest.jtf.swing.JPasswordField;
import com.microfocus.silktest.jtf.swing.JToggleButton;


import com.microfocus.silktest.jtf.swing.JTextField;
import com.microfocus.silktest.jtf.swing.JTable;
import com.microfocus.silktest.jtf.swing.JTextArea;
import com.microfocus.silktest.jtf.swing.JTabbedPane;

public abstract class BasicDX
{

  public Desktop desktop = new Desktop();

  @Before
  public void executebaseState ()
  {
    BaseState baseState = new BaseState();
    baseState.execute(desktop);

  }

  // Clicks on a location based off the input
  public void clickHeader (String word)
  {
    if (exists(word)) {
      try {
        desktop.<JToggleButton>find(word).click();
      } catch (Exception e) {
        try {
          desktop.<JButton>find(word).click();

        } catch (Exception d) {
          System.out.println("Not a ");
        }

      }

    }
  }

  /*
   * Logs a string
   */
  public void logInfo (String info)
  {
    desktop.logInfo(info);
  }

  /*
   * Logs an error
   */
  public void logError (String error)
  {
    desktop.logError(error);
  }

  public void waitForObject (String locator)
  {
    desktop.waitForObject(locator);
  }

  /*
   * Checks if there is a locator that exists on the active window
   */
  public boolean exists (String locator)
  {
    return desktop.exists(locator);
  }

  /*
   * Locates a JMenu Item
   */
  public JMenuItem findJMenuItem (String locator)
  {
    return (desktop.<JMenuItem>find(locator));
  }

  /*
   * Locates a JTextField
   */
  public JTextField findJTextField (String locator)
  {

    return desktop.<JTextField>find(locator);

  }

  /*
   * Locates a JTable
   */
  public JTable findJTable (String locator)
  {
    return desktop.<JTable>find(locator);
  }

  /*
   * Locates a JPasswordField
   */
  public JPasswordField findPasswordField (String locator)
  {
    return desktop.<JPasswordField>find(locator);
  }

  /*
   * Locates a JButton
   */
  public JButton findJButton (String locator)
  {
    return desktop.<JButton>find(locator);
  }

  /*
   * Locates a JTabbedPane
   */

  public JTabbedPane findJTabbedPane (String locator)
  {
    return desktop.<JTabbedPane>find(locator);
  }

  /*
   * Locates a TestObject
   */
  public JTextArea findJTextArea (String locator)
  {

    return desktop.<JTextArea>find(locator);
  }

  /*
   * Locates a JEditorPane
   */
  public JEditorPane getJEditorPane (String locator)
  {
    return desktop.<JEditorPane>find(locator);
  }

  public JLabel findEmJLabel (String locator)
  {
    String JLabel = String.format("//JLabel[@caption=\'%s\']", locator);
    return desktop.<JLabel>find(JLabel);
  }

  public JEditorPane getEmJEditorPane (String locator)
  {
    String JEditor = String.format("//JEditorPane[@priorlabel=\'%s\']", locator);
    return desktop.<JEditorPane>find(JEditor);
  }

  protected void sleep (long time)
  {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }

  }
}
