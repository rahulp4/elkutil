import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbox.uploader.JsonFileWriter;
import com.suggester.Contexts;
import com.suggester.RootNode;
import com.suggester.Suggester;
import com.util.ReSTUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 18/12/17.
 */

public class JSONFileCreator {
    public static final String SAMPLE_XLS_FILE_PATH = "./sample-xls-file.xls";
    //public static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";
    //public static final String SAMPLE_XLSX_FILE_PATH = "D:/knowledgebase/myproject/twilio/documents/DataFull/v2ymm.xlsx";
    public static final String SAMPLE_XLSX_FILE_PATH = "D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\UploadToQBOX\\MASTER_DATA_SEARCH_31_SEQ_OCT_V2_Test.xlsx";
    public static final String JSON_FILE	=	"D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\JSON\\JSONData.json";
    public static final boolean bulkmode	=	true;
    
    public static void main(String[] args) throws IOException, InvalidFormatException {

    	
    	int recordAndDocId	=	1000;
    	
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        //System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        //System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            //System.out.println("=> " + sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        //System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            //System.out.println("=> " + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach wih lambda
        //System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            //System.out.println("=> " + sheet.getSheetName());
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
        //System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        JsonFileWriter jsonFileWriter	=	new JsonFileWriter();
        String filePathName	=	JSON_FILE;
        jsonFileWriter.openFileWriter(filePathName);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
        	recordAndDocId++;
        	String jsonString	=	getData4Upload(row);
        	System.out.println("Index is  "+recordAndDocId);
        	if(bulkmode) {
            	jsonFileWriter.writeFile2(jsonString);
        		
        	} else {
        		uploadDocument(jsonString,recordAndDocId);
        	}
        	//Index is  5999
        	//System.out.println("Object "+jsonString);
        
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
            //System.out.println();
        }

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        //System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                //System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                //printCellValue(cell);
                
            });
            //System.out.println();
        });
        
    

//        sheet.forEach(row -> {
//        	//recordAndDocId++;
//        	String jsonString	=	getData4Upload(row);
//        	uploadDocument(jsonString,recordAndDocId);
//        	
//            System.out.println();
//        });
        // Closing the workbook
        workbook.close();
    }

    public static void generateFile(String args1,String args2) throws IOException, InvalidFormatException {

    	
    	int recordAndDocId	=	1000;
    	
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        //System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        //System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            //System.out.println("=> " + sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        //System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            //System.out.println("=> " + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach wih lambda
        //System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            //System.out.println("=> " + sheet.getSheetName());
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
        //System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        JsonFileWriter jsonFileWriter	=	new JsonFileWriter();
        String filePathName	=	args1;
        jsonFileWriter.openFileWriter(filePathName);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
        	recordAndDocId++;
        	String jsonString	=	getData4Upload(row);
        	System.out.println("Index is  "+recordAndDocId);
        	if(bulkmode) {
            	jsonFileWriter.writeFile2(jsonString);
        		
        	} else {
        		uploadDocument(jsonString,recordAndDocId);
        	}
        	//Index is  5999
        	//System.out.println("Object "+jsonString);
        
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
            //System.out.println();
        }

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        //System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                //System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                //printCellValue(cell);
                
            });
            //System.out.println();
        });
        
    

//        sheet.forEach(row -> {
//        	//recordAndDocId++;
//        	String jsonString	=	getData4Upload(row);
//        	uploadDocument(jsonString,recordAndDocId);
//        	
//            System.out.println();
//        });
        // Closing the workbook
        workbook.close();
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
    
    public static String getData4Upload(Row row) {
    	RootNode root	=	new RootNode();
    	
    	List<String> input	=	new ArrayList<String>();
    	Contexts context	=	new Contexts();
    	List<String> contextList	=	new ArrayList();
    	Suggester suggest	=	new Suggester();
        ObjectMapper mapper	=	new ObjectMapper();
        String jsonString = null;
        String output	=	"";
        row.forEach(cell -> {
            System.out.println("\n Cell "+cell.getColumnIndex());
        	switch (cell.getColumnIndex()) {
            case 0:
            	//YEAR
                //System.out.print(cell.getBooleanCellValue());
                break;
            case 1:
            	//CAR NAME
                //System.out.print(cell.getRichStringCellValue().getString());
                break;
            case 2:
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
            	root.setYearmakemodel_desc(cell.getRichStringCellValue().getString());
                break;
            case 4:
            	//yearmakemodel
            	//System.out.print(cell.getRichStringCellValue().getString());
            	contextList.add(cell.getRichStringCellValue().getString());
                break;
            case 5:
            	//input
            	System.out.print(cell.getRichStringCellValue().getString());
                break;
            case 6:
            	//Category
            	String str	=	cell.getRichStringCellValue().getString();
            	
            	if(str!=null && !str.trim().equals("")) {
            		
            		input.add(str);
            		
            	}
            	
                System.out.print("");
                break;
            case 7:
            	//Sub-Category
            	String strSubCategory	=	cell.getRichStringCellValue().getString();
            	if(strSubCategory!=null && !strSubCategory.trim().equals("")) {
            		input.add(strSubCategory);	
            	}

                System.out.print("");
                break;
            case 8:
            	//Sub-Cat-2
            	String strSubCategory2	=	cell.getRichStringCellValue().getString();
            	if(strSubCategory2!=null && !strSubCategory2.trim().equals("")) {
            		input.add(strSubCategory2);	
            	}
                System.out.print("");
                break;
            case 9:
            	//Part
            	String part	=	cell.getRichStringCellValue().getString();
            	if(part!=null && !part.trim().equals("")) {
            		input.add(part);	
            	}

                System.out.print("");
                break;

            default:
                System.out.print("");
        }
            
        });
        		
        context.setYearmakemodel(contextList);
        suggest.setContexts(context);
        suggest.setInput(input);
        for (String string : input) {
        	if(output.equals("")) {
        		output	=	string;
        	} else {
        		output	=	output+" "+string;
        	}
				
		}
        root.setOutput(output);
        root.setSuggest(suggest);
        try {
        	jsonString = mapper.writeValueAsString(root);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("*****************************\n");
        System.out.println(jsonString);
    	return jsonString;
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
    
    
    public static void uploadBulkTest() {
    	try {
    		String finalURLWithDocId	=	"https://3a7721e6.qb0x.com:30002/ymm/_bulk/";
		  File f = new File("D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\UploadToQBOX\\testbulk.json");
		  FileInputStream fis = new FileInputStream(f); byte[] buffer = new byte[10];
		  StringBuilder sb = new StringBuilder(); while (fis.read(buffer) != -1) {
			  sb.append(new String(buffer)); buffer = new byte[10]; } fis.close();
		 

		 String content = sb.toString();
		 String finalStr	=	content.replace("\\", "");//StringEscapeUtils.escapeJava(content);
		 
		 String str	=	ReSTUtil.sendPost(content, finalURLWithDocId);
		 System.out.println(str);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    }
}
