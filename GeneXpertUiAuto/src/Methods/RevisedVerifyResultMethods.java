package Methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.microfocus.silktest.jtf.swing.JTable;

import core.ExcelMethods;

public class RevisedVerifyResultMethods extends ExcelMethods
{
  /*
   * Compares SampleID of expectedData, finds similarities in JTable and maps
   * locations
   */
  public Map<String, Integer> actualResultsList (Map<String, ArrayList<String>> expectedData)
  {
    findJButton(VIEW_TEST_BTN).click();
    
    //Create keys and locations map
    Set<String> keys;
    Map<String, Integer> actualData = new HashMap<String, Integer>();
    final int numRows = findJTable(TEST_JTABLE).getRowCount();

    //Iterate through expected map, if JTable contains assay map the location
    for (int count = 0; count < numRows; count++) {
      keys = expectedData.keySet();
      String actualSampleID = findJTable(TEST_JTABLE).getCellText(count, 1);
      if (keys.contains(actualSampleID)) {
        actualData.put(actualSampleID, count);
      }
    }

    findJButton(TEST_CANCEL_BTN).click();
    
    //Return JTable
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

   sleep(10);
    if (exists(VR_STATUS_BOX)) {
      leftStatus = findJTextField(VR_STATUS_BOX).getText();
    } else
        leftStatus = "Does Not Exist";
    String exAssayName = expectedData.get(key).get(2);
    String exLeftStatus = expectedData.get(key).get(0);

    //Compare Sample ID
    if (expectedData.containsKey(leftSampleID)) {
      returnVal.add(String.format("Actual ID:   %s \nExpected ID: %s", leftSampleID, key));
    } else
      returnVal.add(String.format("Error. Actual ID:   %s \n Expected ID:   %s", leftSampleID, key));
    
    //Compare Assay Name
    if (exAssayName.equals(leftAssayName)) {
      returnVal.add(String.format("Actual Assay Name:   %s \nExpected Assay Name: %s", leftAssayName, exAssayName));
    } else
      returnVal
          .add(String.format("Error. \nActual Assay Name    %s \nExpected Assay Name: %s", leftAssayName, exAssayName));

    //Compare Status
    if (exLeftStatus.equals(leftStatus)) {
      returnVal.add(String.format("Actual Status:   %s \nExpected Status: %s", leftStatus, exLeftStatus));
    } else
      returnVal.add(String.format("Error. \nActual Status:   %s \nExpected Status: %s ", leftStatus, exLeftStatus));

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
    } else
      resultVal = String.format("Error.\nActual Assay Name:   %s \nExpected Assay Name: %s", assayName, exAssayName);

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
        resultVal = resultVal.concat(String.format("Actual Text:   %s \nExpected Text: %s\r\n", iterator, resultText.get(0)));
      } else
        resultVal = resultVal
            .concat(String.format("Error.\n Actual Text:   %s \nExpected Text: %s\r\n", iterator, resultText.get(0)));

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
      
      //Create unique row information
      String rowGUI = String.format("tr class=\"row%s\"", resultPos.get(rowNum) - 1);
      String rowInfo = String.format("tr.row%s { background-color: #%s; color: #%s }", resultPos.get(rowNum) - 1,
          resultBGColor.get(rowNum), resultFontColor.get(rowNum));
      
      //Check if the correct row exists
      if (formattedText.indexOf(rowGUI) != -1) {
        
        //Check if row information is correct
        if (formattedText.indexOf(rowInfo) != -1) {
          resultVal = resultVal.concat(String.format("Actual BC: %s", rowInfo));
        } else
          resultVal = resultVal.concat(String.format("Error.\nActual BC: %s", rowInfo));

      } else
        resultVal = resultVal.concat(String.format("Error. \nActual Row: %s", rowGUI));

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

    //Verify the error status
    if (exErrorStatus.equals(errorStatus)) {
      returnVal.add(String.format("Actual Error Status:   %s \nExpected Error Status: %s", errorStatus, exErrorStatus));
    } else
      returnVal.add(
          String.format("Error. \nActual Error Status:   %s \nExpected Error Status: %s", errorStatus, exErrorStatus));

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
    String exInterpretation = exAnalyteData.get(key).get(2);
    String exReason = exAnalyteData.get(key).get(3);
    String exAnalyteResult = exAnalyteData.get(key).get(4);

    // Compare AnalyteNames
    if (exAnalyteData.containsKey(key)) {
      resultVal.add(String.format("Analyte Names are correct: %s", analyteR.get(0)));
    } else
      resultVal.add(String.format("Analyte Names are Incorrect: %s", analyteR.get(0)));

    // Compare Ct
    if (exCt.equals(analyteR.get(2))) {
      resultVal.add(String.format("Actual Ct:   %s \nExpected Ct: %s", analyteR.get(2), exCt));
    } else
      resultVal.add(String.format("Error.\nActual Ct:   %s \nExpected Ct: %s", analyteR.get(2), exCt));

    // Compare EndPt
    if (exEndPt.equals(analyteR.get(3))) {
      resultVal.add(String.format("Actual EndPt:   %s \nExpected EndPt: %s", analyteR.get(3), exEndPt));
    } else
      resultVal.add(String.format("Error.\nActual EndPt:   %s \nExpected EndPt: %s", analyteR.get(3), exEndPt));

    // Compare Interpretation Result
    if (exInterpretation.equals(analyteR.get(4))) {
      resultVal.add(String.format("Actual Interpretation Result:   %s \nExpected Interpretation Result: %s ",
          analyteR.get(4), exInterpretation));
    } else
      resultVal.add(String.format("Error.\nActual Interpretation Result:   %s \nExpected Interpretation Result: %s ",
          analyteR.get(4), exInterpretation));

    // Compare reason
   if (exReason.equals(analyteR.get(5))) {
        resultVal.add(String.format("Actual Reason:   %s \nExpected Reason: %s ", analyteR.get(5), exReason));
      } 
   else if (exReason.equals("NA"))
      {
        if(analyteR.get(5).equals(""))
        {
          resultVal.add(String.format("*** \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), exReason));
        }
        else
          resultVal.add(String.format("Error. \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5),exReason));
      }
   else if(exReason.equals("No Ct"))
   {
     if(analyteR.get(5).equals(""))
     {
       resultVal.add(String.format("*** \nActual Reason   %s \nExpected Reason: %s ", analyteR.get(5), exReason));
     }
     else
       resultVal.add(String.format("Error. \nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5),exReason));
   }
   else
     resultVal.add(String.format("Error.\nActual Reason:   %s \nExpected Reason: %s", analyteR.get(5), exReason));
       

    // Compare Analyte Result
    if (exAnalyteResult.equals(analyteR.get(6))) {
      resultVal.add(
          String.format("Actual Analyte Result:   %s \nExpected Analyte Reason: %s", analyteR.get(6), exAnalyteResult));

    } else
      resultVal.add(String.format("Error.\nActual Analyte Result:  %s \nExpected Analyte Reason %s", analyteR.get(6),
          exAnalyteResult));

    return resultVal;
  }

  /*
   * Verifies the Analyte Details Tab of the GUI
   */
  public ArrayList<String> verifyAnalyteDetails (Map<String, ArrayList<String>> exAnalyteData, int rowNum)
  {
    clickTabbedPane(VR_TABBED_PANE, "Detail");

    Map<Integer, String> analyteD = extractAnalyteResults(rowNum);
    ArrayList<String> resultVal = new ArrayList<String>();
    String key = analyteD.get(0);
    String exPrbChk1 = exAnalyteData.get(key).get(7);
    String exPrbChk2 = exAnalyteData.get(key).get(8);
    String exPrbChk3 = exAnalyteData.get(key).get(9);
    String exPrbChkResult = exAnalyteData.get(key).get(5);
    String exDerivPeak = exAnalyteData.get(key).get(10);

    exPrbChkResult = verifyPCR(exPrbChkResult);
    exDerivPeak = verify2ndDeriv(exDerivPeak);

    // Compare Analyte Names
    if (exAnalyteData.containsKey(key)) {
      resultVal.add(String.format("Analyte Name is Correct: %s", key));

    } else
      resultVal.add(String.format("Error. Analyte Name is Wrong %s", key));

    // Compare PrbChk1
    if (exPrbChk1.equals(analyteD.get(2)) || exPrbChk1.equals("NA")) {
      resultVal.add(String.format("Actual PrbChk1:   %s \nExpected PrbChk1: %s", analyteD.get(2), exPrbChk1));
    } else
      resultVal.add(String.format("Error.\nActual PrbChk1:   %s \nExpected PrbChk1: %s", analyteD.get(2), exPrbChk1));

    // Compare PrbChk2
    if (exPrbChk2.equals(analyteD.get(3)) || exPrbChk2.equals("NA")) {
      resultVal.add(String.format("Actual PrbChk2:   %s \nExpected PrbChk2: %s", analyteD.get(3), exPrbChk2));
    } else
      resultVal.add(String.format("Error.\nActual PrbChk2:   %s \nExpected PrbChk2: %s", analyteD.get(3), exPrbChk2));

    // Compare PrbChk3
    if (exPrbChk3.equals(analyteD.get(4)) || exPrbChk3.equals("NA")) {
      resultVal.add(String.format("Actual PrbChk3:   %s \nExpected PrbChk3: %s", analyteD.get(4), exPrbChk3));
    } else
      resultVal.add(String.format("Error.\nActual PrbChk3:   %s \nExpected PrbChk3: %s", analyteD.get(4), exPrbChk3));

    // Compare Probe Check Result
    if (exPrbChkResult.equals(analyteD.get(5))) {
      resultVal.add(String.format("Actual Probe Check Result:   %s \nExpected Probe Check Result: %s", analyteD.get(5),
          exPrbChkResult));
    } else
      resultVal.add(String.format("Error. \nActual Probe Check Result:   %s \nExpected Probe Check Result: %s",
          analyteD.get(5), exPrbChkResult));

    // Compare 2nd Deriv Peak
    if (exDerivPeak.equals(analyteD.get(6))) {
      resultVal
          .add(String.format("Actual 2nd Deriv Peak:   %s \nExpected 2nd Deriv Peak: %s", analyteD.get(6), exDerivPeak));
    } else
      resultVal.add(String.format("Error.\nActual 2nd Deriv Peak:   %s \nExpected 2nd Deriv Peak: %s", analyteD.get(6),
          exDerivPeak));

    return resultVal;
  }

  /*
   * Verify the text in the disclaimer box of GUI
   */
  public String verifyDisclaimer (String exDisclaimer)
  {
    String verify = verifyBoxDisclaimer();
    String GUIText;
    String resultVal = "";

    //Disclaimer has two unique ID's so check for both
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
    
    //Verify text in disclaimer
    if (GUIText.equals(exDisclaimer.trim())) {
      resultVal = String.format("Actual Disclaimer: %s \nExpected Disclaimer: %s", GUIText, exDisclaimer);
    } else
    {
      if(GUIText.equals("For Research Use Only"))
      {
        resultVal = String.format("Actual Disclaimer: %s \nExpected Disclaimer: \"\"", GUIText);
      }
      else
        resultVal = String.format("Error.\n Actual Disclaimer: %s \nExpected Disclaimer %s", GUIText, exDisclaimer);
    }
     resultVal = resultVal.replace(".", ".\n");
    return resultVal;
  }

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
   * Converts -9999.9 as NA
   */
  private String verifyPCR (String data)
  {
    if (data.equals("-9999.0")) {
      data = "NA";
    }
    return data;
  }

  /*
   * Converts -9999.9 as 0.0
   */
  private String verify2ndDeriv (String data)
  {
    if (data.equals("-9999.0")) {
      data = "0.0";
    }
    return data;
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
}
