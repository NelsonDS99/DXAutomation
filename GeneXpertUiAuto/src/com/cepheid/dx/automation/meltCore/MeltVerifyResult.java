package com.cepheid.dx.automation.meltCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import com.cepheid.dx.automation.core.VerifyResultMethods;
import com.microfocus.silktest.jtf.swing.JTable;

public class MeltVerifyResult extends VerifyResultMethods
{

  /*
   * Verifies the analyte result tab, iterates through the excel and analyte
   * tables one at a time
   */
  public ArrayList<String> verifyResultsTab (HSSFSheet sheet)
  {
    int excelRow = 1;
    int jRow = 0;
    Row row = sheet.getRow(excelRow);
    ArrayList<String> htmlAR = new ArrayList<String>();

    while (jRow < findJTable(VR_ANALYTE_RTABLE).getRowCount()) {
      ArrayList<String> resultVal = new ArrayList<String>();
      Map<Integer, String> analyteData = extractAnalyteData(jRow);
      String excelAnalyteName = getExcelAnalyte(row);
      resultVal.add("PASS");

      if (analyteData.get(0).equals(excelAnalyteName)) {
        ArrayList<String> exAnalyteR = getExcelAnalyteResults(row);

        resultVal.add(String.format("Analyte Name: %s", analyteData.get(0)));

        // Compare Ct
        if (exAnalyteR.get(0).equals(analyteData.get(2))) {
          resultVal.add(String.format("Actual Ct:  %s \nExpected Ct:  %s", analyteData.get(2), exAnalyteR.get(0)));
        } else {
          resultVal
              .add(String.format("Error.\nActual Ct:  %s \nExpected Ct:  %s", analyteData.get(2), exAnalyteR.get(0)));
          resultVal.set(0, "FAIL");
        }
        // Compare endPt
        if (exAnalyteR.get(1).equals(analyteData.get(3))) {
          resultVal
              .add(String.format("Actual EndPt:   %s \nExpected EndPt: %s", analyteData.get(3), exAnalyteR.get(1)));
        } else {
          resultVal.add(
              String.format("Error.\nActual EndPt:   %s \nExpected EndPt: %s", analyteData.get(3), exAnalyteR.get(1)));
          resultVal.set(0, "FAIL");
        }
        // Compare Interpretation
        if (exAnalyteR.get(2).equals(analyteData.get(4))) {
          resultVal.add(String.format("Actual Interpretation:   %s \nExpected Interpretation: %s", analyteData.get(4),
              exAnalyteR.get(2)));
        } else {
          resultVal.add(String.format("Error.\nActual Interpretation:   %s \nExpected Interpretation: %s",
              analyteData.get(4), exAnalyteR.get(2)));
          resultVal.set(0, "FAIL");
        }
        // Compare Reason
        if (exAnalyteR.get(3).equals(analyteData.get(5))) {
          resultVal
              .add(String.format("Actual Reason:  %s \nExpected Reason: %s ", analyteData.get(5), exAnalyteR.get(3)));
        } else {
          resultVal.add(String.format("Error.\nActual Reason:  %s \nExpected Reason: %s ", analyteData.get(5),
              exAnalyteR.get(3)));
          resultVal.set(0, "FAIL");
        }
        // Compare Analyte Result
        if (exAnalyteR.get(4).equals(analyteData.get(6))) {
          resultVal.add(String.format("Actual Analyte Result:  %s \nExpected Analyte Result: %s", analyteData.get(6),
              exAnalyteR.get(4)));
        } else {
          String.format("Error.\nActual Analyte Result:  %s \nExpected Analyte Result: %s", analyteData.get(6),
              exAnalyteR.get(4));
          resultVal.set(0, "FAIL");
        }
        htmlAR.addAll(resultVal);
        excelRow++;
        row = sheet.getRow(excelRow);
      }
      jRow++;

    }
    return htmlAR;
  }

  /*
   * Verifies the analyte details tab
   */
  public ArrayList<String> verifyAnalyteResultDetails (HSSFSheet sheet)
  {
    int excelRow = 1;
    int jRow = 0;
    Row row = sheet.getRow(excelRow);
    ArrayList<String> htmlAD = new ArrayList<String>();

    while (jRow < findJTable(VR_ANALYTE_RTABLE).getRowCount()) {
      ArrayList<String> resultVal = new ArrayList<String>();
      Map<Integer, String> analyteData = extractAnalyteData(jRow);
      String excelAnalyteName = getExcelAnalyte(row);
      resultVal.add("PASS");

      if (excelAnalyteName.equals(analyteData.get(0))) {
        ArrayList<String> exAnalyteD = getExcelAnalyteDetails(row);

        resultVal.add(String.format("Analyte Name: %s", analyteData.get(0)));

        // Compare Prb Chk 1
        if (exAnalyteD.get(1).equals(analyteData.get(2))) {
          resultVal
              .add(String.format("Actual PrbChk1:   %s \nExpected PrbChk1: %s", analyteData.get(2), exAnalyteD.get(1)));
        } else {
          resultVal.add(String.format("Error.\nActual PrbChk1:   %s \nExpected PrbChk1: %s", analyteData.get(2),
              exAnalyteD.get(1)));
          resultVal.set(0, "FAIL");
        }
        // Compare Prb Chk 2
        if (exAnalyteD.get(2).equals(analyteData.get(3))) {
          resultVal
              .add(String.format("Actual PrbChk2:   %s \nExpected PrbChk2: %s", analyteData.get(3), exAnalyteD.get(2)));
        } else {
          resultVal.add(String.format("Error.\nActual PrbChk2:   %s \nExpected PrbChk2: %s", analyteData.get(3),
              exAnalyteD.get(2)));
          resultVal.set(0, "FAIL");
        }
        // Compare Prb Chk 3
        if (exAnalyteD.get(3).equals(analyteData.get(4))) {
          resultVal
              .add(String.format("Actual PrbChk3:   %s \nExpected PrbChk3: %s", analyteData.get(4), exAnalyteD.get(3)));
        } else {
          resultVal.add(String.format("Error. \nActual PrbChk3:   %s \nExpected PrbChk3: %s", analyteData.get(4),
              exAnalyteD.get(3)));
          resultVal.set(0, "FAIL");
        }
        // Compare PrbChkResult
        if (exAnalyteD.get(0).equals(analyteData.get(5))) {
          resultVal.add(String.format("Actual PrbChkResult:   %s \nExpected PrbChkResult: %s", analyteData.get(5),
              exAnalyteD.get(0)));
        } else {
          resultVal.add(String.format("Error.\nActual PrbChkResult:   %s \nExpected PrbChkResult: %s",
              analyteData.get(5), exAnalyteD.get(0)));
          resultVal.set(0, "FAIL");
        }
        // Compare 2nd Deriv
        if (exAnalyteD.get(4).equals(analyteData.get(6))) {
          resultVal.add(
              String.format("Actual 2ndDeriv:   %s \nExpected 2ndDeriv: %s", analyteData.get(6), exAnalyteD.get(4)));
        } else {
          resultVal.add(String.format("Error.\nActual 2ndDeriv:   %s \nExpected 2ndDeriv: %s", analyteData.get(6),
              exAnalyteD.get(4)));
          resultVal.set(0, "FAIL");
        }

        htmlAD.addAll(resultVal);
        excelRow++;
        row = sheet.getRow(excelRow);
      }

      jRow++;

    }
    return htmlAD;
  }

  /*
   * Verifies the analyte melt peak tab
   */
  public ArrayList<String> getMeltPeaks (HSSFSheet sheet)
  {
    ArrayList<String> htmlMelt = new ArrayList<String>();
    int excelRow = 1;
    int jRow = 0;
    Row row = sheet.getRow(excelRow);

    while (jRow < findJTable(VR_ANALYTE_RTABLE).getRowCount()) {
      ArrayList<String> resultVal = new ArrayList<String>();
      Map<Integer, String> analyteMeltData = extractAnalyteMeltData(jRow);
      String analyteName = getExcelAnalyte(row);
      resultVal.add("PASS");

      if (analyteName.equals(analyteMeltData.get(0))) {
        ArrayList<String> exAnalyteMelt = getExcelAnalyteMelt(row);

        resultVal.add(String.format("Analyte Name: %s", analyteMeltData.get(0)));

        // Compare Melt Peak Temp
        if (exAnalyteMelt.get(0).equals(analyteMeltData.get(2))) {
          resultVal.add(String.format("Actual Melt Peak Temp:    %s \nExpected Melt Peak Temp:  %s",
              analyteMeltData.get(2), exAnalyteMelt.get(0)));
        } else if ("".equals(analyteMeltData.get(2)) && "NA".equals(exAnalyteMelt.get(0))) {
          resultVal.add(
              String.format("Actual Melt Peak Temp:   %s \nExpected Melt Peak Temp:  %s", analyteMeltData.get(2), ""));
        } else {
          resultVal.add(String.format("Error.\nActual Melt Peak Temp:  %s \nExpected Melt Peak Temp:  %s",
              analyteMeltData.get(2), exAnalyteMelt.get(0)));
          resultVal.set(0, "FAIL");
        }
        // Compare Melt Peak Height
        if (exAnalyteMelt.get(1).equals(analyteMeltData.get(3))) {
          resultVal.add(String.format("Actual Melt Peak Height:   %s \nExpected Melt Peak Height: %s",
              analyteMeltData.get(3), exAnalyteMelt.get(1)));
        } else if ("".equals(analyteMeltData.get(3)) && "NA".equals(exAnalyteMelt.get(1))) {
          resultVal.add(String.format("Actual Melt Peak Height:   %s \nExpected Melt Peak Height: %s",
              analyteMeltData.get(3), ""));
        } else {
          resultVal.add(String.format("Error.\nActual Melt Peak Height:   %s \nExpected Melt Peak Height: %s",
              analyteMeltData.get(3), exAnalyteMelt.get(1)));
          resultVal.set(0, "FAIL");
        }
        htmlMelt.addAll(resultVal);
        excelRow++;
        row = sheet.getRow(excelRow);
      }
      jRow++;
    }
    return htmlMelt;
  }

  /*
   * Get the expected analyte data values that correspond with the analyte results
   * tab
   * 
   */
  private ArrayList<String> getExcelAnalyteResults (Row row)
  {
    ArrayList<String> exAnalyteR = new ArrayList<String>();

    // Get expected Ct
    exAnalyteR.add(verifyNegativeNull(row.getCell(2).toString()));

    // Get expected endPt
    exAnalyteR.add(verifyNegativeNull(row.getCell(3).toString()));

    // Get expected Interpretation Result
    exAnalyteR.add(row.getCell(4).toString());

    // Get expected Reason
    exAnalyteR.add(row.getCell(5).toString());

    // Get expected Analyte Result
    exAnalyteR.add(row.getCell(6).toString());

    return exAnalyteR;

  }

  /*
   * Extracts expected analyte data that corresponds with analyte details tab
   */
  private ArrayList<String> getExcelAnalyteDetails (Row row)
  {
    ArrayList<String> exAnalyteD = new ArrayList<String>();

    // Get Prb Chk Result
    exAnalyteD.add(verifyNegativeNull(row.getCell(7).toString()));

    // Get Prb Chk1
    exAnalyteD.add(format(converter(row.getCell(9).toString())));

    // Get Prb Chk2
    exAnalyteD.add(format(converter(row.getCell(10).toString())));

    // Get Prb Chk3
    exAnalyteD.add(format(converter(row.getCell(11).toString())));

    // Get 2nd Deriv Peak
    exAnalyteD.add(row.getCell(12).toString());

    return exAnalyteD;
  }

  /*
   * Extracts expected analyte data that corresponds with analyte melt tab
   */
  private ArrayList<String> getExcelAnalyteMelt (Row row)
  {
    ArrayList<String> exAnalyteMelt = new ArrayList<String>();

    // Get Melt Peak Temp
    exAnalyteMelt.add(verifyNegativeNull(row.getCell(14).toString()));

    // Get Melt Peak Height
    exAnalyteMelt.add(verifyNegativeNull(row.getCell(15).toString()));

    return exAnalyteMelt;
  }

  /*
   * Gets the expected name of the excel analyte in the current row
   */
  private String getExcelAnalyte (Row row)
  {
    String analyteName = row.getCell(0).toString();
    return analyteName;
  }

  /*
   * Change any -9999 to NA from excel data
   */
  private String verifyNegativeNull (String text)
  {
    if ("-9999".equals(text) || "-9999.0".equals(text)) {
      text = "NA";
    }
    return text;
  }

  /*
   * Extracts specific analyte row from JTable
   */
  private Map<Integer, String> extractAnalyteData (int jRow)
  {
    JTable analyteResultsT = findJTable(VR_ANALYTE_RTABLE);
    Map<Integer, String> analyteData = new HashMap<Integer, String>();

    // Extract Analyte Name
    analyteData.put(0, analyteResultsT.getCellText(jRow, 0));

    // Extract Ct Number || Prb Chk 1
    analyteData.put(2, analyteResultsT.getCellText(jRow, 2));

    // Extract EndPt || Prb Chk2
    analyteData.put(3, analyteResultsT.getCellText(jRow, 3));

    // Extract Interpretation Result || Prb Chk3
    analyteData.put(4, analyteResultsT.getCellText(jRow, 4));

    // Extract Reason || Probe Check Result
    analyteData.put(5, verifyNegativeNull(analyteResultsT.getCellText(jRow, 5)));

    // Extract Analyte Result || 2nd Deriv Peak
    analyteData.put(6, analyteResultsT.getCellText(jRow, 6));

    return analyteData;
  }

  /*
   * Extracts analyte melt data from JTable
   */
  private Map<Integer, String> extractAnalyteMeltData (int jRow)
  {
    JTable analyteMeltTable = findJTable(VR_ANALYTE_RTABLE);
    Map<Integer, String> analyteData = new HashMap<Integer, String>();

    // Get Analyte Name
    analyteData.put(0, analyteMeltTable.getCellText(jRow, 0));

    // Get Melt Peak Temperature
    analyteData.put(2, analyteMeltTable.getCellText(jRow, 2));

    // Get Melt Peak Height
    analyteData.put(3, analyteMeltTable.getCellText(jRow, 3));

    return analyteData;
  }

  /*
   * Converts excel number strings to double for proper comparison
   */
  private double converter (String number)
  {
    if (number != null) {
      try {
        double d;
        d = Double.parseDouble(number);
        return d;
      } catch (NumberFormatException e) {
        logInfo("Cannot turn into a number: " + number);
      }
    }
    // Some of the expected data is NA or a non-number, translates to 0 in the GUI
    return 0;
  }

  /*
   * 
   */
  private String format (double text)
  {
    return String.format("%.0f", text);
  }
}
