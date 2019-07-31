package Methods;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class htmlCreator extends RevisedVerifyResultMethods
{
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
    
    String tableWidth                                                       = "<table style=\"width:100%\">\r\n<tr>\r\n";
    String endRow                                                           = "</tr>\r\n";
    String endTable                                                         = "</table>\r\n";
    String beginRow                                                         = "<tr>\r\n";
    String styleTable                                                       = styleTable();
    String styleRows                                                        = styleRows();
    String styleExtra                                                       = styleExtra();
    String analyteResultsHeaders                                            = tableHeaderCells(verifyAnalyteName,verifyCt, verifyEndPt, verifyInterp, verifyAnalyteReason, verifyAnalyteResult);
    String analyteDetailHeader                                              = tableHeaderCells(verifyAnalyteDName, verifyPrbChk1, verifyPrbChk2,verifyPrbChk3, verifyPrbChkResult, verifyDerivPeak);

    //Create the table header of Test Results    
    printWriter.print(htmlStarter);
    String beginTable = beginTableCreation(header,styleTable,styleRows,styleExtra,tableWidth,tableHeaderCells,endRow); 
    printWriter.print(beginTable);

    /* Create cells with individual verification results
     * Iterates through the htmlList for each individual assay
     * 
     */
    for (int outerArray = 0; outerArray < htmlList.size(); outerArray++) {
      printWriter.print(beginRow);
      for (int innerArray = 0; innerArray < htmlList.get(outerArray).size(); innerArray++) {
        String cell = htmlList.get(outerArray).get(innerArray);
        if (cell.contains("Error.")) {
          printWriter.print(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", red, cell));
        } 
        else if(cell.contains("***"))
        {
          printWriter.print(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,cell));
        }
        else
          printWriter.print(String.format("<td><pre>%s</pre></td>\r\n", cell));
      }
      printWriter.print(endRow);
    }

    printWriter.print(endTable);

    /*
     * 
     * Begin the data creation for the Analytes of each assay
     * Creates the assay header and table label
     */
    printWriter.print("<h2 style=\"text-align:center;\">Analyte Data</h2>\r\n");
    for (String val : htmlAnalyte.keySet()) {
      printWriter.print(String.format("<h3><u>%s</u></h3>\r\n", val));
      printWriter.print(String.format("<h4>%s</h4>\r\n", "Analyte Results Verification"));
      beginTable = beginTableCreation(styleTable,styleRows,styleExtra,tableWidth);
      printWriter.print(beginTable);
      printWriter.print(analyteResultsHeaders);
      printWriter.print("</tr>\r\n<tr>\r\n");

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
          printWriter.print(endRow);
        }
        String aCell = htmlAnalyte.get(val).get(count);
        if (aCell.contains("Error.")) {
          printWriter.print(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", red, aCell));
        } 
        else if(aCell.contains("***"))
        {
          printWriter.print(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,aCell));
        }
        else
          printWriter.print(String.format("<td><pre>%s</pre></td>\r\n", aCell));
      }
      printWriter.print(endRow);
      printWriter.print(endTable);

      //Create the table for analyte details verification
      printWriter.print(String.format("<h4>%s</h4>\r\n", "Analyte Details Verification"));
      printWriter.print(beginTable);
      printWriter.print(analyteDetailHeader);
      printWriter.print("</tr>\r\n<tr>\r\n");
      
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
          printWriter.print(endRow);
        }
        String aCell = htmlAnalyteD.get(val).get(detailNum);
        if (aCell.contains("Error."))
          printWriter.print(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", red, aCell));
        else if(aCell.contains("***"))
         printWriter.print(String.format("<td style=\"background-color:#%s\"><pre>%s</pre></td>\r\n", yellow,aCell));
        else
          printWriter.print(String.format("<td><pre>%s</pre></td>\r\n", aCell));

      }
      printWriter.print(endRow);
      printWriter.print(endTable);
    }

    //Finish the html code
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
  
  
}
