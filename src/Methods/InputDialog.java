package Methods;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import core.BasicDX;

public class InputDialog extends BasicDX
{
  public String selectDocument (String folder)
  {
    String[] options = arrayCreator(folder);
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\zeldaLogo.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose an excel Document", "Excel Option",
        JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

    return input;
  }

  public String selectFolder ()
  {
    String[] options = { "D6780 Silk Data Files", "D6781 Silk Data Files", "D8380 Silk Data Files" };
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\metroidHelmet.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose a test result folder", "Folder Option",
        JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

    return input;
  }

  public String selectSheet (String document)
  {
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\shyGuy.png");
    String sheet;
    String options [];
    switch (document) {
      case "VerifyResultText & Color Organism":
        sheet = "GUIVerification2";
        break;
      case "VerifyResultText_and_Color":
         options = new String [] { "TestRes3", "TestRes5" };
        sheet = (String) JOptionPane.showInputDialog(null, "Please choose a sheet", "Sheet Option",
            JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        break;
      case "VerifyResultText_and_Color-6-color":
        sheet = "TestRes6Target";
        break;
      case "ProbeCheckCombinatorial":
      case "ProbeCheckNormalization1":
        sheet = "TestResult3";
        break;
      case "ProbeCheckNormalizationMelt":
        sheet = "Melt";
        break;
      case "ProbeCheckVerification1":
        options = new String []{"LSP1", "TestResult6"};
        sheet = (String) JOptionPane.showInputDialog(null, "Please choose a sheet", "Sheet Option",
            JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        break;
      default:
        sheet = "AllTestResults";
        break;
    }

    return sheet;
  }

  private String[] arrayCreator (String folder)
  {
    String[] files = null;
    switch (folder) {
      case "D6780 Silk Data Files":
        files = new String[] { "VerifyResultText_and_Color", "VerifyResultText_and_Color-6-color",
            "VerifyResultText & Color Organism" };
        break;
      case "D6781 Silk Data Files":
        files = new String[] { "D6781 Semi-quantitative Organism Test Results Verification",
            "D6781 Organism Type Combination Test Results Verification",
            "D6781 Delta Ct Organism Test Results Verification", "D6781 CTNG Test Results Verification",
            "D6781 Basic Organism Based Test Results Verification" };
        break;
      case "D8380 Silk Data Files":
        files = new String[] { "ProbeCheckCombinatorial", "ProbeCheckNormalization1", "ProbeCheckNormalizationMelt",
            "ProbeCheckVerification1" };
        break;
    }
    return files;
  }

}
