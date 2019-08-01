package Methods;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class htmlCreator extends RevisedVerifyResultMethods
{
  String htmlCode = "";

  public void html (ArrayList<ArrayList<String>> htmlList, String document, Map<String, ArrayList<String>> htmlAnalyte,
      Map<String, ArrayList<String>> htmlAnalyteD) throws IOException
  {
    //Create either html file or txt file
    String file = path(document); 
    FileWriter fileWriter = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(fileWriter);

    //Create strings
    
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

    String htmlStarter                                                      = htmlStarter();
    String htmlEnder                                                        = htmlEnder();
    String header                                                           = htmlHeader(document);
    String tableHeaderCells                                                 = tableHeaderCells(SampleID, verifySampleID, verifyAssayName, verifyStatus, verifyErrorStatus, verifyAssayNameR,
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
    String analyteResultsHeaders                                            = tableHeaderCells(verifyAnalyteName,verifyCt, verifyEndPt, verifyInterp, verifyAnalyteReason, verifyAnalyteResult);
    String analyteDetailHeader                                              = tableHeaderCells(verifyAnalyteDName, verifyPrbChk1, verifyPrbChk2,verifyPrbChk3, verifyPrbChkResult, verifyDerivPeak);

    int errorID = 0;
    String errorTable = "";
    String errorTableHeader                                                      = "<h3><u>Error Table</u></h3>\r\n";
    String analyteErrorTable                                                     = ""; 
    String analyteErrorHeader                                                    = "<h3><u>Analyte Error Table</u></h3>\r\n";

    
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
    for (String val : htmlAnalyte.keySet()) {
     
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
      for (int count = 0; count < htmlAnalyte.get(val).size(); count++) {
        
        //Start a new row once all 6 data points of analyte are checked
        if (count % 6 == 0 && count != 0) {
       
          concat(endRow);
        }
        String aCell = htmlAnalyte.get(val).get(count);
        if (aCell.contains("Error.")) {
          concat(String.format("<td id =\"Error%s\" style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", errorID, red, aCell));
          analyteErrorTable = analyteErrorTable.concat(createAErrorCells(val,htmlAnalyte.get(val).get(0), errorID, count % 6));
          errorID++;
        } 
        else if(aCell.contains("***"))
        {
         
          concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,aCell));
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
        if (detailNum % 6 == 0 && detailNum != 0) {
          
          concat(endRow);
        }
        String aCell = htmlAnalyteD.get(val).get(detailNum);
        if (aCell.contains("Error.")) {
         concat(String.format("<td id=\"Error%s\" style=\"background-color:#%s\"><pre>%s</pre></td>\r\n",errorID, red, aCell));
          analyteErrorTable = analyteErrorTable.concat(createADErrorCells(val,htmlAnalyteD.get(val).get(0),errorID,detailNum % 6));
          errorID++;
        }
        else if(aCell.contains("***"))
        {
       
         concat(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,aCell));
        }
        else
        {
        
          concat(String.format("<td><pre>%s</pre></td>\r\n", aCell));
        }

      }
     
      concat(endRow);
  
      concat(endTable);
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
  
  private String path(String document)
  {
    return String.format(
        "C:\\Users\\nelson.scott\\gitDx\\GeneXpertUiAuto\\%s.html",
        document);
  }
  private String htmlStarter()
  {
    return "<!DOCTYPE html>\r\n<html>\r\n<head>\r\n<title>Verification Results</title>\r\n</head>\r\n<body>\r\n";
  }
  
  private String htmlEnder()
  {
    return "</body>\r\n</html>";
  }
  
  private String htmlHeader(String document)
  {
    return String.format("<h1 style= \"text-align:center;\">%s</h1>\r\n", document);
  }
  
  private String tableHeaderCells(String ... args)
  {
    String concat = ""; 
    for(String arg: args)
    {
      concat = concat.concat(String.format("<th>%s</th>\r\n", arg));
    }
    return concat;
  }

  private String styleTable()
  {
    return "<style>\r\ntable {\r\n border-collapse: collapse; \r\n } \r\n";
  }
  
  private String styleRows()
  {
    return "th,td {\r\nborder: 1px solid green; \r\n } \r\n ";
  }
  private String styleExtra()
  {
    return "tr:nth-child(even) { \r\n background-color: #eee; \r\n } \r\n tr:nth-child(odd) { \r\n background-color: #fff; \r\n } \r\n </style> \r\n";
  }
  
  private String beginTableCreation(String...args)
  {
    String concat = "";
    
    for(String arg: args)
    {
      concat = concat.concat(arg);
    }
    
    return concat;
  }
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
  
  private String createAErrorCells(String assay, String analyte, int error, int colNum)  {
    String errorCell = ""; 
    String colName = "";
    switch(colNum)
    {
      case 1:
        colName = "Analyte Name";
        break;
      case 2:
        colName = "Ct";
        break;
      case 3:
        colName = "End Pt";
        break;
      case 4:
        colName = "Interpretation Result";
        break;
      case 5:
        colName = "Analyte Reason";
        break;
      case 6: 
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
  private String createADErrorCells(String assay, String analyte,  int error, int colNum)  {
    String errorCell = ""; 
    String colName = "";
    switch(colNum)
    {
      case 1:
        colName = "Analyte Name";
        break;
      case 2:
        colName = "Prb Check 1";
        break;
      case 3:
        colName = "Prb Check 2";
        break;
      case 4:
        colName = "Prb Check 3";
        break;
      case 5:
        colName = "Probe Check Result";
        break;
      case 6: 
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
  private void concat(String code)
  {
    htmlCode = htmlCode.concat(code);
  }
  
  
}
