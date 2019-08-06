package com.cepheid.dx.automation.core;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class InputDialog extends BasicDX
{
  /*
   * Select the excel document based off the folder selected
   */
  public String selectDocument (String folder)
  {
    String[] options = arrayCreator(folder);
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\zeldaLogo.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose an excel Document", "Excel Option",
        JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

    return input;
  }

  /*
   * Choose the folder that corresponds with the given nxx file loaded
   */
  public String selectFolder ()
  {
    String[] options = { "D6780 Customize Test Result", "D6781 Organism-Based Results", "D8380 Probe Check" };
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\metroidHelmet.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose a test result folder", "Folder Option",
        JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

    return input;
  }

  /*
   * If there are multiple sheets within the excel document that have verification
   * results, choose which sheet the user would like to test
   */
  public String selectSheet (String document)
  {
    ImageIcon icon = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\shyGuy.png");
    String sheet;
    String options[];
    switch (document) {
      case "VerifyResultText & Color Organism":
        sheet = "GUIVerification2";
        break;
      case "VerifyResultText_and_Color":
        options = new String[] { "TestRes3", "TestRes5" };
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
        options = new String[] { "LSP1", "TestResult6" };
        sheet = (String) JOptionPane.showInputDialog(null, "Please choose a sheet", "Sheet Option",
            JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        break;
      default:
        sheet = "AllTestResults";
        break;
    }

    return sheet;
  }

  /*
   * Creates an array of the different excel sheets each folder can open
   */
  private String[] arrayCreator (String folder)
  {
    String[] files = null;
    switch (folder) {
      case "D6780 Customize Test Result":
        files = new String[] { "VerifyResultText_and_Color", "VerifyResultText_and_Color-6-color",
            "VerifyResultText & Color Organism" };
        break;
      case "D6781 Organism-Based Results":
        files = new String[] { "D6781 Semi-quantitative Organism Test Results Verification",
            "D6781 Organism Type Combination Test Results Verification",
            "D6781 Delta Ct Organism Test Results Verification", "D6781 CTNG Test Results Verification",
            "D6781 Basic Organism Based Test Results Verification" };
        break;
      case "D8380 Probe Check":
        files = new String[] { "ProbeCheckCombinatorial", "ProbeCheckNormalization1", "ProbeCheckNormalizationMelt",
            "ProbeCheckVerification1" };
        break;
    }
    return files;
  }

}
