package com.cepheid.dx.automation.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class htmlCreator extends ExcelMethods
{
  String htmlCode = "";
  Map<String, Boolean> P_F;
  
  
  public void html (ArrayList<ArrayList<String>> htmlList, Map<String, ArrayList<String>> htmlAnalyteR,
      Map<String, ArrayList<String>> htmlAnalyteD,Map<String, ArrayList<String>> htmlAnalyteMelt, boolean melt, String document,String folder) throws IOException
  {
    //Create either html file
    String file = path(document,folder); 
    FileWriter fileWriter = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(fileWriter);

    //Create strings
    
    final String finalResult                                                = "Pass / Fail"; 
    final String SampleID                                                   = "Sample ID";
    final String verifySampleID                                             = "Verification Sample ID";
    final String verifyAssayName                                            = "Verification Assay Name";
    final String verifyStatus                                               = "Verification Status";
    final String verifyErrorStatus                                          = "Verification Error Status";
    final String verifyAssayNameR                                           = "Verification Right Assay Name";
    final String verifyTextBox                                              = "Verification Text Result Box";
    final String verifyResultColor                                          = "Verification of Result Color";
    final String verifyDisclaimer                                           = "Verification of Disclaimer";
    final String red                                                        = "FF0000";
    final String yellow                                                     = "FFFF00";
    final String green                                                      = "00FF00";

    final String verifyAnalyteName                                          = "Verification of Analyte Name";
    final String verifyCt                                                   = "Verification of Ct";
    final String verifyEndPt                                                = "Verification of End Pt";
    final String verifyInterp                                               = "Verification of Interpretation Result";
    final String verifyAnalyteReason                                        = "Verification of Analyte Reason";
    final String verifyAnalyteResult                                        = "Verification of Analyte Result";

    final String verifyAnalyteDName                                         = "Verification of Analyte Details Name";
    final String verifyPrbChk1                                              = "Verification of Prb Check 1";
    final String verifyPrbChk2                                              = "Verification of Prb Check 2";
    final String verifyPrbChk3                                              = "Verification of Prb Check 3";
    final String verifyPrbChkResult                                         = "Verification of Probe Check Result"; 
    final String verifyDerivPeak                                            = "Verification of 2nd Deriv Peak";
    
    final String verifyMeltPeak                                             = "Verification of Melt Peak Temperature";
    final String verifyMeltPeakHeight                                       = "Verification of Melt Peak Height"; 

    String htmlStarter                                                      = htmlStarter();
    String htmlEnder                                                        = htmlEnder();
    String header                                                           = htmlHeader(document);
    String tableHeaderCells                                                 = tableHeaderCells(finalResult, SampleID, verifySampleID, verifyAssayName, verifyStatus, verifyErrorStatus, verifyAssayNameR,
                                                                               verifyTextBox,verifyResultColor, verifyDisclaimer);
    String assayHeader                                                      = "<h3><u>Assay Verification</u></h3>\r\n";
    
    String tableWidth                                                       = "<table style=\"width:100%\">\r\n<tr>\r\n";
    String endRow                                                           = "</tr>\r\n";
    String endTable                                                         = "</table>\r\n";
    String beginRow                                                         = "<tr>\r\n";
    String styleTable                                                       = styleTable();
    String styleRows                                                        = styleRows();
    String styleExtra                                                       = styleExtra();
    String errorHeaders                                                     = tableHeaderCells("Sample ID", "Error");
    String analyteResultsHeaders                                            = tableHeaderCells(finalResult,verifyAnalyteName,verifyCt, verifyEndPt, verifyInterp, verifyAnalyteReason, verifyAnalyteResult);
    String analyteDetailHeader                                              = tableHeaderCells(finalResult,verifyAnalyteDName, verifyPrbChk1, verifyPrbChk2,verifyPrbChk3, verifyPrbChkResult, verifyDerivPeak);
    String analyteMeltHeader                                                = tableHeaderCells(finalResult,verifyAnalyteName,verifyMeltPeak,verifyMeltPeakHeight);

    int errorID                                                             = 0;
    String errorTable                                                       = "";
    String errorTableHeader                                                 = "<h3><u>Error Table</u></h3>\r\n";
    String analyteErrorTable                                                = ""; 
    String analyteErrorHeader                                               = "<h3><u>Analyte Error Table</u></h3>\r\n";

    
    //Create the table header of Test Results    
    printWriter.print(htmlStarter);
    printWriter.print(header);
    String beginTable = beginTableCreation(assayHeader,styleTable,styleRows,styleExtra,tableWidth,tableHeaderCells,endRow); 
    errorTable = beginTableCreation(errorTableHeader,styleTable, styleRows,styleExtra,tableWidth,errorHeaders);
    concat(beginTable);

    /* Create cells with individual verification results
     * Iterates through the htmlList for each individual assay
     * 
     * 0 -- Sample ID
     * 1 -- Verify Sample ID
     * 2 -- Verify Assay Name
     * 3 -- Verify Status
     * 4 -- Verify Error Status
     * 5 -- Verify Assay Name R
     * 6 -- Verify Text Box
     * 7 -- Verify Result Color
     * 8 -- Verify Disclaimer
     */
    for (int outerArray = 0; outerArray < htmlList.size(); outerArray++) {
      concat(beginRow); 
      if(P_F.get(htmlList.get(outerArray).get(0)) == true)
      {
        concat(String.format("<td style= \"background-color:#%s\"><pre>%s</pre></td>\r\n", green,"PASS"));
      }
      else
        concat(String.format("<td style= \"background-color:#%s\"><pre>%s</pre></td>\r\n", red, "FAIL"));
      for (int innerArray = 0; innerArray < htmlList.get(outerArray).size(); innerArray++) {
        String cell = htmlList.get(outerArray).get(innerArray);       
        if (cell.contains("Error.")) {
          concat(String.format("<td id=\"Error%s\" style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", errorID, red, cell));
         errorTable = errorTable.concat(createErrorCells(htmlList.get(outerArray).get(0), errorID, innerArray));
          errorID++;
        } 
        else if(cell.contains("***"))
        {
         concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,cell)); 
          
        }
        else if(cell.contains("$$$"))
        {
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", green,cell));
        }
        else
          concat(String.format("<td><pre>%s</pre></td>\r\n", cell));
      }
      concat(endRow);
    }

    concat(endTable);
    
   
    
    printWriter.print(errorTable);
    printWriter.print(endTable);
    
   
    analyteErrorTable = beginTableCreation(analyteErrorHeader, styleTable, styleRows, styleExtra, tableWidth, errorHeaders);
    /*
     * 
     * Begin the data creation for the Analytes of each assay
     * Creates the assay header and table label
     */
   
    concat("<h2 style=\"text-align:center;\">Analyte Data</h2>\r\n");
    for (String val : htmlAnalyteR.keySet()) {
     
      concat(String.format("<h3><u>%s</u></h3>\r\n", val));
     
      concat(String.format("<h4>%s</h4>\r\n", "Analyte Results Verification"));
      beginTable = beginTableCreation(styleTable,styleRows,styleExtra,tableWidth);
     
      concat(beginTable);
    
      concat(analyteResultsHeaders); 
  
      concat("</tr>\r\n<tr>\r\n");

      /*
       * 
       * Iterates through the analyte result map
       * 0 -- Analyte Name
       * 1 -- Ct
       * 2 -- End Pt
       * 3 -- Interpret Result
       * 4 -- Analyte reason
       * 5 -- Analyte Result
       */
      for (int count = 0; count < htmlAnalyteR.get(val).size(); count++) {
        
        //Start a new row once all 6 data points of analyte are checked
        if (count % 7 == 0 && count != 0) {
       
          concat(endRow);
        }
        String aCell = htmlAnalyteR.get(val).get(count);
        if (aCell.contains("Error.")) {
          concat(String.format("<td id =\"Error%s\" style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", errorID, red, aCell));
          analyteErrorTable = analyteErrorTable.concat(createARErrorCells(val,htmlAnalyteR.get(val).get(count-(count%7)+1), errorID, (count % 7)+1));
          errorID++;
        } 
        else if(aCell.contains("***"))
        {
         
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,aCell));
        }
        else if(aCell.equals("PASS"))
        {
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", green,aCell));
        }
        else if(aCell.equals("FAIL"))
        {
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", red,aCell));
        }
        else {
         
          concat(String.format("<td><pre>%s</pre></td>\r\n", aCell));
        }
      }
    
      concat(endRow);
     
      concat(endTable);

      //Create the table for analyte details verification
      
      concat(String.format("<h4>%s</h4>\r\n", "Analyte Details Verification"));
     
      concat(beginTable);
    
      concat(analyteDetailHeader);
    
      concat("</tr>\r\n<tr>\r\n");
      
      /*
       * Iterates through the analyte details map
       * 0 -- Analyte Name
       * 1 -- Prb Chk 1
       * 2 -- Prb Chk 2
       * 3 -- Prb Chk 3
       * 4 -- Probe Check Result
       * 5 -- 2nd Deriv Peak
       * 
       */
      for (int detailNum = 0; detailNum < htmlAnalyteD.get(val).size(); detailNum++) {
        if (detailNum % 7 == 0 && detailNum != 0) {
          
          concat(endRow);
        }
        String aCell = htmlAnalyteD.get(val).get(detailNum);
        if (aCell.contains("Error.")) {
         concat(String.format("<td id=\"Error%s\" style=\"background-color:#%s\"><pre>%s</pre></td>\r\n",errorID, red, aCell));
          analyteErrorTable = analyteErrorTable.concat(createADErrorCells(val,htmlAnalyteD.get(val).get(detailNum-(detailNum%7)+1),errorID,(detailNum % 7)+1));
          errorID++;
        }
        else if(aCell.contains("***"))
        {
       
         concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,aCell));
        }
        else if(aCell.equals("PASS"))
        {
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", green,aCell));
        }
        else if(aCell.equals("FAIL"))
        {
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", red,aCell));
        }
        else
        {
        
          concat(String.format("<td><pre>%s</pre></td>\r\n", aCell));
        }

      }
     
      concat(endRow);
      concat(endTable);
      
      if(melt)
      {
        //Create header for melt
        concat(String.format("<h4>%s</h4>\r\n", "Analyte Melt Verification"));
        concat(beginTable);
        concat(analyteMeltHeader);
        
        concat("</tr>\r\n<tr>\r\n");
        
        /*
         * Iterates through melt peak
         * 0 -- Analyte Name
         * 1 -- Melt Peak
         * 2 -- Melt Peak Height
         */
        
        for(int meltNum = 0; meltNum < htmlAnalyteMelt.get(val).size(); meltNum++)
        {
          if(meltNum % 4 == 0 && meltNum != 0)
          {
            concat(endRow);
          }
          String aCell = htmlAnalyteMelt.get(val).get(meltNum); 
          if(aCell.contains("Error."))
          {
            concat(String.format("<td id=\"Error%s\" style=\"background-color:#%s\"><pre>%s</pre></td>\r\n",errorID, red, aCell));
            analyteErrorTable = analyteErrorTable.concat(createMeltErrorCells(val,htmlAnalyteMelt.get(val).get(meltNum-(meltNum%4)+1),errorID,(meltNum % 4)+1));
            errorID++;
          }
          else if(aCell.equals("PASS"))
          {
            concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", green,aCell));
          }
          else if(aCell.equals("FAIL"))
          {
            concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", red,aCell));
          }
          else
          {
            concat(String.format("<td><pre>%s</pre></td>\r\n", aCell));
          }
        }
        concat(endRow);
        concat(endTable); 
      }
    }

    //Finish the html code
    printWriter.print(analyteErrorTable);
    printWriter.print(endRow);
    printWriter.print(endTable);
    
    printWriter.print(htmlCode);
    
    printWriter.print(htmlEnder);
    printWriter.close();
    fileWriter.close();

  }
  private void createAssayTable()
  {
    
  }
  
  /*
   * The path at which the html file will be saved
   */
  private String path(String document, String folder)
  {
   
    return String.format(
        "C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\TestResultFiles\\%s\\%s.html", folder,
        document);
  }
  /*
   * Beginning code to start the html file
   */
  private String htmlStarter()
  {
    return "<!DOCTYPE html>\r\n<html>\r\n<head>\r\n<title>Verification Results</title>\r\n</head>\r\n<body>\r\n";
  }
  
  /*
   * The code to end the html file
   */
  private String htmlEnder()
  {
    return "</body>\r\n</html>";
  }
  
  /*
   * Put the document name as the main header for the document
   */
  private String htmlHeader(String document)
  {
    return String.format("<h1 style= \"text-align:center;\">%s</h1>\r\n", document);
  }
  
  /*
   * Create header cells based off user input
   */
  private String tableHeaderCells(String ... args)
  {
    String concat = ""; 
    for(String arg: args)
    {
      concat = concat.concat(String.format("<th>%s</th>\r\n", arg));
    }
    return concat;
  }

  /*
   * Styles the overall table
   */
  private String styleTable()
  {
    return "<style>\r\ntable {\r\n border-collapse: collapse; \r\n } \r\n";
  }
  
  /*
   * Sytle the rows with a color
   */
  private String styleRows()
  {
    return "th,td {\r\nborder: 1px solid green; \r\n } \r\n ";
  }
  
  /*
   * Add the gray and white alternating row colors
   */
  private String styleExtra()
  {
    return "tr:nth-child(even) { \r\n background-color: #eee; \r\n } \r\n tr:nth-child(odd) { \r\n background-color: #fff; \r\n } \r\n </style> \r\n";
  }
  
  /*
   * Concats all the preliminary code to begin the table, includes the header cells and styles
   */
  private String beginTableCreation(String...args)
  {
    String concat = "";
    
    for(String arg: args)
    {
      concat = concat.concat(arg);
    }
    
    return concat;
  }
  
  /*
   * Creates the bookmarks for the error cells for the assay table 
   */
  private String createErrorCells(String assay, int error, int colNum)
  {
    String errorCell = ""; 
    String colName = "";
    switch(colNum)
    {
      case 1:
        colName = "Sample ID";
        break;
      case 2:
        colName = "Assay Name";
        break;
      case 3:
        colName = "Status";
        break;
      case 4:
        colName = "Error Status";
        break;
      case 5:
        colName = "Right Assay Name";
        break;
      case 6: 
        colName = "Text Result Box";
        break;
      case 7:
        colName = "Result Color";
        break;
      case 8:
        colName = "Disclaimer"; 
        break;
      default:
        colName = "No Clue"; 
    }
    errorCell = String.format("<tr>\r\n"
        + "<td><pre>%s</pre></td>\r\n"
        + "<td><pre><a href=\"#Error%s\">%s</a></pre></td>\r\n"
        + "</tr>\r\n", assay, error, colName);
    
    return errorCell;
  }
  
  /*
   * Creates the bookmarks for the analyte result tables
   */
  private String createARErrorCells(String assay, String analyte, int error, int colNum)  {
    String errorCell = ""; 
    String colName = "";
    switch(colNum)
    {
      case 2:
        colName = "Analyte Name";
        break;
      case 3:
        colName = "Ct";
        break;
      case 4:
        colName = "End Pt";
        break;
      case 5:
        colName = "Interpretation Result";
        break;
      case 6:
        colName = "Analyte Reason";
        break;
      case 7: 
        colName = "Analyte Result";
        break;
      default:
        colName = "No Clue"; 
    }
    errorCell = String.format("<tr>\r\n"
        + "<td><pre>%s | %s</pre></td>\r\n"
        + "<td><pre><a href=\"#Error%s\">%s</a></pre></td>\r\n"
        + "</tr>\r\n", assay, analyte, error, colName);
    
    return errorCell;
  }
  /*
   * Creates the bookmarks for the analyte details
   */
  private String createADErrorCells(String assay, String analyte,  int error, int colNum)  {
    String errorCell = ""; 
    String colName = "";
    switch(colNum)
    {
      case 2:
        colName = "Analyte Name";
        break;
      case 3:
        colName = "Prb Check 1";
        break;
      case 4:
        colName = "Prb Check 2";
        break;
      case 5:
        colName = "Prb Check 3";
        break;
      case 6:
        colName = "Probe Check Result";
        break;
      case 7: 
        colName = "2nd Deriv Peak";
        break;
      default:
        colName = "No Clue"; 
    }
    errorCell = String.format("<tr>\r\n"
        + "<td><pre>%s | %s</pre></td>\r\n"
        + "<td><pre><a href=\"#Error%s\">%s</a></pre></td>\r\n"
        + "</tr>\r\n", assay, analyte,  error, colName);
    
    return errorCell;
  }
  
  /*
   * Creates the bookmarks for the analyte melt tables
   */
  private String createMeltErrorCells(String assay, String analyte, int error, int colNum)
  {
    String errorCell = "";
    String colName = "";
    
    switch(colNum)
    {
      case 1:
        colName = "Analyte Name";
        break;
      case 2:
        colName = "Melt Peak Temperature";
        break;
      case 3:
        colName = "Melt Peak Height";
        break;
        default:
          colName = "No Clue"; 
    }
    errorCell = String.format("<tr>\r\n"
        + "<td><pre>%s | %s</pre></td>\r\n"
        + "<td><pre><a href=\"#Error%s\">%s</a></pre></td>\r\n"
        + "</tr>\r\n", assay, analyte,  error, colName);
    return errorCell; 
  }
  /*
   * Concats the html code into the string
   */
  private void concat(String code)
  {
    htmlCode = htmlCode.concat(code);
  }
  
  public void setPF(Map<String, Boolean> PF)
  {
    P_F = PF; 
  }
  public void resetHtml()
  {
    htmlCode = "";
  }
  
  
  
  
}
