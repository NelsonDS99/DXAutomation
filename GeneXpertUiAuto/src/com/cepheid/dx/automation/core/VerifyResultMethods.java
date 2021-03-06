package com.cepheid.dx.automation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.microfocus.silktest.jtf.swing.JTable;

public class VerifyResultMethods extends htmlCreator
{
  Map<String, Boolean> PF = new HashMap<String, Boolean>();

  /*
   * Compares SampleID of expectedData, finds similarities in JTable and maps
   * locations
   */
  public Map<String, Integer> actualResultsList (Map<String, ArrayList<String>> expectedData)
  {
    findJButton(VIEW_TEST_BTN).click();
    // Create keys and locations map
    Set<String> keys;
    Map<String, Integer> actualData = new HashMap<String, Integer>();
    final int numRows = findJTable(TEST_JTABLE).getRowCount();

    // Iterate through expected map, if JTable contains assay map the location
    for (int count = 0; count < numRows; count++) {
      keys = expectedData.keySet();
      String actualSampleID = findJTable(TEST_JTABLE).getCellText(count, 1);
      if (keys.contains(actualSampleID)) {
        actualData.put(actualSampleID, count);
      }
    }
    findJButton(TEST_CANCEL_BTN).click();
    // Return JTable
    return actualData;
  }

  /*
   * Click specific assay in JTable
   */
  public void clickResult (int rowCount)
  {
    findJButton(VIEW_TEST_BTN).click();

    JTable viewTest = findJTable(TEST_JTABLE);

    viewTest.clickCell(rowCount, 1);
    findJButton(TEST_OK_BTN).click();
  }

  /*
   * Verify the SampleID, Status, Assay Name
   */
  public ArrayList<String> verifyGuiLeftPanel (Map<String, ArrayList<String>> expectedData, String key)
  {
    waitForObject(VR_LEFT_PANEL);

    String leftSampleID = findJTextArea(VR_SAMPLE_ID_BOX).getText();
    String leftAssayName = findJTextField(VR_ASSAY_BOX).getText();
    String leftStatus;
    ArrayList<String> returnVal = new ArrayList<String>();

    sleep(20);
    if (exists(VR_STATUS_BOX)) {
      leftStatus = findJTextField(VR_STATUS_BOX).getText();
    } else
      leftStatus = "Does Not Exist";
    String exAssayName = expectedData.get(key).get(2);
    String exLeftStatus = expectedData.get(key).get(0);

    // Compare Sample ID
    if (expectedData.containsKey(leftSampleID)) {
      returnVal.add(String.format("Actual ID:   %s \nExpected ID: %s", leftSampleID, key));
    } else {
      returnVal.add(String.format("Error. Actual ID:   %s \n Expected ID:   %s", leftSampleID, key));
      updateFail(key);
    }
    // Compare Assay Name
    if (exAssayName.equals(leftAssayName)) {
      returnVal.add(String.format("Actual Assay Name:   %s \nExpected Assay Name: %s", leftAssayName, exAssayName));
    } else {
      returnVal
          .add(String.format("Error. \nActual Assay Name    %s \nExpected Assay Name: %s", leftAssayName, exAssayName));
      updateFail(key);
    }
    // Compare Status
    if (exLeftStatus.equals(leftStatus)) {
      returnVal.add(String.format("Actual Status:   %s \nExpected Status: %s", leftStatus, exLeftStatus));
    } else {
      returnVal.add(String.format("Error. \nActual Status:   %s \nExpected Status: %s ", leftStatus, exLeftStatus));
      updateFail(key);
    }
    return returnVal;
  }

  /*
   * Verifies the rightPanel Assay Name
   */
  public String verifyRightAssayName (Map<String, ArrayList<String>> expectedData, String key)
  {
    String resultVal = "";
    if (exists(VR_TABBED_PANE)) {
      clickTabbedPane(VR_TABBED_PANE, "Test Result");
    } else
      logError("Tabbed Pane does not exists");

    // Verify Assay Name
    String assayName = findEmJLabel(expectedData.get(key).get(2)).getText();

    String exAssayName = expectedData.get(key).get(2);

    if (exAssayName.equals(assayName)) {
      resultVal = String.format("Actual Assay Name:   %s \nExpected Assay Name: %s", assayName, exAssayName);
    } else {
      resultVal = String.format("Error.\nActual Assay Name:   %s \nExpected Assay Name: %s", assayName, exAssayName);
      updateFail(key);
    }
    return resultVal;
  }

  /*
   * Verifies the text of the Test Result box
   */
  public String verifyTestResultBox (Map<String, ArrayList<String>> expectedData, String key,
      ArrayList<String> resultText)
  {

    String resultVal = "";
    String GUIText = getEmJEditorPane(expectedData.get(key).get(2)).getText();
    String[] sepGUIText = GUIText.split(";\n");

    for (String iterator : sepGUIText) {
      if (iterator.trim().equals(resultText.get(0))) {
        resultVal = resultVal
            .concat(String.format("Actual Text:   %s \nExpected Text: %s\r\n", iterator, resultText.get(0)));
      } else {
        resultVal = resultVal
            .concat(String.format("Error.\nActual Text:   %s \nExpected Text: %s\r\n", iterator, resultText.get(0)));
        updateFail(key);
      }
      resultVal = resultVal.concat("\r\n");
      resultText.remove(0);
    }
    return resultVal;
  }

  /*
   * Verifies the background color and font color in the Test Result box
   */
  public String verifyColor (Map<String, ArrayList<String>> expectedData, String key, ArrayList<String> resultBGColor,
      ArrayList<String> resultFontColor, ArrayList<Integer> resultPos)
  {
    String resultVal = "";
    String formattedText = String.format(locator("//JEditorPane[@priorlabel=\'%s\']"), expectedData.get(key).get(2));
    formattedText = (getJEditorPane(formattedText).getFormattedText());
    logInfo(formattedText);
    for (int rowNum = 0; rowNum < resultPos.size(); rowNum++) {

      // Create unique row information
      String rowGUI = String.format("tr class=\"row%s\"", resultPos.get(rowNum) - 1);
      String rowInfo = String.format("tr.row%s { background-color: #%s; color: #%s }", resultPos.get(rowNum) - 1,
          resultBGColor.get(rowNum), resultFontColor.get(rowNum));

      // Check if the correct row exists
      if (formattedText.indexOf(rowGUI) != -1) {

        // Check if row information is correct
        if (formattedText.indexOf(rowInfo) != -1) {
          resultVal = resultVal.concat(String.format("Actual BC: %s", rowInfo));
        } else {
          resultVal = resultVal.concat(String.format("Error.\nActual BC: %s", rowInfo));
          updateFail(key);
        }
      } else
        resultVal = resultVal.concat(String.format("No Text is Displayed \n %s", rowGUI));

      resultVal = resultVal.concat("\r\n");
    }
    return resultVal;
  }

  /*
   * Verifies the error status of the support tab
   */
  public ArrayList<String> verifyErrorStatus (Map<String, ArrayList<String>> expectedData, String key)
  {

    ArrayList<String> returnVal = new ArrayList<String>();

    if (exists(VR_TABBED_PANE)) {
      clickTabbedPane(VR_TABBED_PANE, "Support");
    } else
      logError("Tabbed Pane does not exist");

    String errorStatus;

    waitForObject(VR_SUPPORT_TAB);

    if (exists(VR_ERROR_STATUS_TXT)) {
      errorStatus = findJTextField(VR_ERROR_STATUS_TXT).getText();
    } else
      errorStatus = "Does Not Exist";
    String exErrorStatus = expectedData.get(key).get(1);

    // Verify the error status
    if (exErrorStatus.equals(errorStatus)) {
      returnVal.add(String.format("Actual Error Status:   %s \nExpected Error Status: %s", errorStatus, exErrorStatus));
    } else {
      returnVal.add(
          String.format("Error. \nActual Error Status:   %s \nExpected Error Status: %s", errorStatus, exErrorStatus));
      updateFail(key);
    }
    return returnVal;
  }

  /*
   * Verifies the Analyte Results Tab of the GUI
   */
  public ArrayList<String> verifyAnalyteResults (Map<String, ArrayList<String>> exAnalyteData, int rowNum)
  {
    ArrayList<String> resultVal = new ArrayList<String>();
    Map<Integer, String> analyteR = extractAnalyteResults(rowNum);
    String key = analyteR.get(0);
    String exCt = exAnalyteData.get(key).get(0);
    String exEndPt = exAnalyteData.get(key).get(1);
    String exInterpretation = exAnalyteData.get(key).get(2).trim();
    String exReason = exAnalyteData.get(key).get(3);
    String exAnalyteResult = exAnalyteData.get(key).get(4).trim();

    resultVal.add("PASS");
    // Compare AnalyteNames
    if (exAnalyteData.containsKey(key)) {
      resultVal.add(String.format("Analyte Name: %s", analyteR.get(0)));
    } else {
      resultVal.add(String.format("Analyte Names are Incorrect: %s", analyteR.get(0)));
      resultVal.set(0, "FAIL");
    }

    // Compare Ct
    if (ctRange(analyteR.get(2), exCt)) {
      resultVal.add(String.format("Actual Ct:   %s \nExpected Ct: %s", analyteR.get(2), exCt));
    } else {
      resultVal.add(String.format("Error.\nActual Ct:   %s \nExpected Ct: %s", analyteR.get(2), exCt));
      resultVal.set(0, "FAIL");
    }
    // Compare EndPt
    if (endPtRange(analyteR.get(3), exEndPt)) {
      resultVal.add(String.format("Actual EndPt:   %s \nExpected EndPt: %s", analyteR.get(3), exEndPt));
    } else {
      resultVal.add(String.format("Error.\nActual EndPt:   %s \nExpected EndPt: %s", analyteR.get(3), exEndPt));
      resultVal.set(0, "FAIL");
    }
    // Compare Interpretation Result
    if (exInterpretation.equals(analyteR.get(4))) {
      resultVal.add(String.format("Actual Interpretation Result:   %s \nExpected Interpretation Result: %s ",
          analyteR.get(4), exInterpretation));
    } else if (exInterpretation.equals("NA")) {
      if (analyteR.get(4).equals("NO RESULT")) {
        resultVal.add(String.format("*** \nActual Interpretation Result:   %s \n Expected Interpretation Result: %s",
            analyteR.get(4), exInterpretation));
      } else {
        resultVal.add(String.format("Error.\nActual Interpretation Result:   %s \nExpected Interpretation Result: %s ",
            analyteR.get(4), exInterpretation));
        resultVal.set(0, "FAIL");
      }
    } else {
      resultVal.add(String.format("Error.\nActual Interpretation Result:   %s \nExpected Interpretation Result: %s ",
          analyteR.get(4), exInterpretation));
      resultVal.set(0, "FAIL");
    }
    // Compare reason
    if (exReason.equals(analyteR.get(5))) {
      resultVal.add(String.format("Actual Reason:   %s \nExpected Reason: %s ", analyteR.get(5), exReason));
      // Check for exceptions of NA
    } else if (exReason.equals("NA")) {
      // Check if the analyte equals nothing
      if (analyteR.get(5).equals("")) {
        resultVal.add(String.format("*** \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), ""));
      }
      // Check if the analyte has a different meaning
      else if (analyteR.get(5).equals("No Ct") || analyteR.get(5).equals("NO RESULT")) {
        resultVal.add(String.format("*** \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), exReason));
      } else {
        resultVal.add(String.format("Error. \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), exReason));
        resultVal.set(0, "FAIL");
      }
    }
    // Check for exceptions of No CT
    else if (exReason.equals("No Ct")) {
      if (analyteR.get(5).equals("")) {
        resultVal.add(String.format("*** \nActual Reason   %s \nExpected Reason: %s ", analyteR.get(5), exReason));
      } else {
        resultVal.add(String.format("Error. \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), exReason));
        resultVal.set(0, "FAIL");
      }
    }
    // Produce error if Ct not found
    else {
      resultVal.add(String.format("Error.\nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), exReason));
      resultVal.set(0, "FAIL");
    }
    // Compare Analyte Result
    if (exAnalyteResult.equals(analyteR.get(6))) {
      resultVal.add(
          String.format("Actual Analyte Result:   %s \nExpected Analyte Result: %s", analyteR.get(6), exAnalyteResult));

    } else {
      resultVal.add(String.format("Error.\nActual Analyte Result:  %s \nExpected Analyte Result: %s", analyteR.get(6),
          exAnalyteResult));
      resultVal.set(0, "FAIL");
    }
    return resultVal;
  }

  /*
   * Verifies the Analyte Details Tab of the GUI
   */
  public ArrayList<String> verifyAnalyteDetails (Map<String, ArrayList<String>> exAnalyteData, int rowNum)
  {

    Map<Integer, String> analyteD = extractAnalyteResults(rowNum);
    ArrayList<String> resultVal = new ArrayList<String>();
    String key = analyteD.get(0);
    String exPrbChk1 = exAnalyteData.get(key).get(7);
    String exPrbChk2 = exAnalyteData.get(key).get(8);
    String exPrbChk3 = exAnalyteData.get(key).get(9);
    String exPrbChkResult = exAnalyteData.get(key).get(5);
    String exDerivPeak = exAnalyteData.get(key).get(10);

    resultVal.add("PASS");

    // Compare Analyte Names
    if (exAnalyteData.containsKey(key)) {
      resultVal.add(String.format("Analyte Name: %s", key));

    } else {
      resultVal.add(String.format("Error. Analyte Name is Wrong %s", key));
      resultVal.set(0, "FAIL");
    }

    // Compare PrbChk1
    if (exPrbChk1.equals(analyteD.get(2)) || exPrbChk1.equals("NA")) {
      resultVal.add(String.format("Actual PrbChk1:   %s \nExpected PrbChk1: %s", analyteD.get(2), exPrbChk1));
    } else {
      resultVal.add(String.format("Error.\nActual PrbChk1:   %s \nExpected PrbChk1: %s", analyteD.get(2), exPrbChk1));
      resultVal.set(0, "FAIL");
    }
    // Compare PrbChk2
    if (exPrbChk2.equals(analyteD.get(3)) || exPrbChk2.equals("NA")) {
      resultVal.add(String.format("Actual PrbChk2:   %s \nExpected PrbChk2: %s", analyteD.get(3), exPrbChk2));
    } else {
      resultVal.add(String.format("Error.\nActual PrbChk2:   %s \nExpected PrbChk2: %s", analyteD.get(3), exPrbChk2));
      resultVal.set(0, "FAIL");
    }
    // Compare PrbChk3
    if (exPrbChk3.equals(analyteD.get(4)) || exPrbChk3.equals("NA")) {
      resultVal.add(String.format("Actual PrbChk3:   %s \nExpected PrbChk3: %s", analyteD.get(4), exPrbChk3));
    } else {
      resultVal.add(String.format("Error.\nActual PrbChk3:   %s \nExpected PrbChk3: %s", analyteD.get(4), exPrbChk3));
      resultVal.set(0, "FAIL");
    }
    // Compare Probe Check Result
    if(exPrbChkResult.equals("-9999.0"))
    {
      resultVal.add(String.format("***\nActual Probe Check Result:  %s \nExpected Probe Check Result: %s", analyteD.get(5), exPrbChkResult)); 
    }
    else if (exPrbChkResult.equals(analyteD.get(5))) {
      resultVal.add(String.format("Actual Probe Check Result:   %s \nExpected Probe Check Result: %s", analyteD.get(5),
          exPrbChkResult));
    } else {
      resultVal.add(String.format("Error. \nActual Probe Check Result:   %s \nExpected Probe Check Result: %s",
          analyteD.get(5), exPrbChkResult));
      resultVal.set(0, "FAIL");
    }
    // Compare 2nd Deriv Peak
    if(exDerivPeak.equals("-9999.0"))
    {
      resultVal.add(String.format("***\nActual 2nd Deriv Peak:   %s \nExpected 2nd Deriv Peak: %s", analyteD.get(6),exDerivPeak));
    }
    else if (exDerivPeak.equals(analyteD.get(6))) {
      resultVal.add(
          String.format("Actual 2nd Deriv Peak:   %s \nExpected 2nd Deriv Peak: %s", analyteD.get(6), exDerivPeak));
    } else {
      resultVal.add(String.format("Error.\nActual 2nd Deriv Peak:   %s \nExpected 2nd Deriv Peak: %s", analyteD.get(6),
          exDerivPeak));
      resultVal.set(0, "FAIL");
    }
    return resultVal;
  }

  /*
   * Verify the text in the disclaimer box of GUI
   */
  public String verifyDisclaimer (String exDisclaimer, String key)
  {
    String verify = verifyBoxDisclaimer();
    String GUIText;
    String resultVal = "";

    // Disclaimer has two unique ID's so check for both
    switch (verify) {
      case "1":
        GUIText = findJTextArea(VR_TEST_RESULT_BOX).getText();
        GUIText = GUIText.trim();
        break;
      case "2":
        GUIText = findJTextArea(VR_TEST_RESULT_BOX2).getText();
        GUIText = GUIText.trim();
        break;
      default:
        GUIText = "Disclaimer Does Not Exist";
        break;
    }

    // Verify text in disclaimer
    if (GUIText.equals(exDisclaimer.trim())) {
      resultVal = String.format("Actual Disclaimer: %s \nExpected Disclaimer: %s", GUIText, exDisclaimer);
    } else {
      if (GUIText.equals("For Research Use Only")) {
        resultVal = String.format("*** \nActual Disclaimer: %s \nExpected Disclaimer: \"\"", GUIText);
      } else {
        resultVal = String.format("Error.\n Actual Disclaimer: %s \nExpected Disclaimer %s", GUIText, exDisclaimer);
        updateFail(key);
      }
    }
    resultVal = resultVal.replace(".", ".\n");
    return resultVal;
  }

  /*
   * Extracts analyte data from the GUI JTable, data values are dependent on which
   * JTab is opened
   */
  private Map<Integer, String> extractAnalyteResults (int row)
  {
    JTable analyteResultsT = findJTable(VR_ANALYTE_RTABLE);
    Map<Integer, String> analyteR = new HashMap<Integer, String>();

    // Get Analyte Name
    analyteR.put(0, analyteResultsT.getCellText(row, 0));

    // Get Ct Number || Get PrbChk1
    analyteR.put(2, analyteResultsT.getCellText(row, 2));

    // Get EndPt || PrbChk2
    analyteR.put(3, analyteResultsT.getCellText(row, 3));

    // Get Interpretation Result || PrbChk3
    analyteR.put(4, analyteResultsT.getCellText(row, 4));

    // Get Reason || Probe Check Result
    analyteR.put(5, analyteResultsT.getCellText(row, 5));

    // Get Analyte Result || 2nd Deriv Peak Height
    analyteR.put(6, analyteResultsT.getCellText(row, 6));

    return analyteR;
  }


  /*
   * Checks for which disclaimer ID is present
   */
  private String verifyBoxDisclaimer ()
  {
    if (exists(VR_TEST_RESULT_BOX)) {
      return "1";
    } else if (exists(VR_TEST_RESULT_BOX2)) {
      return "2";
    } else
      return "-1";
  }
  
  private boolean endPtRange(String actualPt, String expPt)
  {
    int expInt = Integer.parseInt(expPt);
    int value = Integer.parseInt(actualPt);
    int minVal = value-1;
    int maxVal = value+1;
    
    if (expInt >= minVal && expInt <= maxVal)
    {
      return true;
    }
    else
      return false;
  }
  
  private boolean ctRange(String actualCt, String expCt)
  {
    double expDoub = Double.parseDouble(expCt);
    double value = Double.parseDouble(actualCt);
    double minVal = value - 0.1;
    double maxVal = value + 0.1;
    
    if(expDoub >= minVal && expDoub <= maxVal)
    {
      return true;
    }
    else
      return false;
  }

  public void setMap (String key)
  {
    PF.put(key, null);
  }

  public void convertToTrue ()
  {
    for (String key : PF.keySet()) {
      if (PF.get(key) == null)
        PF.put(key, true);
      logInfo(key + PF.get(key) + " ");
    }

  }

  public void mapToHTML ()
  {
    setPF(PF);
  }

  private void updateFail (String key)
  {
    if (PF.get(key) == null) {
      PF.put(key, false);
    }
  }
  public void resetMap()
  {
    PF.clear();
  }

}
