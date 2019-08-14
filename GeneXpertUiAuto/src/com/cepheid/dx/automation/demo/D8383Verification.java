package com.cepheid.dx.automation.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.junit.Test;

import com.cepheid.dx.automation.core.VerifyResultMethods;

public class D8383Verification extends VerifyResultMethods
{

  public void ExpectedAnalyteResultsVerification (String folder, String document, String workSheet)
  {

    FileInputStream file = openFileInputStream(String.format("C:\\Silk Data Files\\%s\\%s.xls", folder, document));

    // Get excel workbook && Get Specific Sheet
    HSSFWorkbook workbook = openHSSFWorkbook(file);
    logInfo(workSheet);
    HSSFSheet sheet = openHSSFSheet(workbook, workSheet);

    // View Test Panel
    navigateToViewResults();

    Map<String, ArrayList<String>> expectedData = createStaticMapValues(sheet);

    Map<String, Integer> sampleIDList = actualResultsList(expectedData);

    ArrayList<ArrayList<String>> htmlData = new ArrayList<ArrayList<String>>();
    ArrayList<String> htmlValue;

    Map<String, ArrayList<String>> htmlAnalyteR = new HashMap<String, ArrayList<String>>();
    ArrayList<String> htmlARValue;

    Map<String, ArrayList<String>> htmlAnalyteD = new HashMap<String, ArrayList<String>>();
    ArrayList<String> htmlADValue;

    int count = 0;

    for (String key : sampleIDList.keySet()) {

      logInfo(key);
      setMap(key);
      // Add key to htmlData
      htmlValue = new ArrayList<String>();
      htmlValue.add(key);
      // Analyte Results Storage
      htmlARValue = new ArrayList<String>();
      // Analyte Detail Storage
      htmlADValue = new ArrayList<String>();

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

      // Create map of Analyte Data for each assay
      Map<String, ArrayList<String>> analyteDataMap = exAnalyteDataMap(analyteSheet);

      logInfo(Arrays.toString(analyteDataMap.keySet().toArray()));
      int rowNum = 0;

      clickTabbedPane(VR_TABBED_PANE, "Analyte Result");

      // Check the analyte results tab
      while (rowNum < analyteDataMap.keySet().size()) {

        if (rowNum < findJTable(VR_ANALYTE_RTABLE).getRowCount()) {
          htmlARValue.addAll(verifyAnalyteResults(analyteDataMap, rowNum));
        }
        rowNum++;
      }

      rowNum = 0;
      clickTabbedPane(VR_TABBED_PANE, "Detail");
      while (rowNum < analyteDataMap.keySet().size()) {

        htmlADValue.addAll(verifyAnalyteDetails(analyteDataMap, rowNum));

        rowNum++;
      }

      count++;

      // Add to htmlArrayList
      htmlData.add(htmlValue);
      htmlAnalyteR.put(key, htmlARValue);
      htmlAnalyteD.put(key, htmlADValue);

      logInfo("====================");
    }

    convertToTrue();
    mapToHTML();

    try {
      document = String.format("%s-%s", document, workSheet);
      html(htmlData, htmlAnalyteR, htmlAnalyteD, null, false, document, folder);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logInfo(String.format("I analyzed %s test results", count));
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsBkgndSub_2Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsBkgndSub", "2Analytes");
    resetMap();
    resetHtml();
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsBkgndSub_3Analytes()
  {
  ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsBkgndSub", "3Analytes");
  resetMap();
  resetHtml();
}
  @Test
  public void D8383_ExpectedAnalyteResultsBkgndSub_4Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsBkgndSub", "4Analytes");
    resetMap();
    resetHtml();
  }
  
  @Test 
  public void D8383_ExpectedAnalyteResultsBkgndSub_6Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsBkgndSub", "6Analytes");
    resetMap();
    resetHtml();
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsBkgndValidation1_Tests()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsBkgndValidation1", "Tests");
    resetMap();
    resetHtml();
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsCombinatorial_3Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsCombinatorial", "3Analytes");
    resetMap();
    resetHtml();
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsCombinatorial_6Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsCombinatorial", "6Analytes");
    resetMap();
    resetHtml();
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsCrosstalk_3Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsCrosstalk", "3Analytes");
    resetMap();
    resetHtml();
  }
  
  @Test
  public void D8383_ExpectedAnalyteResultsCrosstalk_4Analytes()
  {
    ExpectedAnalyteResultsVerification("D8383 Background Subtraction and Crosstalk Correction", "ExpectedAnalyteResultsCrosstalk", "6Analytes");
    resetMap();
    resetHtml();
  }
}