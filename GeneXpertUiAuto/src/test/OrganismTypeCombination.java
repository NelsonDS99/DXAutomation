package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.junit.Test;
import Methods.htmlCreator;

public class OrganismTypeCombination extends htmlCreator
{
  @Test
  public void OrganismTypeCombinationVerification ()
  {
    String document = "D6781 Organism Type Combination Test Results Verification";
    FileInputStream file = openFileInputStream(String.format(
        "C:\\Silk Data Files\\D6781 Silk Data Files\\%s.xls",document));

    // Get excel workbook && Get Specific Sheet
    HSSFWorkbook workbook = openHSSFWorkbook(file);
    HSSFSheet sheet = openHSSFSheet(workbook, "AllTestResults");

    // Login to Cepheid
   // userLogin();
    // View Test Panel
    navigateToViewResults();

    Map<String, ArrayList<String>> expectedData = createStaticMapValues(sheet);

    Map<String, Integer> sampleIDList = actualResultsList(expectedData);
    
    ArrayList<ArrayList<String>> htmlData = new ArrayList<ArrayList<String>>();
    ArrayList<String> htmlValue; 
    
    Map<String, ArrayList<String>> htmlAnalyteR = new HashMap<String,ArrayList<String>>();
    ArrayList<String> htmlARValue;
    
    Map<String,ArrayList<String>>  htmlAnalyteD = new HashMap<String,ArrayList<String>>(); 
    ArrayList<String> htmlADValue;

    int count = 0;

    for (String key : sampleIDList.keySet()) {

      //Add key to htmlData
      htmlValue = new ArrayList<String>();
      htmlValue.add(key); 
      //Analyte Results Storage
      htmlARValue = new ArrayList<String>(); 
      
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
      
      //Check the disclaimer box
      String resultDisclaimer = resultDisclaimer(sheet, key); 
      htmlValue.add(verifyDisclaimer(resultDisclaimer)); 

      HSSFSheet analyteSheet = openHSSFSheet(workbook, key);

      // Create map of Analyte Data for each assay
      Map<String, ArrayList<String>> analyteDataMap = exAnalyteDataMap(analyteSheet);
       
      logInfo(Arrays.toString(analyteDataMap.keySet().toArray()));
      int rowNum = 0;

      clickTabbedPane(VR_TABBED_PANE, "Analyte Result");

      // Check the analyte results tab
      for (String analyte : analyteDataMap.keySet()) {
       
        if (rowNum < findJTable(VR_ANALYTE_RTABLE).getRowCount()) {
          htmlARValue.addAll(verifyAnalyteResults(analyteDataMap, rowNum));
        }
        rowNum++;
      }

      // Check the analyte details tab
      if (key.equals("ORG-12.A4") || key.equals("ORG-12.A3")) {
        rowNum = 1;
      } else
        rowNum = 0;

      clickTabbedPane(VR_TABBED_PANE, "Detail");
      for (String analyte : analyteDataMap.keySet()) {
        
        htmlADValue.addAll(verifyAnalyteDetails(analyteDataMap, rowNum));

        rowNum++;
      }

      count++;
      
      //Add to htmlArrayList
     htmlData.add(htmlValue);
     htmlAnalyteR.put(key, htmlARValue); 
     htmlAnalyteD.put(key, htmlADValue); 

      logInfo("====================");
    }
    try {
      html(htmlData,document, htmlAnalyteR,htmlAnalyteD);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logInfo(String.format("I analyzed %s test results", count));
  }
 

}
