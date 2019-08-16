package com.cepheid.dx.automation.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.cepheid.dx.automation.meltCore.MeltVerifyResult;

public class MeltVerification extends MeltVerifyResult
{

  public void D19170_MeltVerification ()
  {
    String folder = "D19170 Melt";
    String document = "D19170 Melt Peak Verification";
    FileInputStream file = openFileInputStream(String.format("Z:\\swq\\private\\Nelson\\DX Automation\\Silk Data Files\\%s\\%s.xls", folder, document));

    // Get excel workbook && Get Specific Sheet
    HSSFWorkbook workbook = openHSSFWorkbook(file);
    String workSheet = "All Tests";
    HSSFSheet sheet = openHSSFSheet(workbook, workSheet);

    // View Test Panel
    navigateToViewResults();

    // Obtain static values from excel for each test case
    Map<String, ArrayList<String>> expectedData = createStaticMapValues(sheet);

    // Obtain matching test cases and their row number
    Map<String, Integer> sampleIDList = actualResultsList(expectedData);

    //Record all the result data to be used in the htmlCreator class
    ArrayList<ArrayList<String>> htmlData = new ArrayList<ArrayList<String>>();
    ArrayList<String> htmlValue;

    Map<String, ArrayList<String>> htmlAnalyteR = new HashMap<String, ArrayList<String>>();
    ArrayList<String> htmlARValue;

    Map<String, ArrayList<String>> htmlAnalyteD = new HashMap<String, ArrayList<String>>();
    ArrayList<String> htmlADValue;

    Map<String, ArrayList<String>> htmlAnalyteMelt = new HashMap<String, ArrayList<String>>();
    ArrayList<String> htmlMeltValue;

    int count = 0;

    for (String key : sampleIDList.keySet()) {

      logInfo(key);
      setMap(key);
      // Add key to htmlData
      htmlValue = new ArrayList<String>();
      htmlValue.add(key);
      // Analyte Results Storage
      htmlARValue = new ArrayList<String>();

      htmlADValue = new ArrayList<String>();

      htmlMeltValue = new ArrayList<String>();

      // Click Specific Assay in JTable
      clickResult(sampleIDList.get(key));

      // Check left GUI panel
      htmlValue.addAll(verifyGuiLeftPanel(expectedData, key));

      // Check error status text in support tab
      htmlValue.addAll(verifyErrorStatus(expectedData, key));

      // Check right assay name in GUI
      htmlValue.add(verifyRightAssayName(expectedData, key));

      ArrayList<String> resultText = resultText(sheet, key);

      // Check the text in the textbox of GUI
      htmlValue.add(verifyTestResultBox(expectedData, key, resultText));

      ArrayList<String> resultBGColor = resultBGColor(sheet, key);

      ArrayList<String> resultFontColor = resultFontColor(sheet, key);

      ArrayList<Integer> resultPos = resultPos(sheet, key);

      // Check the background color and font color of textbox
      htmlValue.add(verifyColor(expectedData, key, resultBGColor, resultFontColor, resultPos));

      // Check the disclaimer box
      String resultDisclaimer = resultDisclaimer(sheet, key);
      htmlValue.add(verifyDisclaimer(resultDisclaimer, key));

      HSSFSheet analyteSheet = openHSSFSheet(workbook, key);

      clickTabbedPane(VR_TABBED_PANE, "Analyte Result");

      // Check the analyte results tab
      htmlARValue = verifyResultsTab(analyteSheet);

      // Check the analyte details tab
      clickTabbedPane(VR_TABBED_PANE, "Detail");
      htmlADValue = verifyAnalyteResultDetails(analyteSheet);

      // Check the analyte Melt Peak tab
      clickTabbedPane(VR_TABBED_PANE, "Melt Peaks");
      htmlMeltValue = getMeltPeaks(analyteSheet);

      // Add to htmlArrayList
      htmlData.add(htmlValue);

      htmlAnalyteR.put(key, htmlARValue);
      htmlAnalyteD.put(key, htmlADValue);
      htmlAnalyteMelt.put(key, htmlMeltValue);

      count++;
      logInfo("====================");
    }
    
    convertToTrue();
    mapToHTML();
    try {
      document = String.format("%s-%s", document, workSheet);
      html(htmlData, htmlAnalyteR, htmlAnalyteD, htmlAnalyteMelt, true, document,folder);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logInfo(String.format("I analyzed %s test results", count));
  }
  
  @Test
  public void MeltTestVerification()
  {
    D19170_MeltVerification();
  }


}
