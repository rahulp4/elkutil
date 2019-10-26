package com.jsonreader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suggester.Contexts;
import com.suggester.RootNode;
import com.suggester.Suggester;
import com.util.ReSTUtil;

public class YearMakeModelReaderFromXLS {
	final static Logger logger = Logger.getLogger(YearMakeModelReaderFromXLS.class);

    public static final String SAMPLE_XLS_FILE_PATH = "./sample-xls-file.xls";
    //public static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";
    //public static final String SAMPLE_XLSX_FILE_PATH = "D:/knowledgebase/myproject/twilio/documents/DataFull/File_V3.xlsx";
    public static final String SAMPLE_XLSX_FILE_PATH = "/var/config/DataPul/conf/File_Part_1.xlsx";
    public static void main(String[] args) {
    	YearMakeModelReaderFromXLS instance	=	new YearMakeModelReaderFromXLS();
    	try {
    		List yearMakeModelList	=	instance.getYearMakeModelList();
    		logger.info(yearMakeModelList);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public List<YearMakeModelXLSDTO> getYearMakeModelList() throws IOException, InvalidFormatException {
    	int recordAndDocId	=	0;
    	
    	List<YearMakeModelXLSDTO> listYearMakeModel	=	new ArrayList<YearMakeModelXLSDTO>();
    	
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        //logger.info("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        //logger.info("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            //logger.info("=> " + sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        //logger.info("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            //logger.info("=> " + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach wih lambda
        //logger.info("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            //logger.info("=> " + sheet.getSheetName());
        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        //logger.info("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
        	recordAndDocId++;
        	YearMakeModelXLSDTO jsonString	=	getData4Upload(row);
        	//uploadDocument(jsonString,recordAndDocId);
            logger.info("Object "+jsonString);
            listYearMakeModel.add(jsonString);
        
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                //System.out.print(cellValue + "\t");
            }
            //logger.info();
        }

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        //logger.info("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                //System.out.print(cellValue + "\t");
            }
            logger.info("");
        }

        // 3. Or you can use Java 8 forEach loop with lambda
        logger.info("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                //printCellValue(cell);
                
            });
            //logger.info();
        });
        
    

//        sheet.forEach(row -> {
//        	//recordAndDocId++;
//        	String jsonString	=	getData4Upload(row);
//        	uploadDocument(jsonString,recordAndDocId);
//        	
//            logger.info();
//        });
        // Closing the workbook
        workbook.close();
        return listYearMakeModel;
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
    
    public static YearMakeModelXLSDTO getData4Upload(Row row) {
    	YearMakeModelXLSDTO root	=	new YearMakeModelXLSDTO();
    	
    	List<String> input	=	new ArrayList<String>();
    	Contexts context	=	new Contexts();
    	List<String> contextList	=	new ArrayList();
    	Suggester suggest	=	new Suggester();
        ObjectMapper mapper	=	new ObjectMapper();
        String jsonString = null;
        String output	=	"";
        row.forEach(cell -> {
            logger.info("\n Cell "+cell);
        	switch (cell.getColumnIndex()) {
            case 0:
            	//YEAR
            	if(cell==null) {
            		logger.info("0: NULL CELL");
            	} else {
                System.out.print("0: "+cell.getNumericCellValue());
                root.setYear(new Double(cell.getNumericCellValue()).intValue());
            	}
                break;
            case 1:
            	//CAR NAME
                System.out.print("1: "+cell.getStringCellValue());
                root.setMake(cell.getStringCellValue());
                break;
            case 2:
            	System.out.print("2: "+cell);
            	try {
            		root.setModel(cell.getStringCellValue());
            	} catch (Exception e) {
            		root.setModel(cell.getNumericCellValue()+"");
            	}
            	
            	//TYPE
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    System.out.print(cell.getDateCellValue());
//                } else {
//                    System.out.print(cell.getNumericCellValue());
//                }
                break;
            case 3:
            	//yearmakemodel_desc
            	System.out.print(cell.getRichStringCellValue().getString());
                break;
            case 4:
            	//yearmakemodel
            	//System.out.print(cell.getRichStringCellValue().getString());
                break;
            case 5:
            	//input
            	System.out.print(cell.getRichStringCellValue().getString());
                break;
            case 6:
            	//Category
            	
                System.out.print("");
                break;
            case 7:
            	//Sub-Category
                System.out.print("");
                break;
            case 8:
            	//Sub-Cat-2
                System.out.print("");
                break;
            case 9:
            	//Part
                System.out.print("");
                break;

            default:
                System.out.print("");
        }
            
        });
        		
    	return root;
    }
    
    static String baseurlUpload	=	"https://3a7721e6.qb0x.com:30002/ymmv2/_doc/";
    public static void uploadDocument(String jsonString,int docId) {
    	String finalURLWithDocId	= baseurlUpload+docId;
    	String str	=	null;
    	try {
    		str	=	ReSTUtil.sendPost(jsonString, finalURLWithDocId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
