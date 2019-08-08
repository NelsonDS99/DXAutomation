package com.cepheid.dx.automation.core;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class InputDialog extends BasicDX
{

  String folder;

  /*
   * Select the excel document based off the folder selected
   */
  public String selectDocument (String folder)
  {
    String[] options = arrayCreator(folder);
    ImageIcon icon1 = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\zeldaLogo.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose an excel Document", "Excel Option",
        JOptionPane.QUESTION_MESSAGE, icon1, options, options[0]);

    return input;
  }

  /*
   * Choose the folder that corresponds with the given nxx file loaded
   */
  public String selectFolder ()
  {
    String[] options = { "D6780 Customize Test Result", "D6781 Organism-Based Results", "D8380 Probe Check",
        "D8383 Background Subtraction and Crosstalk Correction", "D8384 Percent Ratio" };
    ImageIcon icon2 = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\metroidHelmet.png");
    String input = (String) JOptionPane.showInputDialog(null, "Please choose a test result folder", "Folder Option",
        JOptionPane.QUESTION_MESSAGE, icon2, options, options[0]);
    folder = input;

    return input;
  }

  /*
   * If there are multiple sheets within the excel document that have verification
   * results, choose which sheet the user would like to test
   */
  public String selectSheet (String document)
  {
    String sheet = null;
    String options[];
    logInfo(folder);
    switch (folder) {
      case "D6780 Customize Test Result":
        switch (document) {
          case "VerifyResultText & Color Organism":
            sheet = "GUIVerification2";
            break;
          case "VerifyResultText_and_Color":
            options = new String[] { "TestRes3", "TestRes5" };
            sheet = sheetOption(options);
            break;
          case "VerifyResultText_and_Color-6-color":
            sheet = "TestRes6Target";
            break;
        }
        break;
      case "D8380 Probe Check":
        switch (document) {
          case "ProbeCheckCombinatorial":
          case "ProbeCheckNormalization1":
            sheet = "TestResult3";
            break;
          case "ProbeCheckNormalizationMelt":
            sheet = "Melt";
            break;
          case "ProbeCheckVerification1":
            options = new String[] { "LSP1", "TestResult6", "PrbChk2", "PrbChk-3_7", "PrbChk-8", "PrbChk-9_13" };
            sheet = sheetOption(options);
            break;
        }
        break;
      case "D8383 Background Subtraction and Crosstalk Correction":
        switch (document) {
          case "ExpectedAnalyteResultsBkgndSub":
            options = new String[] { "2Analytes", "3Analytes", "4Analytes", "6Analytes" };
            sheet = sheetOption(options);
            break;
          case "ExpectedAnalyteResultsBkgndValidation1":
            sheet = "Tests";
            break;
          case "ExpectedAnalyteResultsCombinatorial":
            options = new String[] { "3Analytes", "6Analytes" };
            sheet = sheetOption(options);
            break;
          case "ExpectedAnalyteResultsCrosstalk":
            options = new String[] { "3Analytes", "4Analytes", "6Analytes" };
            sheet = sheetOption(options);
            break;
        }
        break;
      case "D8384 Percent Ratio":
        switch (document) {
          case "VerifyD8384":
            options = new String[] { "PercentRatio", "LSP" };
            sheet = sheetOption(options);
            break;
        }
        break;
      case "D6781 Organism-Based Results":
        sheet = "AllTestResults";
        break;
    }

    return sheet;
  }

  public String getTestResult ()
  {
    if (folder == null)
      return "Extra";
    return folder;
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
      case "D8383 Background Subtraction and Crosstalk Correction":
        files = new String[] { "ExpectedAnalyteResultsBkgndSub", "ExpectedAnalyteResultsBkgndValidation1",
            "ExpectedAnalyteResultsCombinatorial", "ExpectedAnalyteResultsCrosstalk" };
        break;
      case "D8384 Percent Ratio":
        files = new String[] { "VerifyD8384" };
        break;
    }
    return files;
  }

  private String sheetOption (String[] options)
  {
    ImageIcon icon3 = new ImageIcon("C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\src\\shyGuy.png");
    return (String) JOptionPane.showInputDialog(null, "Please choose a sheet", "Sheet Option",
        JOptionPane.QUESTION_MESSAGE, icon3, options, options[0]);
  }

}
