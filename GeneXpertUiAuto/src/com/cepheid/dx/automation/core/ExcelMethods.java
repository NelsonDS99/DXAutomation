package com.cepheid.dx.automation.core;

import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileInputStream;

public class ExcelMethods extends ApplicationFunctions
{
  /*
   * Open file
   */
  public FileInputStream openFileInputStream (String location)
  {
    try {
      FileInputStream file = new FileInputStream(new File(location));
      return file;
    } catch (Exception e) {
      logError("Could not create file path");
      return null;
    }
  }

  /*
   * 
   * Open specified workbook
   */
  public HSSFWorkbook openHSSFWorkbook (FileInputStream locator)
  {
    try {
      HSSFWorkbook workbook = new HSSFWorkbook(locator);
      return workbook;
    } catch (Exception e) {
      logError("Could not open excel document");
      return null;
    }
  }

  /*
   * 
   * Open specified sheet of Excel File
   */
  public HSSFSheet openHSSFSheet (HSSFWorkbook workbook, String locator)
  {
    // Open sheet at with given name
    try {
      HSSFSheet sheet = workbook.getSheet(locator);
      return sheet;
    } catch (Exception e) {
      logError("Could not open the sheet");
      return null;
    }
  }

  /*
   * Create static excel map, includes status, error status, assay name
   */
  public Map<String, ArrayList<String>> createStaticMapValues (HSSFSheet sheet)
  {
    int rowNum = 1;
    int colStatic = 0;
    Row row = sheet.getRow(rowNum);
    ArrayList<String> expectedSampleID = new ArrayList<String>();
    ArrayList<String> staticList = new ArrayList<String>();
    Map<String, ArrayList<String>> expectedData = new HashMap<String, ArrayList<String>>();

    while (isRowEmpty(sheet, rowNum) == false && checkIfCellIsEmpty(row, 0) == false) {
      if (!expectedSampleID.contains(row.getCell(0).toString())) {
        staticList = new ArrayList<String>();
        expectedSampleID.add(row.getCell(0).toString());
        staticList.add(row.getCell(1).toString());
        staticList.add(row.getCell(2).toString());
        staticList.add(row.getCell(3).toString());
        expectedData.put(expectedSampleID.get(colStatic), staticList);
        colStatic++;
      }

      rowNum++;
      row = sheet.getRow(rowNum);
    }

    for (Entry<String, ArrayList<String>> entrySet : expectedData.entrySet()) {
      logInfo(entrySet.getKey() + " " + entrySet.getValue());
    }
    return expectedData;

  }

  /*
   * Map the expected analyte data map from the excel sheet
   */
  public Map<String, ArrayList<String>> exAnalyteDataMap (HSSFSheet sheet)
  {

    Row row = sheet.getRow(1);
    ArrayList<String> analyteDetail = new ArrayList<String>();
    Map<String, ArrayList<String>> analyteDataMap = new HashMap<String, ArrayList<String>>();
    boolean verify = true;

    while (row != null && verify && isRowEmpty(row) == false) {

      if (row.getRowNum() != 0 && row != null) {
        for (int colNum = 2; colNum < 13; colNum++) {

          if (colNum == 3 || colNum == 9 || colNum == 10 || colNum == 11) {
            String convert = row.getCell(colNum).toString();
            analyteDetail.add(String.format("%.0f", converter(convert)));
          } else if (colNum == 2) {
            analyteDetail.add(String.format("%.1f", converter(row.getCell(colNum).toString())));
          } else
            analyteDetail.add(row.getCell(colNum).toString());
        }
        analyteDataMap.put(row.getCell(0).toString(), analyteDetail);
        analyteDetail = new ArrayList<String>();

      } else
        verify = false;

      row = sheet.getRow(row.getRowNum() + 1);
    }

    return analyteDataMap;

  }

  /*
   * Extracts the Result Text from the excel
   */
  public ArrayList<String> resultText (HSSFSheet sheet, String key)
  {
    int start = getFirstCell(sheet, key);
    ArrayList<String> resultText = new ArrayList<String>();

    logInfo(start +" " + key);
    Row row = sheet.getRow(start);
    logInfo(row.getCell(0).toString());
    while (isRowEmpty(sheet, start) == false  && checkIfCellIsEmpty(row,0) == false && key.equals(row.getCell(0).toString())) {
      String cell = row.getCell(4).toString();
      if(cell.isEmpty() == false && ";".equals(cell.substring(cell.length()-1)))
      {
        cell = cell.substring(0,cell.length()-1); 
      }
      resultText.add(cell);
      start++;
      row = sheet.getRow(start);
    }

    return resultText;
  }

  /*
   * Extracts the expected Background Color from the excel
   */
  public ArrayList<String> resultBGColor (HSSFSheet sheet, String key)
  {
    int start = getFirstCell(sheet, key);

    ArrayList<String> resultBGColor = new ArrayList<String>();

    Row row = sheet.getRow(start);

    while (isRowEmpty(sheet, start) == false && checkIfCellIsEmpty(row,0) == false && key.equals(row.getCell(0).toString())) {
      resultBGColor.add(row.getCell(5).toString());
      start++;
      row = sheet.getRow(start);
    }
    return resultBGColor;
  }

  /*
   * Extracts the Font Color of the excel
   */
  public ArrayList<String> resultFontColor (HSSFSheet sheet, String key)
  {
    int start = getFirstCell(sheet, key);

    ArrayList<String> resultFontColor = new ArrayList<String>();
    Row row = sheet.getRow(start);

    while (isRowEmpty(sheet, start) == false && checkIfCellIsEmpty(row,0) == false && key.equals(row.getCell(0).toString())) {
      resultFontColor.add(row.getCell(6).toString());
      start++;
      row = sheet.getRow(start);
    }
    return resultFontColor;
  }

  /*
   * Extracts the Position from the excel sheet
   */
  public ArrayList<Integer> resultPos (HSSFSheet sheet, String key)
  {
    int start = getFirstCell(sheet, key);

    ArrayList<Integer> resultPos = new ArrayList<Integer>();
    Row row = sheet.getRow(start);

    while (isRowEmpty(sheet, start) == false && checkIfCellIsEmpty(row,0) == false && key.equals(row.getCell(0).toString())) {
      resultPos.add((int) converter(row.getCell(7).toString()));
      start++;
      row = sheet.getRow(start);
    }
    return resultPos;
  }

  /*
   * Extracts the disclaimers from the excel sheet
   */
  public String resultDisclaimer (HSSFSheet sheet, String key)
  {
    String exText = "";
    int iterator = 8;
    int start = getFirstCell(sheet, key);
    Row row = sheet.getRow(start);

    while (checkIfCellIsEmpty(row, iterator) == false && !"NA".equals(row.getCell(iterator).toString())) {
      exText = exText.concat(String.format("%s ", row.getCell(iterator).toString()));
      iterator++;
    }
    return exText;
  }

  /*
   * Checks if the cell is empty
   */
  public boolean checkIfCellIsEmpty (Row row, int colNum)
  {
    Cell cell = row.getCell(colNum);

    if (cell == null || cell.getCellType() == CellType.BLANK || cell.getCellType() == CellType._NONE
        || isTheCellAnEmptyString(cell)) {
      return true;
    }
    return false;
  }

  /*
   * Checks to see if the cell is of type string but is still empty
   */
  public boolean isTheCellAnEmptyString (Cell cell)
  {
    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
      return true;
    }
    return false;
  }

  /*
   * 
   * Check if a row is empty
   */
  private boolean isRowEmpty (HSSFSheet sheet, int rowIndex)
  {

    logInfo(sheet.getLastRowNum() + " ");
    if (sheet.getLastRowNum() == rowIndex-1) {

      return true;
    }
    return false;
  }
  
  

  /*
   * Check if the row is empty
   */
  private static boolean isRowEmpty (Row row)
  {
    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
      Cell cell = row.getCell(c);
      if (cell != null && cell.getCellType() != CellType.BLANK)
        return false;
    }
    return true;
  }


  /*
   * Check the first cell of the assay name
   */
  private int getFirstCell (HSSFSheet sheet, String key)
  {
    Iterator<Row> rowIterator = sheet.iterator();
    boolean verify = false;

    while (rowIterator.hasNext() && verify == false) {
      Row row = rowIterator.next();
      Cell cell = row.getCell(0);
      if (cell.toString().equals(key)) {
        verify = true;
        return row.getRowNum();
      }

    }
    return -1;
  }

  /*
   * Convert a string into a double type
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

}
