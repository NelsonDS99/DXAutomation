package Methods;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import core.BasicDX;

public class InputDialog extends BasicDX
{
  public String selectDocument ()
  {
    String[] options = { "D6781 Semi-quantitative Organism Test Results Verification",
        "D6781 Organism Type Combination Test Results Verification",
        "D6781 Delta Ct Organism Test Results Verification", "D6781 CTNG Test Results Verification",
        "D6781 Basic Organism Based Test Results Verification" };
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\zeldaLogo.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose an excel Document", "Excel Option",
        JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

    return input;
  }
}
